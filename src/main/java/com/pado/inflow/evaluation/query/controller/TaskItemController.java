package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import com.pado.inflow.evaluation.query.service.TaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryTaskItemController")
@RequestMapping("/api/evaluation/taskItem")
public class TaskItemController {

    @Autowired
    private final TaskItemService taskItemService;

    public TaskItemController(TaskItemService taskItemService) {
        this.taskItemService = taskItemService;
    }

    // 부서 과제 항목 조회
    @GetMapping("/departmentTask")
    public ResponseDTO<?> findDepartmentTaskItemByEmpId(
            @RequestParam( value = "year")  Integer year
           ,@RequestParam( value = "half")  String half
           ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskItemDTO> TaskItemList =
                taskItemService.findTaskItemByEmpIdAndYearAndHalf(year, half, empId);
        return ResponseDTO.ok(TaskItemList);
    }

    // 개인 과제 항목 리스트 조회
    @GetMapping("/individualTasks")
    public ResponseDTO<?> findindividualTaskItemByEmpId(
            @RequestParam( value = "year")  Integer year
           ,@RequestParam( value = "half")  String half
           ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskItemDTO> TaskItemList =
                taskItemService.findindividualTaskItemByEmpId(year, half, empId);
        return ResponseDTO.ok(TaskItemList);
    }

    // 개인 과제 단건 조회
    @GetMapping("/individualTask/{taskItemId}")
    public ResponseDTO<?> findIndividualTeskByTaskItemId(
            @PathVariable Long taskItemId
    ) {
        TaskItemDTO taskItem = taskItemService.findIndividualTaskItemByTaskItemId(taskItemId);
        return ResponseDTO.ok(taskItem);
    }

    // 공통 과제 조회
    @GetMapping("/commonTask")
    public ResponseDTO<?> findCommonTaskItemByEmpId(
            @RequestParam( value = "year") Integer year
           ,@RequestParam( value = "half") String half
            ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskItemDTO> CommonTaskList = taskItemService.getCommonTaskItem(year, half, empId);

        return ResponseDTO.ok(CommonTaskList);

    }

}

