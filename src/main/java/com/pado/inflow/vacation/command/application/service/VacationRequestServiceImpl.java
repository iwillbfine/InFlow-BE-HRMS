package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import com.pado.inflow.vacation.command.application.dto.RequestCancelVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationRequestFileDTO;
import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationRequest;
import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationRequestFile;
import com.pado.inflow.vacation.command.domain.aggregate.type.CancelStatus;
import com.pado.inflow.vacation.command.domain.aggregate.type.ExpirationStatus;
import com.pado.inflow.vacation.command.domain.aggregate.type.RequestStatus;
import com.pado.inflow.vacation.command.domain.repository.VacationRepository;
import com.pado.inflow.vacation.command.domain.repository.VacationRequestFileRepository;
import com.pado.inflow.vacation.command.domain.repository.VacationRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Service("appVacationRequestService")
public class VacationRequestServiceImpl implements VacationRequestService {

    private final ModelMapper modelMapper;
    private final VacationRequestRepository vacationRequestRepository;
    private final VacationRequestFileRepository vacationRequestFileRepository;
    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;
    private final VacationS3Service vacationS3Service;

    @Autowired
    public VacationRequestServiceImpl(ModelMapper modelMapper,
                                      VacationRequestRepository vacationRequestRepository,
                                      VacationRequestFileRepository vacationRequestFileRepository,
                                      VacationRepository vacationRepository,
                                      EmployeeRepository employeeRepository,
                                      VacationS3Service vacationS3Service) {
        this.modelMapper = modelMapper;
        this.vacationRequestRepository = vacationRequestRepository;
        this.vacationRequestFileRepository = vacationRequestFileRepository;
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
        this.vacationS3Service = vacationS3Service;
    }

    // 휴가 신청 등록
    @Transactional
    @Override
    public ResponseVacationRequestDTO registVacationRequest(RequestVacationRequestDTO reqVacationRequestDTO) {
        // 사원 유효성 검사
        employeeRepository.findById(reqVacationRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 날짜 String -> LocalDateTime 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            date = LocalDate.parse(reqVacationRequestDTO.getStartDate(), formatter);
            startDate = date.atStartOfDay();
            date = LocalDate.parse(reqVacationRequestDTO.getEndDate(), formatter);
            endDate = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 종료일자가 시작일자보다 이전일 경우
        if (endDate.isBefore(startDate)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴가 유효성 검사
        Vacation vacation = vacationRepository.findById(reqVacationRequestDTO.getVacationId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_VACATION));

        if (vacation.getExpirationStatus() == ExpirationStatus.Y) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION);
        }

        // 남은 휴가일수보다 사용하는 휴가일수가 많은 경우
        long requestedDays = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
        if (requestedDays > vacation.getVacationLeft()) {
            throw new CommonException(ErrorCode.INSUFFICIENT_VACATION_DAYS);
        }

        // 휴가 사유가 NULL 이거나 비어있을 경우
        if (reqVacationRequestDTO.getRequestReason() == null || reqVacationRequestDTO.getRequestReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseVacationRequestDTO resVacationRequestDTO = ResponseVacationRequestDTO
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(LocalDateTime.now().withNano(0))
                .requestReason(reqVacationRequestDTO.getRequestReason())
                .requestStatus(RequestStatus.ACCEPT.name())
                .canceledAt(null)
                .cancelReason(null)
                .cancelStatus(CancelStatus.N.name())
                .employeeId(reqVacationRequestDTO.getEmployeeId())
                .vacationId(reqVacationRequestDTO.getVacationId())
                .build();

        VacationRequest vacationRequest =
                vacationRequestRepository.save(modelMapper.map(resVacationRequestDTO, VacationRequest.class));

        if (reqVacationRequestDTO.getAttachments() != null && !reqVacationRequestDTO.getAttachments().isEmpty()) {
            // 첨부 파일 DB 저장
            try {
                for (MultipartFile file : reqVacationRequestDTO.getAttachments()) {

                    String fileUrl = vacationS3Service.uploadFile(file, vacationRequest.getEmployeeId());

                    ResponseVacationRequestFileDTO resVacationRequestFileDTO = ResponseVacationRequestFileDTO
                            .builder()
                            .fileName(file.getOriginalFilename())
                            .fileUrl(fileUrl)
                            .vacationRequestId(vacationRequest.getVacationRequestId())
                            .build();

                    vacationRequestFileRepository.save(modelMapper.map(resVacationRequestFileDTO, VacationRequestFile.class));
                }
            } catch (Exception e) {
                throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
            }
        }

        // 휴가 사용 처리하는 로직
        vacation.setVacationLeft(vacation.getVacationLeft()-requestedDays);
        vacation.setVacationUsed(vacation.getVacationUsed()+requestedDays);
        vacationRepository.save(vacation);

        return modelMapper.map(vacationRequest, ResponseVacationRequestDTO.class);
    }

    // 휴가 신청 취소
    @Transactional
    @Override
    public ResponseVacationRequestDTO cancelVacationRequest(Long vacationRequestId,
                                                            RequestCancelVacationRequestDTO reqCancelVacationRequestDTO) {
        // 기존 엔티티 조회
        VacationRequest vacationRequest = vacationRequestRepository.findById(vacationRequestId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_VACATION_REQUEST));

        // 취소 사유가 NULL 이거나 비어있을 경우
        if (reqCancelVacationRequestDTO.getCancelReason() == null || reqCancelVacationRequestDTO.getCancelReason().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        vacationRequest.setCanceledAt(LocalDateTime.now().withNano(0));
        vacationRequest.setRequestStatus(RequestStatus.ACCEPT);
        vacationRequest.setCancelReason(reqCancelVacationRequestDTO.getCancelReason());
        vacationRequest.setCancelStatus(CancelStatus.Y);

        return modelMapper.map(vacationRequestRepository.save(vacationRequest), ResponseVacationRequestDTO.class);
    }
}
