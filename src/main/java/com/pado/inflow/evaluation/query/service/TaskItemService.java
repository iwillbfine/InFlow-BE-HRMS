package com.pado.inflow.evaluation.query.service;


import com.pado.inflow.evaluation.query.dto.TaskItemDTO;

import java.util.List;

public interface TaskItemService {
    List<TaskItemDTO> findTaskItemByEmpIdAndYearAndHalf(Integer year, String half, Long empId);


    List<TaskItemDTO> findindividualTaskItemByEmpId(Integer year, String half, Long empId);
}
