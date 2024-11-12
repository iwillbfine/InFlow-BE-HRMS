package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.query.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("health")
    public String healthCheck() {
        return "I am healthy";
    }

    // 전 사원의 가족구성원 조회
    @GetMapping("/")
    public List<FamilyMemberDTO> getAllFamilyMemberList() {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberAll();
        return result;
    }

    // 사원 한 명의 가족구성원 조회(/사원ID)
    @GetMapping("/{employeeId}")
    public List<FamilyMemberDTO> getOneFamilyMemberList(@PathVariable("employeeId") String employeeId) {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberOne(parseLong(employeeId));
        return result;
    }
}
