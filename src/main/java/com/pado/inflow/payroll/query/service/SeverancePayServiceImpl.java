package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.query.dto.EmployeeInfoDTO;
import com.pado.inflow.payroll.query.dto.PeriodicPayDTO;
import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
import com.pado.inflow.payroll.query.dto.TotalWorkingDaysDTO;
import com.pado.inflow.payroll.query.repository.SeverancePayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    // 퇴직금 계산 서비스
    @Override
    public SeverancePayDTO calculateSeverancePay(Long employeeId) {
        // 직원 기본 정보 조회
        EmployeeInfoDTO employeeInfo = severancePayMapper.getEmployeeInfo(employeeId);
        if (employeeInfo == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        // 입사일 별도 조회
        LocalDate joinDate = severancePayMapper.getJoinDateByEmployeeId(employeeId);
        if (joinDate == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // 퇴사일 전날 설정
        LocalDate severanceDate = LocalDate.now().minusDays(1);
        LocalDate threeMonthsAgo = severanceDate.minusMonths(3);

        // 최근 3개월 급여 내역 조회
        List<PeriodicPayDTO> periodicPayDTOS = severancePayMapper.getLastThreeMonthsPay(employeeId, severanceDate);
        if (periodicPayDTOS == null || periodicPayDTOS.isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // 급여 비율 계산
        periodicPayDTOS.forEach(dto -> {
            LocalDate adjustedStartDate = dto.getStartDate().isBefore(threeMonthsAgo) ? threeMonthsAgo : dto.getStartDate();
            LocalDate adjustedEndDate = dto.getEndDate().isAfter(severanceDate) ? severanceDate : dto.getEndDate();

            int actualDaysInPeriod = (int) ChronoUnit.DAYS.between(adjustedStartDate, adjustedEndDate) + 1;
            int totalDaysInMonth = dto.getDaysInPeriod();

            double dailyBasicSalary = (double) dto.getBasicSalary() / totalDaysInMonth;
            double dailyAllowance = (double) dto.getAllowance() / totalDaysInMonth;

            dto.setDaysInPeriod(actualDaysInPeriod);
            dto.setBasicSalary((int) Math.floor(dailyBasicSalary * actualDaysInPeriod));
            dto.setAllowance((int) Math.floor(dailyAllowance * actualDaysInPeriod));
        });

        // 평균 임금 계산
        int averageWage = calculateAverageWage(employeeInfo, periodicPayDTOS);

        // 통상 임금 계산
        int dailyRegularWage = calculateDailyRegularWage(employeeInfo);

        // 퇴직금 계산
        int greaterWage = Math.max(averageWage, dailyRegularWage);
        long totalWorkingDays = ChronoUnit.DAYS.between(joinDate, severanceDate);
        int severancePay = (int) Math.floor(greaterWage * 30L * totalWorkingDays / 365.0);

        return new SeverancePayDTO(severancePay, periodicPayDTOS);
    }

    // 평균 임금 계산
    private int calculateAverageWage(EmployeeInfoDTO employeeInfo, List<PeriodicPayDTO> periodicPayDTOS) {
        double totalBasicSalary = 0.0;
        double totalAllowance = 0.0;
        double totalDays = 0.0;

        for (PeriodicPayDTO payDTO : periodicPayDTOS) {
            double dailyBasicSalary = (double) payDTO.getBasicSalary() / payDTO.getDaysInPeriod();
            double dailyAllowance = (double) payDTO.getAllowance() / payDTO.getDaysInPeriod();

            totalBasicSalary += dailyBasicSalary * payDTO.getDaysInPeriod();
            totalAllowance += dailyAllowance * payDTO.getDaysInPeriod();
            totalDays += payDTO.getDaysInPeriod();
        }

        // 상여금 및 미사용 연차수당 추가 계산
        double annualBonus = employeeInfo.getAnnualBonus() * 3.0 / 12.0; // 3개월 비율
        double unusedLeavePay = employeeInfo.getUnusedLeavePayPerDay() * employeeInfo.getUnusedLeaveDays() * 3.0 / 12.0;

        double average = (totalBasicSalary + totalAllowance + annualBonus + unusedLeavePay) / totalDays;

        return (int) Math.floor(average); // 소수점 이하 버림
    }


    // 통상 임금 계산
    private int calculateDailyRegularWage(EmployeeInfoDTO employeeInfo) {
        double hourlyRegularWage = employeeInfo.getMonthlySalary() / 209.0; // 월 기준 근로시간 209시간
        double dailyRegularWage = hourlyRegularWage * 8;

        return (int) Math.floor(dailyRegularWage); // 소수점 이하 버림
    }
}



