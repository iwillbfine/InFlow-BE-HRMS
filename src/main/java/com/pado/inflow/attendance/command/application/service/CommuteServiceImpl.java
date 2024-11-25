package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.CommuteDTO;
import com.pado.inflow.attendance.command.domain.aggregate.entity.Commute;
import com.pado.inflow.attendance.command.domain.aggregate.type.OvertimeStatus;
import com.pado.inflow.attendance.command.domain.aggregate.type.RemoteStatus;
import com.pado.inflow.attendance.command.domain.repository.CommuteRepository;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service("appCommuteService")
public class CommuteServiceImpl implements CommuteService {

    private final ModelMapper modelMapper;
    private final CommuteRepository commuteRepository;

    public CommuteServiceImpl(ModelMapper modelMapper, CommuteRepository commuteRepository) {
        this.modelMapper = modelMapper;
        this.commuteRepository = commuteRepository;
    }

    // 출근
    @Override
    @Transactional
    public CommuteDTO goToWork(Long employeeId) {
        CommuteDTO commuteDTO = CommuteDTO
                .builder()
                .startTime(LocalDateTime.now().withNano(0))
                .endTime(LocalDate.now().atTime(18,0))
                .remoteStatus(RemoteStatus.N)
                .overtimeStatus(OvertimeStatus.N)
                .employeeId(employeeId)
                .attendanceRequestId(null)
                .build();

        Commute commute = modelMapper.map(commuteDTO, Commute.class);

        return modelMapper.map(commuteRepository.save(commute), CommuteDTO.class);
    }

    // 재택근무 출근
    @Override
    @Transactional
    public CommuteDTO goToRemoteWork(Long commuteId) {
        Commute commute = commuteRepository.findById(commuteId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMUTE));

        commute.setStartTime(LocalDateTime.now().withNano(0));
        commute.setEndTime(LocalDate.now().atTime(18,0));

        return modelMapper.map(commuteRepository.save(commute), CommuteDTO.class);
    }

}
