package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.FamilyMemberService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("FMCommandController")
@RequestMapping("/api/employees/family-members")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    // 가구원 등록
    @PostMapping
    public ResponseDTO<List<FamilyMemberDTO>> addFamilyMembers(@RequestBody List<FamilyMemberDTO> familyMember) {
        List<FamilyMemberDTO> result = familyMemberService.insertFamilyMembers(familyMember);
        return ResponseDTO.ok(result);
    }

    // 가구원 수정
    @PutMapping
    public ResponseDTO<List<FamilyMemberDTO>> modifyFamilyMembers(@RequestBody List<FamilyMemberDTO> familyMember) {
        List<FamilyMemberDTO> result = familyMemberService.modifyFamilyMembers(familyMember);
        return ResponseDTO.ok(result);
    }

    // 가구원 삭제
    @DeleteMapping
    public ResponseDTO<String> deleteFamilyMember(@RequestBody List<Long> familyMember) {
        Boolean result = familyMemberService.deleteFamilyMember(familyMember);
        return ResponseDTO.ok("삭제 완료");
    }
}
