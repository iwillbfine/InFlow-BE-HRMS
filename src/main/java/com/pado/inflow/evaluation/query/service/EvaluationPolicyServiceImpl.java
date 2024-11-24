package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationPolicyServiceImpl implements EvaluationPolicyService {

    @Autowired
    private final EvaluationPolicyMapper evaluationPolicyMapper;

    public EvaluationPolicyServiceImpl(EvaluationPolicyMapper evaluationPolicyMapper) {
        this.evaluationPolicyMapper = evaluationPolicyMapper;
    }

    @Override
    public List<EvaluationPolicyDTO> findPolicyWithYearAndHalf(Integer year, String half) {
        List<EvaluationPolicyDTO> selectedPolicy =
                evaluationPolicyMapper.findPolicyByYearAndHalf(year, half);

        if ( selectedPolicy == null || selectedPolicy.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION_POLICY);
        }
        return selectedPolicy;
    }

    @Override
    public EvaluationPolicyDTO findPolicyByevaluationPolicyId(Long evaluationPolicyId) {
        EvaluationPolicyDTO selectedPolicy = evaluationPolicyMapper.getEvaluationPolicyByEvaluationPolicyId(evaluationPolicyId);

        if (selectedPolicy == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION_POLICY);
        }
        return selectedPolicy;
    }

    @Override
    public EvaluationPolicyDTO findPolicyByYearAndHalfAndTaskTypeId(Integer year, String half, Long taskTypeId) {
        EvaluationPolicyDTO selectedPolicy =
                evaluationPolicyMapper.findPolicyByYearAndHalfAndTaskTypeId(year, half, taskTypeId);

        if (selectedPolicy == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION_POLICY);
        }
        return selectedPolicy;
    }
}
