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

    // 부서별 과제 항목 조회
    @Override
    public List<TaskItemDTO> findTaskItemByEmpIdAndYearAndHalf(Integer year, String half, Long empId) {

        List<TaskItemDTO> selectedTaskItemList =
                taskItemMapper.findDepartmentTaskItemByEmpIdAndYearAndHalf(year, half, empId);

        if (selectedTaskItemList == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }

        return selectedTaskItemList;
    }

    // 개인 과제 항목 조회
    @Override
    public List<TaskItemDTO> findindividualTaskItemByEmpId(Integer year, String half, Long empId) {
        List<TaskItemDTO> selectedTaskItem = taskItemMapper.findIndividualItemByEmpId(year, half, empId);

        if (selectedTaskItem == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItem;
    }

    // 공통 과제 항목 조회
    @Override
    public List<TaskItemDTO> getCommonTaskItem(Integer year, String half, Long empId) {
        List<TaskItemDTO> selectedTaskItem = taskItemMapper.findCommonTaskItemByYearAndHalf(year, half, empId);
        if (selectedTaskItem == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItem;
    }

    @Override
    public TaskItemDTO findIndividualTaskItemByTaskItemId(Long taskItemId) {
        TaskItemDTO selectedTaskItem = taskItemMapper.findTaskItemByTaskItemId(taskItemId);

        if (selectedTaskItem == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK);
        }
        return selectedTaskItem;
    }


}


