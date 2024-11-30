package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.*;
import com.pado.inflow.attendance.command.domain.aggregate.entity.*;
import com.pado.inflow.attendance.command.domain.aggregate.type.*;
import com.pado.inflow.attendance.command.domain.repository.*;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service("appAttendanceService")
public class AttendanceRequestServiceImpl implements AttendanceRequestService {

    private final ModelMapper modelMapper;
    private final AttendanceRequestRepository attendanceRequestRepository;
    private final AttendanceRequestTypeRepository attendanceRequestTypeRepository;
    private final AttendanceRequestFileRepository attendanceRequestFileRepository;
    private final BusinessTripRepository businessTripRepository;
    private final CommuteRepository commuteRepository;
    private final LeaveReturnRepository leaveReturnRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceS3Service attendanceS3Service;

    @Autowired
    public AttendanceRequestServiceImpl(ModelMapper modelMapper,
                                        AttendanceRequestRepository attendanceRequestRepository,
                                        AttendanceRequestTypeRepository attendanceRequestTypeRepository,
                                        AttendanceRequestFileRepository attendanceRequestFileRepository,
                                        BusinessTripRepository businessTripRepository,
                                        CommuteRepository commuteRepository,
                                        LeaveReturnRepository leaveReturnRepository,
                                        EmployeeRepository employeeRepository,
                                        AttendanceS3Service attendanceS3Service) {
        this.modelMapper = modelMapper;
        this.attendanceRequestRepository = attendanceRequestRepository;
        this.attendanceRequestTypeRepository = attendanceRequestTypeRepository;
        this.attendanceRequestFileRepository = attendanceRequestFileRepository;
        this.businessTripRepository = businessTripRepository;
        this.commuteRepository = commuteRepository;
        this.leaveReturnRepository = leaveReturnRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceS3Service = attendanceS3Service;
    }

    // 재택근무 신청
    @Transactional
    @Override
    public ResponseCommuteRequestDTO registRemoteRequest(RequestCommuteRequestDTO reqCommuteRequestDTO) {
        // 사원 유효성 검사
        employeeRepository.findById(reqCommuteRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 근태신청유형 유효성 검사
        AttendanceRequestType attendanceRequestType = attendanceRequestTypeRepository.findById(reqCommuteRequestDTO.getAttendanceRequestTypeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE));

        if (!attendanceRequestType.getAttendanceRequestTypeName().equals("재택근무")) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 신청사유 유효성 검사
        if (reqCommuteRequestDTO.getRequestReason() == null || reqCommuteRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 날짜 String -> LocalDateTime 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            date = LocalDate.parse(reqCommuteRequestDTO.getStartDate(), formatter);
            startDate = date.atStartOfDay();
            endDate = date.atStartOfDay();

        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 재택근무 시작일이 신청일보다 이전일 경우
        if (startDate.toLocalDate().isBefore(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseCommuteRequestDTO responseCommuteRequestDTO = ResponseCommuteRequestDTO
                .builder()
                .requestReason(reqCommuteRequestDTO.getRequestReason())
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(LocalDateTime.now().withNano(0))
                .rejectionReason(null)
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .employeeId(reqCommuteRequestDTO.getEmployeeId())
                .attendanceRequestTypeId(reqCommuteRequestDTO.getAttendanceRequestTypeId())
                .build();

        // 재택근무 신청 내역 등록
        AttendanceRequest attendanceRequest =
                attendanceRequestRepository.save(modelMapper.map(responseCommuteRequestDTO, AttendanceRequest.class));

        // 재택근무 내역 등록
        ResponseCommuteDTO commuteDTO = ResponseCommuteDTO
                .builder()
                .startTime(null)
                .endTime(null)
                .remoteStatus(RemoteStatus.Y)
                .overtimeStatus(OvertimeStatus.N)
                .employeeId(attendanceRequest.getEmployeeId())
                .attendanceRequestId(attendanceRequest.getAttendanceRequestId())
                .build();

        commuteRepository.save(modelMapper.map(commuteDTO, Commute.class));

        return modelMapper.map(attendanceRequest, ResponseCommuteRequestDTO.class);
    }

    // 초과근무 신청
    @Transactional
    @Override
    public ResponseCommuteRequestDTO registOvertimeRequest(RequestCommuteRequestDTO reqCommuteRequestDTO) {
        // 사원 유효성 검사
        employeeRepository.findById(reqCommuteRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 근태신청유형 유효성 검사
        AttendanceRequestType attendanceRequestType = attendanceRequestTypeRepository.findById(reqCommuteRequestDTO.getAttendanceRequestTypeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE));

        if (!attendanceRequestType.getAttendanceRequestTypeName().equals("초과근무")) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 신청사유 유효성 검사
        if (reqCommuteRequestDTO.getRequestReason() == null || reqCommuteRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 시간 String -> LocalDateTime 변환
        DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startTime;
        LocalDateTime startDate;
        LocalDateTime endTime;

        try {
            startTime = LocalDateTime.parse(reqCommuteRequestDTO.getStartDate(), dateTimeformatter);
            startDate = startTime.toLocalDate().atStartOfDay();
            endTime = LocalDateTime.parse(reqCommuteRequestDTO.getEndDate(), dateTimeformatter);
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 초과근무 시작일이 신청일과 다른 경우
        if (!startDate.toLocalDate().equals(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 초과근무 시작시간이 현재 시간 이후여야 한다
        if (startTime.isBefore(LocalDateTime.now().withNano(0))) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 초과근무 시작시간이 오후 6시(18시) 이후여야 한다
        if (startTime.getHour() < 18) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 초과근무 종료시간이 시작시간보다 30분 이상 뒤에 있어야 하고, 30분 단위여야 한다
        if (Duration.between(startTime, endTime).toMinutes() < 30) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        if (endTime.getMinute() != 0 && endTime.getMinute() != 30) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseCommuteRequestDTO responseCommuteRequestDTO = ResponseCommuteRequestDTO
                .builder()
                .requestReason(reqCommuteRequestDTO.getRequestReason())
                .startDate(startTime)
                .endDate(endTime)
                .createdAt(LocalDateTime.now().withNano(0))
                .rejectionReason(null)
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .employeeId(reqCommuteRequestDTO.getEmployeeId())
                .attendanceRequestTypeId(reqCommuteRequestDTO.getAttendanceRequestTypeId())
                .build();

        // 초과근무 신청 내역 등록
        AttendanceRequest attendanceRequest =
                attendanceRequestRepository.save(modelMapper.map(responseCommuteRequestDTO, AttendanceRequest.class));

        // 초과근무 내역 등록
        ResponseCommuteDTO commuteDTO = ResponseCommuteDTO
                .builder()
                .startTime(startTime)
                .endTime(endTime)
                .remoteStatus(RemoteStatus.N)
                .overtimeStatus(OvertimeStatus.Y)
                .employeeId(attendanceRequest.getEmployeeId())
                .attendanceRequestId(attendanceRequest.getAttendanceRequestId())
                .build();

        commuteRepository.save(modelMapper.map(commuteDTO, Commute.class));

        return modelMapper.map(attendanceRequest, ResponseCommuteRequestDTO.class);
    }

    // 출장 신청
    @Transactional
    @Override
    public ResponseBusinessTripRequestDTO registBusinessTripRequest(RequestBusinessTripRequestDTO reqBusinessTripRequestDTO) {
        // 사원 유효성 검사
        employeeRepository.findById(reqBusinessTripRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 근태신청유형 유효성 검사
        AttendanceRequestType attendanceRequestType =
                attendanceRequestTypeRepository.findById(reqBusinessTripRequestDTO.getAttendanceRequestTypeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE));

        if (!attendanceRequestType.getAttendanceRequestTypeName().equals("출장")) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 신청사유 유효성 검사
        if (reqBusinessTripRequestDTO.getRequestReason() == null || reqBusinessTripRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 목적지 유효성 검사
        if (reqBusinessTripRequestDTO.getDestination() == null || reqBusinessTripRequestDTO.getDestination().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 날짜 String -> LocalDateTime 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            date = LocalDate.parse(reqBusinessTripRequestDTO.getStartDate(), formatter);
            startDate = date.atStartOfDay();
            date = LocalDate.parse(reqBusinessTripRequestDTO.getEndDate(), formatter);
            endDate = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 출장 시작일이 신청일보다 이전일 경우
        if (startDate.toLocalDate().isBefore(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 출장 종료일이 시작일보다 이전일 경우
        if (endDate.isBefore(startDate)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseBusinessTripRequestDTO resBusinessTripRequestDTO = ResponseBusinessTripRequestDTO
                .builder()
                .requestReason(reqBusinessTripRequestDTO.getRequestReason())
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(LocalDateTime.now().withNano(0))
                .rejectionReason(null)
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .destination(reqBusinessTripRequestDTO.getDestination())
                .employeeId(reqBusinessTripRequestDTO.getEmployeeId())
                .attendanceRequestTypeId(reqBusinessTripRequestDTO.getAttendanceRequestTypeId())
                .build();

        // 출장 신청 내역 등록
        AttendanceRequest attendanceRequest =
                attendanceRequestRepository.save(modelMapper.map(resBusinessTripRequestDTO, AttendanceRequest.class));

        // 출장 내역 등록
        ResponseBusinessTripDTO businessTripDTO = ResponseBusinessTripDTO
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .tripType(TripType.BUSINESS)
                .destination(attendanceRequest.getDestination())
                .employeeId(attendanceRequest.getEmployeeId())
                .attendanceRequestId(attendanceRequest.getAttendanceRequestId())
                .build();

        businessTripRepository.save(modelMapper.map(businessTripDTO, BusinessTrip.class));

        return modelMapper.map(attendanceRequest, ResponseBusinessTripRequestDTO.class);
    }

    // 파견 신청
    @Transactional
    @Override
    public ResponseBusinessTripRequestDTO registDispatchRequest(RequestBusinessTripRequestDTO reqBusinessTripRequestDTO) {
        // 사원 유효성 검사
        employeeRepository.findById(reqBusinessTripRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 근태신청유형 유효성 검사
        AttendanceRequestType attendanceRequestType =
                attendanceRequestTypeRepository.findById(reqBusinessTripRequestDTO.getAttendanceRequestTypeId())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE));

        if (!attendanceRequestType.getAttendanceRequestTypeName().equals("파견")) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 신청사유 유효성 검사
        if (reqBusinessTripRequestDTO.getRequestReason() == null || reqBusinessTripRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 목적지 유효성 검사
        if (reqBusinessTripRequestDTO.getDestination() == null || reqBusinessTripRequestDTO.getDestination().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 날짜 String -> LocalDateTime 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            date = LocalDate.parse(reqBusinessTripRequestDTO.getStartDate(), formatter);
            startDate = date.atStartOfDay();
            date = LocalDate.parse(reqBusinessTripRequestDTO.getEndDate(), formatter);
            endDate = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 파견 시작일이 신청일보다 이전일 경우
        if (startDate.toLocalDate().isBefore(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 파견 종료일이 시작일보다 이전일 경우
        if (endDate.isBefore(startDate)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseBusinessTripRequestDTO resBusinessTripRequestDTO = ResponseBusinessTripRequestDTO
                .builder()
                .requestReason(reqBusinessTripRequestDTO.getRequestReason())
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(LocalDateTime.now().withNano(0))
                .rejectionReason(null)
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .destination(reqBusinessTripRequestDTO.getDestination())
                .employeeId(reqBusinessTripRequestDTO.getEmployeeId())
                .attendanceRequestTypeId(reqBusinessTripRequestDTO.getAttendanceRequestTypeId())
                .build();

        // 파견 신청 내역 등록
        AttendanceRequest attendanceRequest =
                attendanceRequestRepository.save(modelMapper.map(resBusinessTripRequestDTO, AttendanceRequest.class));

        // 파견 내역 등록
        ResponseBusinessTripDTO businessTripDTO = ResponseBusinessTripDTO
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .tripType(TripType.DISPATCH)
                .destination(attendanceRequest.getDestination())
                .employeeId(attendanceRequest.getEmployeeId())
                .attendanceRequestId(attendanceRequest.getAttendanceRequestId())
                .build();

        businessTripRepository.save(modelMapper.map(businessTripDTO, BusinessTrip.class));

        return modelMapper.map(attendanceRequest, ResponseBusinessTripRequestDTO.class);
    }

    // 휴직 신청
    @Transactional
    @Override
    public ResponseLeaveReturnRequestDTO registLeaveRequest(RequestLeaveRequestDTO reqLeaveRequestDTO) {
        // 사원 유효성 검사
        employeeRepository.findById(reqLeaveRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 근태신청유형 유효성 검사
        AttendanceRequestType attendanceRequestType =
                attendanceRequestTypeRepository.findById(reqLeaveRequestDTO.getAttendanceRequestTypeId())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE));

        if (!attendanceRequestType.getAttendanceRequestTypeName().equals("휴직")) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 신청사유 유효성 검사
        if (reqLeaveRequestDTO.getRequestReason() == null || reqLeaveRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 날짜 String -> LocalDateTime 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            date = LocalDate.parse(reqLeaveRequestDTO.getStartDate(), formatter);
            startDate = date.atStartOfDay();
            date = LocalDate.parse(reqLeaveRequestDTO.getEndDate(), formatter);
            endDate = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴직 시작일이 신청일보다 이전일 경우
        if (startDate.toLocalDate().isBefore(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴직 종료일이 시작일보다 이전일 경우
        if (endDate.isBefore(startDate)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseLeaveReturnRequestDTO resLeaveReturnRequestDTO = ResponseLeaveReturnRequestDTO
                .builder()
                .requestReason(reqLeaveRequestDTO.getRequestReason())
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(LocalDateTime.now().withNano(0))
                .rejectionReason(null)
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .employeeId(reqLeaveRequestDTO.getEmployeeId())
                .attendanceRequestTypeId(reqLeaveRequestDTO.getAttendanceRequestTypeId())
                .build();


        // 휴직 신청 내역 등록
        AttendanceRequest leaveRequest =
                attendanceRequestRepository.save(modelMapper.map(resLeaveReturnRequestDTO, AttendanceRequest.class));

        if (reqLeaveRequestDTO.getAttachments() != null && !reqLeaveRequestDTO.getAttachments().isEmpty()) {
            // 첨부 파일 DB 저장
            for (MultipartFile file : reqLeaveRequestDTO.getAttachments()) {

                String fileUrl = attendanceS3Service.uploadFile(file, leaveRequest.getEmployeeId());

                ResponseAttendanceRequestFileDTO resAttendanceRequestFileDTO = ResponseAttendanceRequestFileDTO
                        .builder()
                        .fileName(file.getOriginalFilename())
                        .fileUrl(fileUrl)
                        .attendanceRequestId(leaveRequest.getAttendanceRequestId())
                        .build();

                attendanceRequestFileRepository.save(modelMapper.map(resAttendanceRequestFileDTO, AttendanceRequestFile.class));
            }
        }

        // 휴직 내역 등록
        ResponseLeaveReturnDTO leaveReturnDTO = ResponseLeaveReturnDTO
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .employeeId(leaveRequest.getEmployeeId())
                .attendanceRequestId(leaveRequest.getAttendanceRequestId())
                .build();

        leaveReturnRepository.save(modelMapper.map(leaveReturnDTO, LeaveReturn.class));

        return modelMapper.map(leaveRequest, ResponseLeaveReturnRequestDTO.class);
    }

    // 복직 신청
    @Override
    public ResponseLeaveReturnRequestDTO registReturnRequest(RequestReturnRequestDTO reqReturnRequestDTO) {
        // 근태신청 유효성 검사
        AttendanceRequest leaveRequest =
                attendanceRequestRepository.findById(reqReturnRequestDTO.getAttendanceRequestId())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST));

        // 사원 유효성 검사
        employeeRepository.findById(reqReturnRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 근태신청유형 유효성 검사
        AttendanceRequestType attendanceRequestType =
                attendanceRequestTypeRepository.findById(reqReturnRequestDTO.getAttendanceRequestTypeId())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE));

        if (!attendanceRequestType.getAttendanceRequestTypeName().equals("복직")) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 신청사유 유효성 검사
        if (reqReturnRequestDTO.getRequestReason() == null || reqReturnRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 날짜 String -> LocalDateTime 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime endDate;
        try {
            date = LocalDate.parse(reqReturnRequestDTO.getEndDate(), formatter);
            endDate = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴직 종료일이 신청일보다 이전일 경우
        if (endDate.toLocalDate().isBefore(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴직 종료일이 시작일보다 이전일 경우
        if (endDate.toLocalDate().isBefore(leaveRequest.getStartDate().toLocalDate())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴직 종료일이 변경 이전 종료일보다 이후일 경우
        if (endDate.toLocalDate().isAfter(leaveRequest.getEndDate().toLocalDate())) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseLeaveReturnRequestDTO resLeaveReturnDTO = ResponseLeaveReturnRequestDTO
                .builder()
                .requestReason(reqReturnRequestDTO.getRequestReason())
                .startDate(LocalDateTime.now().withNano(0))
                .endDate(endDate)
                .createdAt(LocalDateTime.now().withNano(0))
                .rejectionReason(null)
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .employeeId(reqReturnRequestDTO.getEmployeeId())
                .attendanceRequestTypeId(reqReturnRequestDTO.getAttendanceRequestTypeId())
                .build();

        // 복직 신청 등록
        AttendanceRequest returnRequest =
                attendanceRequestRepository.save(modelMapper.map(resLeaveReturnDTO, AttendanceRequest.class));

        if (reqReturnRequestDTO.getAttachments() != null && !reqReturnRequestDTO.getAttachments().isEmpty()) {
            // 첨부 파일 DB 저장
            for (MultipartFile file : reqReturnRequestDTO.getAttachments()) {

                String fileUrl = attendanceS3Service.uploadFile(file, returnRequest.getEmployeeId());

                ResponseAttendanceRequestFileDTO resAttendanceRequestFileDTO = ResponseAttendanceRequestFileDTO
                        .builder()
                        .fileName(file.getOriginalFilename())
                        .fileUrl(fileUrl)
                        .attendanceRequestId(returnRequest.getAttendanceRequestId())
                        .build();

                attendanceRequestFileRepository.save(modelMapper.map(resAttendanceRequestFileDTO, AttendanceRequestFile.class));
            }
        }

        // 휴직 종료일 변경
        leaveRequest.setEndDate(endDate);

        LeaveReturn leaveReturn =
                leaveReturnRepository.findByAttendanceRequestId(leaveRequest.getAttendanceRequestId())
                                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LEAVE_RETURN));

        leaveReturn.setEndDate(endDate);

        attendanceRequestRepository.save(leaveRequest);
        leaveReturnRepository.save(leaveReturn);

        return modelMapper.map(returnRequest, ResponseLeaveReturnRequestDTO.class);
    }

    // 근태신청 취소
    @Transactional
    @Override
    public ResponseAttendanceRequestDTO cancelAttendanceRequest(Long attendanceRequestId, RequestCancelAttendanceRequestDTO reqCancelAttendanceRequestDTO) {
        // 기존 엔티티 조회
        AttendanceRequest attendanceRequest = attendanceRequestRepository.findById(attendanceRequestId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST));

        // 승인 대기중이 아닌 경우
        if (!(attendanceRequest.getRequestStatus() == RequestStatus.WAIT)) {
            throw new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST);
        }

        // 취소 사유 유효성 검사
        if (reqCancelAttendanceRequestDTO.getCancelReason() == null || reqCancelAttendanceRequestDTO.getCancelReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        attendanceRequest.setRequestStatus(RequestStatus.ACCEPT);
        attendanceRequest.setCanceledAt(LocalDateTime.now().withNano(0));
        attendanceRequest.setCancelReason(reqCancelAttendanceRequestDTO.getCancelReason());
        attendanceRequest.setCancelStatus(CancelStatus.Y);

        return modelMapper.map(attendanceRequestRepository.save(attendanceRequest), ResponseAttendanceRequestDTO.class);
    }

    // 초과근무 연장
    @Transactional
    @Override
    public ResponseCommuteRequestDTO extendOvertime(Long attendanceRequestId, RequestOvertimeExtensionDTO reqOvertimeExtensionDTO) {
        // 기존 엔티티 조회
        AttendanceRequest attendanceRequest = attendanceRequestRepository.findById(attendanceRequestId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST));

        // 승인된 요청이 아닌 경우
        if (!(attendanceRequest.getRequestStatus() == RequestStatus.ACCEPT)) {
            throw new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST);
        }

        Commute commute = commuteRepository.findByAttendanceRequestId(attendanceRequestId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMUTE));

        // 시간 String -> LocalDateTime 변환
        DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime endTime;
        LocalDateTime endDate;

        try {
            endTime = LocalDateTime.parse(reqOvertimeExtensionDTO.getEndTime(), dateTimeformatter);
            endDate = endTime.toLocalDate().atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 연장할 초과근무 종료 시간이 현재보다 이전일 경우
        if (endTime.isBefore(LocalDateTime.now().withNano(0))) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 연장할 초과근무 종료시간이 이전 종료 시간보다 30분 이상 뒤에 있어야 하고, 30분 단위여야 한다
        if (Duration.between(commute.getEndTime(), endTime).toMinutes() < 30) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        if (endTime.getMinute() != 0 && endTime.getMinute() != 30) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 초과근무 종료시간 연장
        attendanceRequest.setEndDate(endDate);
        commute.setEndTime(endTime);
        commuteRepository.save(commute);

        return modelMapper.map(attendanceRequestRepository.save(attendanceRequest), ResponseCommuteRequestDTO.class);
    }

}
