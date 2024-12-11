package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.dto.DisciplineRewardDTO;
import com.pado.inflow.employee.attach.query.service.DisciplineRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("DRQueryController")
@RequestMapping("/api/employees/discipline-rewards")
public class DisciplineRewardController {
    private final DisciplineRewardService disciplineRewardService;

    @Autowired
    public DisciplineRewardController(DisciplineRewardService disciplineRewardService) {
        this.disciplineRewardService = disciplineRewardService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm OK!!";
    }

    // 사원의 포상 및 징계 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO<List<DisciplineRewardDTO>> getDisciplineRewardAll(@PathVariable("employeeId") String employeeId) {
        List<DisciplineRewardDTO> result = disciplineRewardService.getDisciplineRewardsAll(employeeId);
        return ResponseDTO.ok(result);
    }

}
