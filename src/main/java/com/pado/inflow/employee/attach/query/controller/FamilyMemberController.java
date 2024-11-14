package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.query.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController("FMQueryController")
@RequestMapping("/api/employee/family-members")
public class FamilyMemberController {

    static FamilyMemberService familyMemberService;

    @Autowired
    public void setFamilyMemberService(FamilyMemberService familyMemberService) {
        FamilyMemberController.familyMemberService = familyMemberService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I am healthy";
    }

    // 전 사원의 가구원 조회
    @GetMapping("/")
    public ResponseEntity getAllFamilyMemberList() {
        try {
            List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberAll();
            return result.size()>0 ? ResponseEntity.ok(result) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회 결과 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가구원 조회 실패");
        }
    }

    // 사원 한 명의 가구원 조회(/사원ID)
    @GetMapping("/{employeeId}")
    public ResponseEntity getOneFamilyMemberList(@PathVariable("employeeId") String employeeId) {
        try {
            List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberOne(parseLong(employeeId));
            return result.size()>0 ? ResponseEntity.ok(result) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회 결과 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가구원 조회 실패");
        }
    }
}
