package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluationMapper {
    EvaluationDTO getFinalEvaluationGrade(Long empId, Integer year, String half);

    EvaluationDTO getEvaluationByEvaluationId(Long evaluationId);
}
