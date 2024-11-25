package com.pado.inflow.evaluation.command.application.config.schedule.service;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import com.pado.inflow.employee.info.query.repository.EmployeeMapper;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEvalEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationPolicyRepository;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskTypeEvalRepository;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class EvaluationScheduleServiceTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationPolicyRepository evaluationPolicyRepository;

    @Autowired
    private TaskTypeEvalRepository taskTypeEvalRepository;

    @Autowired
    private EvaluationPolicyMapper evaluationPolicyMapper;

    @Autowired
    private EvaluationScheduleService evaluationScheduleService;

    @BeforeEach
    void setUp() {
        // 사전에 필요하면 DB를 초기화하는 작업 추가 가능
    }

    @Test
    @DisplayName("스케쥴러를 통한 평가 및 평가정책별 평가 테이블 생성 테스트 - 성공 ")
    void testInitializeEvaluation() {

        EvaluationPolicyEntity policy1 = EvaluationPolicyEntity.builder()
                .evaluationPolicyId(101L)
                .year(2024)
                .half("1st")
                .taskTypeId(1L)
                .policyRegisterId(1L) // employee1의 ID로 설정
                .policyDescription("Annual Performance Evaluation")
                .startDate(LocalDateTime.of(2024, 1, 1, 0, 0))
                .endDate(LocalDateTime.of(2024, 6, 30, 23, 59))
                .minRelEvalCount(20L)
                .modifiableDate(LocalDateTime.of(2024, 5, 1, 0, 0))
                .taskRatio(0.6)
                .createdAt(LocalDateTime.now())
                .build();

        EvaluationPolicyEntity policy2 = EvaluationPolicyEntity.builder()
                .evaluationPolicyId(102L)
                .year(2024)
                .half("1st")
                .taskTypeId(2L)
                .policyRegisterId(2L)
                .policyDescription("Mid-year Evaluation")
                .startDate(LocalDateTime.of(2024, 7, 1, 0, 0))
                .endDate(LocalDateTime.of(2024, 12, 31, 23, 59))
                .minRelEvalCount(20L)
                .modifiableDate(LocalDateTime.of(2024, 11, 1, 0, 0))
                .taskRatio(0.4)
                .createdAt(LocalDateTime.now())
                .build();

        List<EvaluationPolicyEntity> policies = Arrays.asList(policy1, policy2);

        // 3. 실제 데이터 삽입 - EvaluationPolicy 테이블
        evaluationPolicyRepository.saveAll(policies);

        // 4. 서비스 메서드 호출
        evaluationScheduleService.initializeEvaluation(2024, "1st");

    }
}
