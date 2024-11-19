package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskItemMapper {

    List<TaskItemDTO> findDepartmentTaskItemByEmpIdAndYearAndHalf(Integer year, String half, Long empId);

    List<TaskItemDTO> findIndividualItemByEmpId(Integer year, String half, Long empId);

    List<TaskItemDTO> findCommonTaskItemByYearAndHalf(Integer year, String half, Long empId);

    TaskItemDTO findTaskItemByTaskItemId(Long taskItemId);
}
