package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.TaskTypeEvalDTO;
import com.pado.inflow.evaluation.query.repository.TaskTypeEvalMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskTypeEvalServiceImpl implements TaskTypeEvalService{

    private final TaskTypeEvalMapper taskTypeEvalMapper;

    public TaskTypeEvalServiceImpl(TaskTypeEvalMapper taskTypeEvalMapper) {
        this.taskTypeEvalMapper = taskTypeEvalMapper;
    }

    @Override
    public List<TaskTypeEvalDTO> findByEvaluationId(Long evaluationId) {

        List<TaskTypeEvalDTO> selectedTaskTypeEvals = taskTypeEvalMapper.getTaskTypeEvalByEvaluationId(evaluationId);

        if (selectedTaskTypeEvals == null || selectedTaskTypeEvals.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK_TYPE_EVAL);
        }
        return selectedTaskTypeEvals;
    }
}
