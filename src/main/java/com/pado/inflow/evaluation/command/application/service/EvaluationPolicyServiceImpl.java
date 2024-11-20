package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateEvaluationPolicyResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationPolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CommandEvaluationPolicyService")
public class EvaluationPolicyServiceImpl implements EvaluationPolicyService {

    private final EvaluationPolicyRepository evaluationPolicyRepository;

    public EvaluationPolicyServiceImpl(EvaluationPolicyRepository evaluationPolicyRepository) {
        this.evaluationPolicyRepository = evaluationPolicyRepository;
    }


    @Override
    @Transactional
    public CreateEvaluationPolicyResponseDTO createEvaluationPolicy(
            CreateEvaluationPolicyRequestDTO createEvaluationPolicyRequestDTO) {


        // DTO와 동일한 Year, Half에 해당하는 평가 정책 List
        List<EvaluationPolicyEntity> evaluationPolicyEntityList =
                evaluationPolicyRepository.findEvaluationPolicyByYearAndHalf(
                        createEvaluationPolicyRequestDTO.getYear()
                       ,createEvaluationPolicyRequestDTO.getHalf()
                );
        // DTO로 받은 Year, Half에 대하여 평가 정책에서 동일한 과제 유형이 존재하는지 확인.
        for(EvaluationPolicyEntity policy : evaluationPolicyEntityList) {
            if (policy.getTaskTypeId().equals(createEvaluationPolicyRequestDTO.getTaskTypeId())) {
                throw new CommonException(ErrorCode.ALREADY_EXIST_POLICY_TYPE);
            }
        }

        // 기존 정책들의 반영 비율 합계 계산
        Double totalRatio = 0.0;
        for (EvaluationPolicyEntity policy : evaluationPolicyEntityList) {
            totalRatio += policy.getTaskRatio();
        }

        if (totalRatio + createEvaluationPolicyRequestDTO.getTaskRatio() > 1.0) {
            throw new CommonException(ErrorCode.EXCEED_TOTAL_RATIO);
        }

        // 생성
        EvaluationPolicyEntity createdEvaluationPolicy = createEvaluationPolicyRequestDTO.toEntity();
        evaluationPolicyRepository.save(createdEvaluationPolicy);

        CreateEvaluationPolicyResponseDTO ResponseDTO =
                CreateEvaluationPolicyResponseDTO.EntityToDTO(createdEvaluationPolicy);

        // 생성
        return ResponseDTO;
    }
}
