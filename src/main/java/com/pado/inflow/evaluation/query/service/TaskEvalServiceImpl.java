package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.repository.TaskEvalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TaskEvalServiceImpl implements TaskEvalService{

    @Autowired
    private final TaskEvalMapper taskEvalMapper;
    private final EvaluationService evaluationService;

    public TaskEvalServiceImpl(TaskEvalMapper taskEvalMapper, EvaluationService evaluationService) {
        this.taskEvalMapper = taskEvalMapper;
        this.evaluationService = evaluationService;
    }

    @Override
    public List<TaskEvalDTO> findTaskEval(Long empId, Integer year, String half) {
        List<TaskEvalDTO> selectedTaskEvalList = taskEvalMapper.findTaskEvalList(empId, year, half);
        if ( selectedTaskEvalList == null || selectedTaskEvalList.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK_EVAL);
        }
        return selectedTaskEvalList;
    }

    @Override
    public TaskEvalDTO getTaskEvalItem(Long taskEvalId) {
        TaskEvalDTO selectedTaskEval = taskEvalMapper.findTaskEvalByTaskEvalId(taskEvalId);
        return selectedTaskEval;
    }

    @Override
    public List<TaskEvalDTO> findTaskEvalsByEvaluationId(Long evaluationId) {
        List<TaskEvalDTO> selectedTaskEvals = taskEvalMapper.findTaskEvalListByEvaluationId(evaluationId);
        if (selectedTaskEvals == null || selectedTaskEvals.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK_EVAL);
        }
        return selectedTaskEvals;
    }

    @Override
    public List<TaskEvalDTO> findIndividualTasks(Integer year, String half, Long empId) {

        // 평가 id 조회
        EvaluationDTO selectedEvaluation = evaluationService.findEvaluation(empId, year, half);

        // 얻어온 평가id로 리스트 조회
        List<TaskEvalDTO> selectedTasks = taskEvalMapper.findIndividualTasksByEvaluationId(selectedEvaluation.getEvaluationId());
        if (selectedTasks == null || selectedTasks.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK_EVAL);
        }

        return selectedTasks;
    }
}
