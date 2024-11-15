package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.employee.attach.command.application.service.CareerService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/add")
    public ResponseEntity addCareer(@RequestBody List<CareerDTO> careers) {
        List<Career> result = careerService.addCareers(careers);
        return result.size()>0 ? ResponseEntity.ok(result) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("경력정보 저장 실패");
    }

    // 사원의 경력정보 수정
    @PostMapping("/modify")
    public ResponseEntity modifyCareer(@RequestBody List<CareerDTO> careers) {
        List<Career> result = careerService.modifyCareers(careers);
        return result.size()>0 ? ResponseEntity.ok(result) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("경력정보 수정 실패");
    }

    // 사원의 경력정보 삭제
    @DeleteMapping("/delete")
    public ResponseEntity deleteCareer(@RequestBody List<Long> careers) {
        Boolean result = careerService.deleteCareers(careers);
        return result ? ResponseEntity.ok("경력정보 삭제 완료") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("경력정보 삭제 실패");
    }
}
