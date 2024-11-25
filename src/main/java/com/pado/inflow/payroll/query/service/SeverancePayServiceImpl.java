package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.query.dto.SeverancePayDetailsDTO;
import com.pado.inflow.payroll.query.dto.TotalWorkingDaysDTO;
import com.pado.inflow.payroll.query.repository.SeverancePayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class SeverancePayServiceImpl implements SeverancePayService {

    private final SeverancePayMapper severancePayMapper;

    @Autowired
    public SeverancePayServiceImpl(SeverancePayMapper severancePayMapper) {
        this.severancePayMapper = severancePayMapper;
    }

    // 총 재직일 수 계산
    @Override
    public TotalWorkingDaysDTO getEstimateWorkingDays(Long employeeId) {
        LocalDate joinDate = severancePayMapper.getJoinDateByEmployeeId(employeeId);

        if (joinDate == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        LocalDate severanceDate = LocalDate.now();

        // 총 재직일 계산
        int totalWorkingDays = (int) ChronoUnit.DAYS.between(joinDate, severanceDate);

        return new TotalWorkingDaysDTO(employeeId, joinDate, severanceDate, totalWorkingDays);
    }

    // 퇴직금 계산
    @Override
    public SeverancePayDetailsDTO calculateSeverancePayDetails(Long employeeId) {

        // 입사일 정보 가져오기
        LocalDate joinDate = severancePayMapper.getJoinDateByEmployeeId(employeeId);
        if (joinDate == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        LocalDate severanceDate = LocalDate.now().minusDays(1);
        LocalDate threeMonthsAgo = severanceDate.minusMonths(3);
        LocalDate oneYearAgo = severanceDate.minusYears(1);

        // 급여 및 비과세 계산 (3개월 전)
        Integer totalSalary = severancePayMapper.getTotalSalary(employeeId, threeMonthsAgo, severanceDate);
        Integer totalNonTaxableSalary = severancePayMapper.getTotalNonTaxableSalary(employeeId, threeMonthsAgo, severanceDate);

        if (totalSalary == null) totalSalary = 0;
        if (totalNonTaxableSalary == null) totalNonTaxableSalary = 0;

        // 연간 상여금 정보
        Integer annualBonus = severancePayMapper.getAnnualBonus(employeeId, oneYearAgo, severanceDate);
        if (annualBonus == null) annualBonus = 0;

        // 연간 연차수당 정보
        Integer leaveAllowanceAddition = severancePayMapper.getLeaveAllowanceAddition(employeeId);
        if (leaveAllowanceAddition == null) leaveAllowanceAddition = 0;

        // 3개월 총 급여 + 상여금 가산 + 연차수당 가산
        int totalEarnings = totalSalary + totalNonTaxableSalary + annualBonus * 3 / 12 + leaveAllowanceAddition * 3 / 12;

        // 3개월 간 총 일수
        int daysInThreeMonths = (int) ChronoUnit.DAYS.between(threeMonthsAgo, severanceDate);

        // 평균 임금 계산
        int averageWage = totalEarnings / daysInThreeMonths;

        // 통상 임금 계산
        Integer monthlySalary = severancePayMapper.getMonthlySalary(employeeId);
        int dailyRegularWage = (monthlySalary != null) ? (monthlySalary / 209) * 8 : 0;

        // 통상 임금과 평균 임금 중 더 큰 쪽으로 퇴직금 계산
        int greaterWage = Math.max(averageWage, dailyRegularWage);

        // 총 재직일 수 조회
        long totalWorkingDays = severancePayMapper.getTotalWorkingDays(employeeId);

        // 총 퇴직금 계산
        int severancePay = (int) Math.floor(greaterWage * 30L * totalWorkingDays / 365.0);

        // dto 반환
        return new SeverancePayDetailsDTO(
                totalSalary,
                totalNonTaxableSalary,
                annualBonus * 3 / 12,
                leaveAllowanceAddition * 3 / 12,
                severancePay,
                threeMonthsAgo,
                severanceDate,
                joinDate
        );
    }
}



