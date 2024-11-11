package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.employee.attach.command.application.service.FamilyMemberService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("FMCommandController")
@RequestMapping("employees/family-member")
public class FamilyMemberController {
    private FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody FamilyMemberDTO familyMember) {
        Boolean result = familyMemberService.insertFamilyMembers(familyMember);
        
        return ResponseEntity.ok(result);
    }
}
