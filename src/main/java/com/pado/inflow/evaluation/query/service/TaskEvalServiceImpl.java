package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.repository.TaskEvalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskEvalServiceImpl implements TaskEvalService{

    @Autowired
    private final TaskEvalMapper taskEvalMapper;

    public TaskEvalServiceImpl(TaskEvalMapper taskEvalMapper) {
        this.taskEvalMapper = taskEvalMapper;
    }

    @Override
    public List<TaskEvalDTO> findTaskEval(Long empId, Integer year, String half) {
        List<TaskEvalDTO> selectedTaskEvalList = taskEvalMapper.findTaskEvalList(empId, year, half);
        if ( selectedTaskEvalList == null || selectedTaskEvalList.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION);
        }
        return selectedTaskEvalList;
    }

    @Override
    public TaskEvalDTO getTaskEvalItem(Long taskEvalId) {
        TaskEvalDTO selectedTaskEval = taskEvalMapper.findTaskEvalByTaskEvalId(taskEvalId);
        return selectedTaskEval;
    }
}
