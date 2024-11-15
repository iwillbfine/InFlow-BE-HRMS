package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("CQueryController")
@RequestMapping("/api/employee/career")
public class CareerController {

    private final CareerService careerService;

    @Autowired
    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm OK!!";
    }

    // 전 사원의 경력 조회
    @GetMapping("/")
    public ResponseDTO getCareerAll() {
        List<CareerDTO> result = careerService.getCareerAll();
        return ResponseDTO.ok(result);
    }

    // 사원 한 명의 경력 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO getCareerOne(@PathVariable("employeeId") Long employeeId) {
        List<CareerDTO> result = careerService.getCareerOne(employeeId);
        return ResponseDTO.ok(result);
    }
}
