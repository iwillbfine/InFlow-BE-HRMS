package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationMapper;
import org.apache.ibatis.ognl.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private final EvaluationMapper evaluationMapper;

    public EvaluationServiceImpl(EvaluationMapper evaluationMapper) {
        this.evaluationMapper = evaluationMapper;
    }


    @Override
    public EvaluationDTO findEvaluationGrade(Long empId, Integer year, String half) {
        EvaluationDTO finalGrade = evaluationMapper.getFinalEvaluationGrade(empId, year, half);
        if (finalGrade == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_GRADE);
        }
        return finalGrade;
    }

    @Override
    public EvaluationDTO findEvaluationByEvaluationId(Long evaluationId) {
        EvaluationDTO selectedEvaluation = evaluationMapper.getEvaluationByEvaluationId(evaluationId);

        if ( selectedEvaluation == null ) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION);
        }
        return selectedEvaluation;
    }

    @Override
    public EvaluationDTO findEvaluation(Long empId, Integer year, String half) {
        EvaluationDTO selectedEvaluation = evaluationMapper.getEvaluationByYearAndHalfAndEmpId(empId, year, half);

        if ( selectedEvaluation == null ) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION);
        }
        return selectedEvaluation;
    }

    @Override
    public List<EvaluationDTO> findEvaluations(Long empId, Integer year, String half) {
        List<EvaluationDTO> selectedEvaluations = evaluationMapper.getEvaluationsByEmpIdAndYearAndHalf(empId, year, half);

        if (selectedEvaluations == null || selectedEvaluations.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION);
        }

        return selectedEvaluations;
    }
}
