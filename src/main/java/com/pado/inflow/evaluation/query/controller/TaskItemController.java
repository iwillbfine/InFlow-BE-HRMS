package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import com.pado.inflow.evaluation.query.service.TaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryTaskItemController")
@RequestMapping("/api/evaluation/taskItem")
public class TaskItemController {

    @Autowired
    private final TaskItemService taskItemService;

    public TaskItemController(TaskItemService taskItemService) {
        this.taskItemService = taskItemService;
    }

    // 부서별 과제 항목 조회
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

    // 부서별 과제 항목 조회
    @GetMapping("/individualTask")
    public ResponseDTO<?> findindividualTaskItemByEmpId(
            @RequestParam( value = "year")  Integer year
           ,@RequestParam( value = "half")  String half
           ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskItemDTO> TaskItemList =
                taskItemService.findindividualTaskItemByEmpId(year, half, empId);
        return ResponseDTO.ok(TaskItemList);
    }


}

/*
부서장 작성 여부가 Y인 경우 2023 및 1st에 해당하면서 부서장의 id를 통해 얻은 부서코드와 해당 과제의 부서코드가 일치하는지 여부를 따진다면 부서별 과제만 선택적으로 select하는게 가능하다고 생각해 맞아 ?

또한 api를 하나 더 만들어서
개인 사원별 Id값이 있으니 id값과 더불어 해당 과제의 정책을 조인하여 2023 1st등 입력된 파라미터 값에 해당하는 과제만 조회할 수 있잖아 맞지 ?
* */
