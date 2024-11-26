package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaluationMapper {
    EvaluationDTO getFinalEvaluationGrade(Long empId, Integer year, String half);

    EvaluationDTO getEvaluationByEvaluationId(Long evaluationId);

    EvaluationDTO getEvaluationByYearAndHalfAndEmpId(Long empId, Integer year, String half);

    List<EvaluationDTO> getEvaluationsByEmpIdAndYearAndHalf(Long empId, Integer year, String half);

    List<EvaluationDTO> getAllEvaluationsByYearAndHalf(Integer year, String half);
}
