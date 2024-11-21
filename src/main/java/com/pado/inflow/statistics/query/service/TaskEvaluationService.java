package com.pado.inflow.statistics.query.service;

import com.pado.inflow.statistics.query.dto.TaskEvaluationDTO;

import java.util.List;

public interface TaskEvaluationService {

    // 연도별 과제평가 통계 조회
    List<TaskEvaluationDTO> getTaskEvaluation(String year);

    // 특정 부서의 과제평가 통계 조회
    List<TaskEvaluationDTO> getDeptYearEmpNums(String deptCode);
}
