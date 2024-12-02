package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.ResponseCommuteDTO;
import com.pado.inflow.attendance.command.domain.aggregate.entity.Commute;
import com.pado.inflow.attendance.command.domain.aggregate.type.OvertimeStatus;
import com.pado.inflow.attendance.command.domain.aggregate.type.RemoteStatus;
import com.pado.inflow.attendance.command.domain.repository.CommuteRepository;
import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.service.CommuteQueryService;
import com.pado.inflow.attendance.query.service.LeaveReturnService;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.query.service.VacationRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service("appCommuteService")
public class CommuteCommandServiceImpl implements CommuteCommandService {

    private final ModelMapper modelMapper;
    private final CommuteRepository commuteRepository;
    private final CommuteQueryService commuteQueryService;
    private final LeaveReturnService leaveReturnService;
    private final VacationRequestService vacationRequestService;

    public CommuteCommandServiceImpl(ModelMapper modelMapper,
                                     CommuteRepository commuteRepository,
                                     CommuteQueryService commuteQueryService,
                                     LeaveReturnService leaveReturnService,
                                     VacationRequestService vacationRequestService) {
        this.modelMapper = modelMapper;
        this.commuteRepository = commuteRepository;
        this.commuteQueryService = commuteQueryService;
        this.leaveReturnService = leaveReturnService;
        this.vacationRequestService = vacationRequestService;
    }

    // 출근
    @Override
    @Transactional
    public String checkAndUpdateCommute(Long employeeId) {

        LocalTime now = LocalDateTime.now().toLocalTime();

        // 기준 시간 설정
        LocalTime evening = LocalTime.of(18, 0); // 오후 6시
        LocalTime morning = LocalTime.of(9, 0); // 오전 9시

        // 근무 시간 검사
        if (now.isAfter(evening) || now.isBefore(morning)) {
            return "근무 시간이 아닙니다.";
        }

        // 오늘 휴가인가
        if(vacationRequestService.isVacationNow(employeeId)) {
            return "휴가중입니다.";
        }

        // 오늘 휴직인가
        if(leaveReturnService.isLeaveNow(employeeId)) {
            return "휴직중입니다.";
        }

        // 오늘 출근한 적이 있는가
        if(commuteQueryService.findTodayCommuteByEmployeeId(employeeId) != null) {
            return "이미 출근했습니다.";
        }

        // 오늘 재택근무인가
        CommuteDTO remote = commuteQueryService.findTodayRemoteByEmployeeId(employeeId);
        if(remote != null) {
            goToRemoteWork(remote.getCommuteId());
            return "재택 출근 완료";
        }

        // 출퇴근 내역 생성
        goToWork(employeeId);
        return "출근 완료";
    }

    // 일반 출퇴근 내역 생성
    @Transactional
    public ResponseCommuteDTO goToWork(Long employeeId) {
        ResponseCommuteDTO commuteDTO = ResponseCommuteDTO
                .builder()
                .startTime(LocalDateTime.now().withNano(0))
                .endTime(LocalDate.now().atTime(18,0))
                .remoteStatus(RemoteStatus.N)
                .overtimeStatus(OvertimeStatus.N)
                .employeeId(employeeId)
                .attendanceRequestId(null)
                .build();

        Commute commute = modelMapper.map(commuteDTO, Commute.class);

        return modelMapper.map(commuteRepository.save(commute), ResponseCommuteDTO.class);
    }

    // 재택근무 출퇴근 내역 업데이트
    @Transactional
    public ResponseCommuteDTO goToRemoteWork(Long commuteId) {
        Commute commute = commuteRepository.findById(commuteId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMUTE));

        commute.setStartTime(LocalDateTime.now().withNano(0));
        commute.setEndTime(LocalDate.now().atTime(18,0));

        return modelMapper.map(commuteRepository.save(commute), ResponseCommuteDTO.class);
    }

}
