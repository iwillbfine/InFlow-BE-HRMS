package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.employee.attach.command.application.service.FamilyMemberService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("FMCommandController")
@RequestMapping("/api/employee/family-members")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    // 가족원 등록
    @PostMapping("add")
    public ResponseEntity add(@RequestBody List<FamilyMemberDTO> familyMember) {
        Boolean result = familyMemberService.insertFamilyMembers(familyMember);
        return result ? ResponseEntity.ok(result) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패");
    }
}
