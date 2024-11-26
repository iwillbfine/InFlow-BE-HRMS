package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.TaskTypeEvalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskTypeEvalMapper {
    List<TaskTypeEvalDTO> getTaskTypeEvalByEvaluationId(Long evaluationId);
}
