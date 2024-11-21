package com.pado.inflow.evaluation.query.service;


import com.pado.inflow.evaluation.query.dto.TaskItemDTO;

import java.util.List;

public interface TaskItemService {
    List<TaskItemDTO> findTaskItemsByEmpIdAndYearAndHalf(Integer year, String half, Long empId);


    List<TaskItemDTO> findindividualTaskItemByEmpId(Integer year, String half, Long empId);

    List<TaskItemDTO> getCommonTaskItem(Integer year, String half, Long empId);

    TaskItemDTO findIndividualTaskItemByTaskItemId(Long taskItemId);

    TaskItemDTO findTaskItemByTaskItemId(Long taskItemId);

    TaskItemDTO findCommonTaskItemByTaskItemId(Long taskItemId);

    List<TaskItemDTO> findTaskItems(Long evaluationPolicyId);
}
