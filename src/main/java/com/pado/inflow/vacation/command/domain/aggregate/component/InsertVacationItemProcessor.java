package com.pado.inflow.vacation.command.domain.aggregate.component;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.enums.Gender;
import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationPolicy;
import com.pado.inflow.vacation.command.domain.aggregate.type.ExpirationStatus;
import com.pado.inflow.vacation.command.domain.aggregate.type.VacationPolicyStatus;
import com.pado.inflow.vacation.command.domain.repository.VacationPolicyRepository;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InsertVacationItemProcessor implements ItemProcessor<Employee, List<Vacation>> {

    private final VacationPolicyRepository vacationPolicyRepository;
    private List<VacationPolicy> vacationPolicies;  // 휴가 정책을 저장할 리스트

    @Autowired
    public InsertVacationItemProcessor(VacationPolicyRepository vacationPolicyRepository) {
        this.vacationPolicyRepository = vacationPolicyRepository;
    }

    // 초기화 메소드: 배치가 시작될 때 한 번만 호출
    @PostConstruct
    public void loadVacationPolicies() {
        // 현재 연도에 해당하는 휴가 정책을 한 번만 로드
        vacationPolicies = vacationPolicyRepository.findByYearAndAutoAllocationCycleIsNotNull(LocalDate.now().getYear());
    }

    @Override
    public List<Vacation> process(@Nonnull Employee employee) {
        List<Vacation> vacations = new ArrayList<>();

        // 현재 날짜
        LocalDate currentDate = LocalDate.now();

        // 입사 날짜와의 차이
        long daysBetween = ChronoUnit.DAYS.between(employee.getJoinDate(), currentDate);

        // 각 휴가 정책에 따라 사원에게 휴가 지급
        for (VacationPolicy policy : vacationPolicies) {
            String cron = policy.getAutoAllocationCycle();

            if (isValidDate(cron)) {
                if (policy.getVacationPolicyStatus() == VacationPolicyStatus.WOMAN_ONLY) {
                    // 여성 보건 휴가만 여성에게 지급
                    if (employee.getGender() == Gender.FEMALE)
                        vacations.add(createVacation(policy, employee, LocalDate.now().plusMonths(1).withDayOfMonth(1).atStartOfDay()));
                } else if (policy.getVacationPolicyStatus() == VacationPolicyStatus.ROOKIE){
                    // 근속일이 1년 미만인 경우 매월 연차 1일 발생
                    if (daysBetween < 365)
                        vacations.add(createVacation(policy, employee, LocalDate.of(LocalDate.now().getYear(), 12, 31).atStartOfDay()));
                } else if (policy.getVacationPolicyStatus() == VacationPolicyStatus.LONG_TERM){
                    // 근속일이 5년 이상인 경우 장기 근속 휴가 발생
                    if (daysBetween >= 1825)
                        vacations.add(createVacation(policy, employee, LocalDate.of(LocalDate.now().getYear(), 12, 31).atStartOfDay()));
                } else {
                    // 1년단위로 발생하는 휴가
                    vacations.add(createVacation(policy, employee, LocalDate.of(LocalDate.now().getYear(), 12, 31).atStartOfDay()));
                }
            }
        }

        return vacations;  // 여러 개의 휴가 객체를 반환
    }

    // 중복 코드를 제거한 vacation 객체 생성 메서드
    private Vacation createVacation(VacationPolicy policy, Employee employee, LocalDateTime expirationDate) {
        Vacation vacation = new Vacation();
        vacation.setVacationName(policy.getVacationPolicyName());
        vacation.setVacationLeft(policy.getAllocationDays());
        vacation.setVacationUsed(0L);
        vacation.setCreatedAt(LocalDateTime.now().withNano(0));
        vacation.setExpiredAt(expirationDate);
        vacation.setExpirationStatus(ExpirationStatus.N);
        vacation.setEmployeeId(employee.getEmployeeId());
        vacation.setVacationPolicyId(policy.getVacationPolicyId());
        vacation.setVacationTypeId(policy.getVacationTypeId());
        return vacation;
    }

    private boolean isValidDate(String cron) {
        // 공백을 기준으로 크론 표현식 나누기
        List<String> cronParts = new ArrayList<>(Arrays.stream(cron.split(" ")).toList());

        // 크론 표현식이 6개 필드를 가져야 함
        if (cronParts.size() < 6) {
            while (cronParts.size() < 6) {
                cronParts.add(0, "0"); // 필드가 6개가 될 때까지 "0"을 추가
            }
        } else if (cronParts.size() > 6) {
            return false; // 크론 표현식이 6개 필드를 초과하면 잘못된 표현식
        }

        // 현재 날짜 가져오기
        LocalDate now = LocalDate.now();
        String[] dateParts = {
                "0", // 초 (기본값 0)
                "0", // 분 (기본값 0)
                "0", // 시 (기본값 0)
                String.valueOf(now.getDayOfMonth()),   // 일
                String.valueOf(now.getMonthValue()),  // 월
                String.valueOf(now.getDayOfWeek().getValue() % 7)  // 요일 (1=월요일, 7=일요일 -> 0=일요일, 6=토요일)
        };

        for (int i = cronParts.size()-1; i >= 3; i--) {
            if (cronParts.get(i).equals("*")) continue;
            else if (!cronParts.get(i).equals(dateParts[i])) return false;
        }

        return true;
    }

}
