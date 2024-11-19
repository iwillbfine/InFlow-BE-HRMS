package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.FamilyMemberService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("FMCommandController")
@RequestMapping("/api/family-members")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    // 가구원 등록
    @PostMapping
    public ResponseDTO<List<FamilyMember>> addFamilyMembers(@RequestBody List<FamilyMemberDTO> familyMember) {
        List<FamilyMember> result = familyMemberService.insertFamilyMembers(familyMember);
        return ResponseDTO.ok(result);
    }

    // 가구원 수정
    @PutMapping
    public ResponseDTO<List<FamilyMember>> modifyFamilyMembers(@RequestBody List<FamilyMemberDTO> familyMember) {
        List<FamilyMember> result = familyMemberService.modifyFamilyMembers(familyMember);
        return ResponseDTO.ok(result);
    }

    // 가구원 삭제
    @DeleteMapping
    public ResponseDTO<String> deleteFamilyMember(@RequestBody List<Long> familyMember) {
        Boolean result = familyMemberService.deleteFamilyMember(familyMember);
        return ResponseDTO.ok("삭제 완료");
    }
}
