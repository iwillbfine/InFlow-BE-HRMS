package com.pado.inflow.statistics.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.statistics.query.dto.TaskEvaluationDTO;
import com.pado.inflow.statistics.query.repository.TaskEvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("TEQueryService")
public class TaskEvaluationServiceImpl implements TaskEvaluationService {

    private final TaskEvaluationMapper taskEvaluationMapper;

    @Autowired
    public TaskEvaluationServiceImpl(TaskEvaluationMapper taskEvaluationMapper){
        this.taskEvaluationMapper = taskEvaluationMapper;
    }

    // 연도별 과제평가 통계 조회
    @Override
    public List<TaskEvaluationDTO> getTaskEvaluation(String year) {
        return Optional.ofNullable(taskEvaluationMapper.getYearlyTE(year))
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_TASK));
    }

    // 특정 부서의 과제평가 통계 조회
    @Override
    public List<TaskEvaluationDTO> getDeptYearEmpNums(String deptCode) {
        return Optional.ofNullable(taskEvaluationMapper.getDeptTE(deptCode))
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_TASK));
    }
}
