package com.pado.inflow.statistics.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.statistics.command.domain.aggregate.dto.OvertimeAllowanceDTO;
import com.pado.inflow.statistics.command.domain.aggregate.entity.OvertimeAllowance;
import com.pado.inflow.statistics.command.domain.repository.OvertimeAllowanceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("OACommandService")
public class OvertimeAllowanceServiceImpl implements OvertimeAllowanceService {

    private final OvertimeAllowanceRepository overtimeAllowanceRepository;
    private final com.pado.inflow.statistics.query.service.OvertimeAllowanceService overtimeAllowanceService;
    private final ModelMapper modelMapper;

    @Autowired
    public OvertimeAllowanceServiceImpl(OvertimeAllowanceRepository overtimeAllowanceRepository,
                                        com.pado.inflow.statistics.query.service.OvertimeAllowanceService overtimeAllowanceService,
                                        ModelMapper modelMapper) {
        this.overtimeAllowanceRepository = overtimeAllowanceRepository;
        this.overtimeAllowanceService = overtimeAllowanceService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    // 초과근무수당 통계 초기화
    @Override
    public List<OvertimeAllowanceDTO> initOvertimeAllowance() {
        return Optional.of(overtimeAllowanceStatistics()
                        .stream()
                        .map(entity -> modelMapper.map(entity, OvertimeAllowanceDTO.class))
                        .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 매달 초과근무수당 통계 업데이트
    @Override
    @Scheduled(cron = "59 59 23 L * ?", zone = "Asia/Seoul")
    public void updateOvertimeAllowance() {
        overtimeAllowanceStatistics();
    }

    // 초과근무수당 통계 생성
    @Transactional
    protected List<OvertimeAllowance> overtimeAllowanceStatistics() {
        List<OvertimeAllowance> oas = new ArrayList<>();
        overtimeAllowanceService.getAllOAStats(null).forEach(l -> {
            l.getMonthly().forEach(m -> {
                m.getDepts().forEach(d -> {
                    OvertimeAllowanceDTO tmp =  new OvertimeAllowanceDTO(l.getYear(),
                            m.getMonth(),
                            d.getTotalAmount(),
                            LocalDate.now(),
                            d.getDepartmentCode());
                    oas.add(modelMapper.map(tmp, OvertimeAllowance.class));
                });

            });
        });
        overtimeAllowanceRepository.deleteAll();
        return Optional.ofNullable(overtimeAllowanceRepository.saveAll(oas))
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
