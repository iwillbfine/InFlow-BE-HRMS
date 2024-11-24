package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.common.Common;
import com.pado.inflow.payroll.query.dto.PeriodicPayDTO;
import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
import com.pado.inflow.payroll.query.dto.SummaryDTO;
import com.pado.inflow.payroll.query.repository.SeverancePayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SeverancePayServiceImpl implements SeverancePayService{

    private final SeverancePayMapper severancePayMapper;

    @Autowired
    public SeverancePayServiceImpl(SeverancePayMapper severancePayMapper) {
        this.severancePayMapper = severancePayMapper;
    }

    @Override
    public SeverancePayDTO calculateSeverancePay(Long employeeId, LocalDate referenceDate) {

        SeverancePayDTO severancePayDTO = severancePayMapper.getSeveranceData(employeeId, referenceDate);

        if (severancePayDTO == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        // 3개월 간 총 근무일과 급여 계산
        int totalDays = severancePayDTO.getRecentThreeMonthsPay().stream()
                .mapToInt(PeriodicPayDTO::getWorkingDays).sum();
        int totalAmount = severancePayDTO.getRecentThreeMonthsPay().stream()
                .mapToInt(dto -> dto.getBasePay() + dto.getExtraPay()).sum();

        // SummaryDTO(총합을 나타내는 필드) 설정
        severancePayDTO.setTotalSummary(new SummaryDTO(totalDays, totalAmount));

        // 평균 임금 계산
        int averageWage = totalAmount / totalDays;

        // 통상 임금 게산
        int hourlyOrdinaryWage = severancePayDTO.getRecentThreeMonthsPay().stream()
                .mapToInt(dto -> dto.getBasePay() / 209).max().orElse(0);
        int dailyordinaryWage = hourlyOrdinaryWage * 8; // 일일 통상 임금

        // 최종 퇴직금 계산
        int sevarancePay = Math.max(averageWage, dailyordinaryWage) * 30 * severancePayDTO.getTotalWorkingDays() / 365;

        // 결과값 dto 매핑
        severancePayDTO.setAverageWage(averageWage);
        severancePayDTO.setOrdinaryWage(dailyordinaryWage);
        severancePayDTO.setSeverancePay(sevarancePay);

        return severancePayDTO;

    }

}
