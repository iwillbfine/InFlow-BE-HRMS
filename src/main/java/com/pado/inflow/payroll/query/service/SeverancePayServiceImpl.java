package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
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

        // 입사일 및 퇴사일 설정
        LocalDate joinDate = severancePayDTO.getJoinDate();
        LocalDate severanceDate = referenceDate;

        if (joinDate.isAfter(severanceDate)) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // 재직 일수 계산
        Integer totalWorkingDays = calculateTotalWorkingDays(joinDate, severanceDate);
        severancePayDTO.setTotalWorkingDays(totalWorkingDays);

        // 직전 3개월 급여로 평균 임금 계산
        SummaryDTO summary = severancePayDTO.getTotalSummary();
        if (summary != null) {
            int totalEarnings = summary.getTotalAmount();
            int totalDays = summary.getTotalDays();

            int averageWage = totalDays > 0 ? totalWorkingDays / totalDays : 0;
            severancePayDTO.setAverageWage(averageWage);
        }

        // 연간 총 상여금 조회
        Integer totalBouns = severancePayMapper.getTotalBonus(employeeId, referenceDate);

    }

    public int calculateTotalWorkingDays(LocalDate joinDate, LocalDate severanceDate) {
        if (joinDate == null || severanceDate == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        return (int) ChronoUnit.DAYS.between(joinDate, severanceDate);
    }

}
