package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.query.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController("FMQueryController")
@RequestMapping("/api/employees/family-members")
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
    @GetMapping
    public ResponseDTO<List<FamilyMemberDTO>> getAllFamilyMemberList() {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberAll(null);
        return ResponseDTO.ok(result);
    }

    // 사원 한 명의 가구원 조회(/사원ID)
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<FamilyMemberDTO>> getOneFamilyMemberList(@PathVariable("employeeId") String employeeId) {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberAll(employeeId);
        return ResponseEntity.ok(result);
    }

    // 가구원 관계 조회
    @GetMapping("/relationships")
    public ResponseDTO<List<HashMap<String, String>>> getRelationshipName() {
        List<HashMap<String, String>> result = familyMemberService.getRelationshipNames();
        return ResponseDTO.ok(result);
    }
}
