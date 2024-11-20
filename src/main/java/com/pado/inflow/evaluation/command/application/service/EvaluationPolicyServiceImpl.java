package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateEvaluationPolicyResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateEvaluationPolicyResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationPolicyRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("CommandEvaluationPolicyService")
public class EvaluationPolicyServiceImpl implements EvaluationPolicyService {

    @Autowired
    private final EvaluationPolicyRepository evaluationPolicyRepository;
    private final EvaluationPolicyMapper evaluationPolicyMapper;

    public EvaluationPolicyServiceImpl(EvaluationPolicyRepository evaluationPolicyRepository, EvaluationPolicyMapper evaluationPolicyMapper) {
        this.evaluationPolicyRepository = evaluationPolicyRepository;
        this.evaluationPolicyMapper = evaluationPolicyMapper;
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

        // 평가 정책 수정 가능 시기는 Now() 보다 미래여야함. ! 관련 검증 로직 추가 필요

        // 평가 시작일은 Now() 보다 미래여야함. 또한 평가 종료일 또한 Now()보단 미래여야함. 또한 평가 종료일은 평가 시작일보다 미래여야함

        // 평가 정책 수정 가능 월 또한 now()보다 과거일 수 없음.

        // 생성
        EvaluationPolicyEntity createdEvaluationPolicy = createEvaluationPolicyRequestDTO.toEntity();
        evaluationPolicyRepository.save(createdEvaluationPolicy);

        CreateEvaluationPolicyResponseDTO ResponseDTO =
                CreateEvaluationPolicyResponseDTO.EntityToDTO(createdEvaluationPolicy);

        // 생성
        return ResponseDTO;
    }

    @Override
    @Transactional
    public UpdateEvaluationPolicyResponseDTO updateEvaluationPolicy(
            UpdateEvaluationPolicyRequestDTO updateEvaluationPolicyRequestDTO
           ,Long evaluationPolicyId
    ) {

        // 기존 정책 조회
        EvaluationPolicyDTO selectedPolicy =
                evaluationPolicyMapper.getEvaluationPolicyByEvaluationPolicyId(evaluationPolicyId);
        if ( selectedPolicy == null ) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION_POLICY);
        }

        selectedPolicy.setStartDate(updateEvaluationPolicyRequestDTO.getStartDate());
        selectedPolicy.setEndDate(updateEvaluationPolicyRequestDTO.getEndDate());
        selectedPolicy.setYear(updateEvaluationPolicyRequestDTO.getYear());
        selectedPolicy.setHalf(updateEvaluationPolicyRequestDTO.getHalf());
        selectedPolicy.setTaskRatio(updateEvaluationPolicyRequestDTO.getTaskRatio());
        selectedPolicy.setMinRelEvalCount(updateEvaluationPolicyRequestDTO.getMinRelEvalCount());
        selectedPolicy.setModifiableDate(updateEvaluationPolicyRequestDTO.getModifiableDate());
        selectedPolicy.setPolicyDescription(updateEvaluationPolicyRequestDTO.getPolicyDescription());


        // 1. 수정 시 같은 년도 및 반기에 동일한 유형의 과제로는 바꿀 수 없음.
        // DTO와 동일한 Year, Half에 해당하는 평가 정책 List
        List<EvaluationPolicyEntity> evaluationPolicyEntityList =
                evaluationPolicyRepository.findEvaluationPolicyByYearAndHalf(
                        selectedPolicy.getYear()
                       ,selectedPolicy.getHalf()
                );
        // DTO로 받은 Year, Half에 대하여 평가 정책에서 동일한 과제 유형이 존재하는지 확인.
        for(EvaluationPolicyEntity policy : evaluationPolicyEntityList) {
            if (policy.getTaskTypeId().equals(selectedPolicy.getTaskTypeId())) {
                throw new CommonException(ErrorCode.ALREADY_EXIST_POLICY_TYPE);
            }
        }

        // 평가 시작일은 Now() 보다 미래여야함.
        if (selectedPolicy.getStartDate().isBefore(LocalDateTime.now())) {
            throw new CommonException(ErrorCode.INVALID_START_DATE);
        }
        // 평가 종료일 또한 Now()보단 미래여야함
        if (selectedPolicy.getEndDate().isBefore(LocalDateTime.now())) {
            throw new CommonException(ErrorCode.INVALID_END_DATE);
        }
        // 또한 평가 종료일은 평가 시작일보다 미래여야함
        if (!selectedPolicy.getEndDate().isAfter(selectedPolicy.getStartDate())) {
            throw new CommonException(ErrorCode.INVALID_DATE_RANGE);
        }
        // 평가 정책 수정 가능 월 또한 now()보다 과거일 수 없으며
        if (selectedPolicy.getModifiableDate().isBefore(LocalDateTime.now())) {
            throw new CommonException(ErrorCode.INVALID_MODIFIABLE_DATE);
        }
        // 평가 정책 시작일보다 미래여야함.
        if (selectedPolicy.getModifiableDate().isBefore(selectedPolicy.getStartDate())) {
            throw new CommonException(ErrorCode.INVALID_MODIFIABLE_DATE_RANGE);
        }

        // 4. 평가 정책 반영 비율 또한 수정 시 합계 1.0을 초과할 수 없음.
        Double totalRatio = 0.0;
        for (EvaluationPolicyEntity policy : evaluationPolicyEntityList) {
            totalRatio += policy.getTaskRatio();
        }
        if (totalRatio + selectedPolicy.getTaskRatio() > 1.0) {
            throw new CommonException(ErrorCode.EXCEED_TOTAL_RATIO);
        }

        // 수정
        EvaluationPolicyEntity updatedEntity = selectedPolicy.toEntity();
        EvaluationPolicyEntity savedEntity = evaluationPolicyRepository.save(updatedEntity);

        UpdateEvaluationPolicyResponseDTO responseDTO = savedEntity.toResponseDTO();

        return responseDTO;
    }
}
