package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.DisciplineRewardService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.DisciplineRewardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("DRCommandController")
@RequestMapping("/api/employees/descipline-rewards")
public class DisciplineRewardController {

    private final DisciplineRewardService disciplineRewardService;

    @Autowired
    public DisciplineRewardController(DisciplineRewardService disciplineRewardService) {
        this.disciplineRewardService = disciplineRewardService;
    }

    // 사원의 포상및징계 등록
    @PostMapping
    public ResponseDTO<List<DisciplineRewardDTO>> addDisciplineRewards(@RequestBody List<DisciplineRewardDTO> drs) {
        List<DisciplineRewardDTO> result = disciplineRewardService.insertDisciplineRewards(drs);
        return ResponseDTO.ok(result);
    }

    // 사원의 포상및징계 수정
    @PutMapping
    public ResponseDTO<List<DisciplineRewardDTO>> modifyDisciplineRewards(@RequestBody List<DisciplineRewardDTO> drs) {
        List<DisciplineRewardDTO> result = disciplineRewardService.modifyDisciplineRewards(drs);
        return ResponseDTO.ok(result);
    }

    // 사원의 포상및징계 삭제
    @DeleteMapping
    public ResponseDTO<String> deleteDisciplineRewards(@RequestBody List<Long> drs) {
        disciplineRewardService.deleteDisciplineRewards(drs);
        return ResponseDTO.ok("포상및징계 삭제 완료");
    }
}
