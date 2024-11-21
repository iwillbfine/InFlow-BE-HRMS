package com.pado.inflow.statistics.query.repository;

import com.pado.inflow.statistics.query.dto.TaskEvaluationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskEvaluationMapper {

    // 연도별 과제평가 통계 조회
    List<TaskEvaluationDTO> getYearlyTE(String year);

    // 특정 부서의 과제평가 통계 조회
    List<TaskEvaluationDTO> getDeptTE(String deptCode);
}
