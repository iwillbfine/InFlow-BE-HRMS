package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import com.pado.inflow.evaluation.query.service.TaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryTaskItemController")
@RequestMapping("/api/evaluations/taskItem")
public class TaskItemController {

    @Autowired
    private final TaskItemService taskItemService;

    public TaskItemController(TaskItemService taskItemService) {
        this.taskItemService = taskItemService;
    }

    // 부서 과제 항목 리스트 조회
    @GetMapping("/departmentTasks")
    public ResponseDTO<?> findDepartmentTaskItemByEmpId(
            @RequestParam( value = "year")  Integer year
           ,@RequestParam( value = "half")  String half
           ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskItemDTO> TaskItemList =
                taskItemService.findTaskItemsByEmpIdAndYearAndHalf(year, half, empId);
        return ResponseDTO.ok(TaskItemList);
    }

    // 부서 과제 항목 단건 조회
    @GetMapping("/departmentTask/{taskItemId}")
    public ResponseDTO<?> findDepartmentTaskItemByEmpIdAndYearAndHalf(
            @PathVariable( value = "taskItemId") Long taskItemId
    ) {
        TaskItemDTO taskItem = taskItemService.findTaskItemByTaskItemId(taskItemId);
        return ResponseDTO.ok(taskItem);

    }

    // 개인 과제 항목 리스트 조회
    @GetMapping("/individualTasks")
    public ResponseDTO<?> findIndividualTaskItemByEmpId(        // 이름 수정함, 오류 생길 시 수정
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
    public ResponseDTO<?> findIndividualTaskByTaskItemId(
            @PathVariable Long taskItemId
    ) {
        TaskItemDTO taskItem =
                taskItemService.findIndividualTaskItemByTaskItemId(taskItemId);
        return ResponseDTO.ok(taskItem);
    }

    // 공통 과제 항목 리스트 조회
    @GetMapping("/commonTasks")
    public ResponseDTO<?> findCommonTaskItemByEmpId(
            @RequestParam( value = "year") Integer year
           ,@RequestParam( value = "half") String half
           ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskItemDTO> CommonTaskList =
                taskItemService.getCommonTaskItem(year, half, empId);

        return ResponseDTO.ok(CommonTaskList);

    }

    //공통 과제 항목 단건 조회
    @GetMapping("/commonTask/{taskItemId}")
    public ResponseDTO<?> findCommonTaskItemByTaskItemId(
            @PathVariable( value = "taskItemId") Long taskItemId
    ) {
        TaskItemDTO commonTask = taskItemService.findCommonTaskItemByTaskItemId(taskItemId);
        return ResponseDTO.ok(commonTask);
    }

    // 평가 정책Id로 과제 List 조회
    @GetMapping("/TaskItems/{evaluationPolicyId}")
    public ResponseDTO<?> findTaskItemByEvaluationPolicyId(
            @PathVariable( value = "evaluationPolicyId") Long evaluationPolicyId
    ) {
        List<TaskItemDTO> selectedTaskItems = taskItemService.findTaskItems(evaluationPolicyId);
        return ResponseDTO.ok(selectedTaskItems);
    }
}

