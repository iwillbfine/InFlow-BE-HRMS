package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import com.pado.inflow.evaluation.query.repository.TaskItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskItemServiceImpl implements TaskItemService{

    @Autowired
    private final TaskItemMapper taskItemMapper;

    public TaskItemServiceImpl(TaskItemMapper taskItemMapper) {
        this.taskItemMapper = taskItemMapper;
    }

    // 부서별 과제 항목 리스트 조회
    @Override
    public List<TaskItemDTO> findTaskItemsByEmpIdAndYearAndHalf(Integer year, String half, Long empId) {

        List<TaskItemDTO> selectedTaskItemList =
                taskItemMapper.findDepartmentTaskItemsByEmpIdAndYearAndHalf(year, half, empId);

        if ( selectedTaskItemList == null || selectedTaskItemList.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }

        return selectedTaskItemList;
    }

    // 부서 과제 단건 조회
    @Override
    public TaskItemDTO findTaskItemByTaskItemId(Long taskItemId) {
        TaskItemDTO selectedItem = taskItemMapper.findDepartmentTaskItemByEmpIdAndYearAndHalf(taskItemId);

        if (selectedItem == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }

        return selectedItem;
    }


    // 개인 과제 항목 리스트 조회
    @Override
    public List<TaskItemDTO> findindividualTaskItemByEmpId(Integer year, String half, Long empId) {
        List<TaskItemDTO> selectedTaskItem = taskItemMapper.findIndividualItemByEmpId(year, half, empId);

        if ( selectedTaskItem == null || selectedTaskItem.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItem;
    }

    // 개인 과제 항목 단건 조회
    @Override
    public TaskItemDTO findIndividualTaskItemByTaskItemId(Long taskItemId) {
        TaskItemDTO selectedTaskItem = taskItemMapper.findTaskItemByTaskItemId(taskItemId);

        if (selectedTaskItem == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItem;
    }

    // 공통 과제 항목 리스트 조회
    @Override
    public List<TaskItemDTO> getCommonTaskItem(Integer year, String half, Long empId) {
        List<TaskItemDTO> selectedTaskItem = taskItemMapper.findCommonTaskItemsByYearAndHalf(year, half, empId);
        if ( selectedTaskItem == null || selectedTaskItem.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItem;
    }

    // 공통 과제 항목 조회
    @Override
    public TaskItemDTO findCommonTaskItemByTaskItemId(Long taskItemId) {
        TaskItemDTO selectedItem = taskItemMapper.findCommonTaskItemByTaskItemId(taskItemId);

        if (selectedItem == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedItem;
    }

    @Override
    public List<TaskItemDTO> findTaskItems(Long evaluationPolicyId) {
        List<TaskItemDTO> selectedTaskItems = taskItemMapper.findTaskItemByEvaluationPolicyId(evaluationPolicyId);

        if (selectedTaskItems == null || selectedTaskItems.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItems;
    }

    @Override
    public List<TaskItemDTO> findAllTaskItemsByEmpId(Integer year, String half, Long empId) {

        List<TaskItemDTO> selectedTakItems = taskItemMapper.findAllTaskItemsByEmpIdAndYearAndHalf(year, half, empId);

        if (selectedTakItems == null || selectedTakItems.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTakItems;
    }


}


