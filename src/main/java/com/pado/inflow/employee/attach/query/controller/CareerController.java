package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getCareerAll() {
        try {
            List<CareerDTO> result = careerService.getCareerAll();
            return result.size()>0 ? ResponseEntity.ok(result) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회 결과 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("경력 조회 실패");
        }
    }

    // 사원 한 명의 경력 조회
    @GetMapping("/{employeeId}")
    public ResponseEntity getCareerOne(@PathVariable("employeeId") Long employeeId) {
        try {
            List<CareerDTO> result = careerService.getCareerOne(employeeId);
            return result.size()>0 ? ResponseEntity.ok(result) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회 결과 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("경력 조회 실패");
        }
    }
}
