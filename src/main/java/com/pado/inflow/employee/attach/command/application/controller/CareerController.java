package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.CareerService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("CCommandController")
@RequestMapping("/api/employee/career")
public class CareerController {

    private final CareerService careerService;

    @Autowired
    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    // 사원의 경력정보 등록
    @PostMapping
    public ResponseDTO addCareer(@RequestBody List<CareerDTO> careers) {
        List<Career> result = careerService.addCareers(careers);
        return ResponseDTO.ok(result);
    }

    // 사원의 경력정보 수정
    @PutMapping
    public ResponseDTO modifyCareer(@RequestBody List<CareerDTO> careers) {
        List<Career> result = careerService.modifyCareers(careers);
        return ResponseDTO.ok(result);
    }

    // 사원의 경력정보 삭제
    @DeleteMapping
    public ResponseDTO deleteCareer(@RequestBody List<Long> careers) {
        careerService.deleteCareers(careers);
        return ResponseDTO.ok("경력정보 삭제 완료");
    }
}
