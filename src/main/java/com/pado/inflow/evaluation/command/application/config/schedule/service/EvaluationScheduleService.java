package com.pado.inflow.evaluation.command.application.config.schedule.service;

import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.repository.EmployeeMapper;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEvalEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationPolicyRepository;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskTypeEvalRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class EvaluationScheduleService {

    private final EmployeeMapper employeeMapper;
    private final EvaluationRepository evaluationRepository;
    private final EvaluationPolicyRepository evaluationPolicyRepository;
    private final TaskTypeEvalRepository taskTypeEvalRepository;
    private final EvaluationPolicyMapper evaluationPolicyMapper;

    public EvaluationScheduleService(EmployeeMapper employeeMapper, EvaluationRepository evaluationRepository, EvaluationPolicyRepository evaluationPolicyRepository, TaskTypeEvalRepository taskTypeEvalRepository, EvaluationPolicyMapper evaluationPolicyMapper) {
        this.employeeMapper = employeeMapper;
        this.evaluationRepository = evaluationRepository;
        this.evaluationPolicyRepository = evaluationPolicyRepository;
        this.taskTypeEvalRepository = taskTypeEvalRepository;
        this.evaluationPolicyMapper = evaluationPolicyMapper;
    }

    public void initializeEvaluation(int year, String half) {

        // 1. 활성 직원 조회
        List<EmployeeDTO> employees = employeeMapper.findAllEmployees();

        // 2. 연도와 반기로 평가정책 리스트 조회
        List<EvaluationPolicyDTO> policies = evaluationPolicyMapper.findPolicyByYearAndHalf(year, half);

        // 3. 평가 테이블 생성 - 각 직원마다 자기평가와 리더평가 생성
        List<EvaluationEntity> evaluations = employees.stream()
                .flatMap(emp -> Stream.of(
                        createEvaluation(year, half, emp, "자기평가"),
                        createEvaluation(year, half, emp, "리더평가")
                ))
                .collect(Collectors.toList());

        // 4. 평가 엔티티 일괄 저장
        evaluationRepository.saveAll(evaluations);

        // 5. Task_Type_Eval 테이블 생성
        List<TaskTypeEvalEntity> taskTypeEvals = evaluations.stream()
                .flatMap(evaluation -> policies.stream()
                        .map(policy -> createTaskTypeEval(evaluation, policy))
                )
                .collect(Collectors.toList());

        // 6. Task_Type_Eval 엔티티 일괄 저장
        taskTypeEvalRepository.saveAll(taskTypeEvals);
    }

    // 자기평가와 리더평가를 구분하여 평가 생성
    private EvaluationEntity createEvaluation(int year, String half, EmployeeDTO emp, String evaluationType) {
        return EvaluationEntity.builder()
                .evaluationType(evaluationType)
                .finScore(0.0)
                .finGrade("N/A")
                .year(year)
                .half(half)
                .employeeId(emp.getEmployeeId())
                .createdAt(LocalDateTime.now())
                .evaluatorId(evaluationType.equals("자기평가") ? emp.getEmployeeId() : 1L) // 자기평가일 경우 employeeId와 evaluatorId 동일, 리더평가는 어드민으로 초깃값 설정
                .build();
    }

    // Task_Type_Eval 생성
    private TaskTypeEvalEntity createTaskTypeEval(EvaluationEntity evaluation, EvaluationPolicyDTO policy) {
        return TaskTypeEvalEntity.builder()
                .evaluationId(evaluation.getEvaluationId())
                .evaluationPolicyId(policy.getEvaluationPolicyId())
                .taskTypeTotalScore(0.0)
                .createdAt(LocalDateTime.now())
                .build();
    }
}