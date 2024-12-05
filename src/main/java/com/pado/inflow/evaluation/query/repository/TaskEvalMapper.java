package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskEvalMapper {
    List<TaskEvalDTO> findTaskEvalList(Long empId, Integer year, String half);

    TaskEvalDTO findTaskEvalByTaskEvalId(Long taskEvalId);

    List<TaskEvalDTO> findTaskEvalListByEvaluationId(Long evaluationId);
}
