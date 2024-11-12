package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.employee.attach.command.application.service.FamilyMemberService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;
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

    // 가구원 등록
    @PostMapping("add")
    public ResponseEntity addFamilyMembers(@RequestBody List<FamilyMemberDTO> familyMember) {
        List<FamilyMember> result = familyMemberService.insertFamilyMembers(familyMember);
        return result != null ? ResponseEntity.ok(result) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패");
    }

    // 가구원 수정
    @PostMapping("modify")
    public ResponseEntity modifyFamilyMembers(@RequestBody List<FamilyMemberDTO> familyMember) {
        List<FamilyMember> result = familyMemberService.modifyFamilyMembers(familyMember);
        return result != null ? ResponseEntity.ok(result) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 실패");
    }

    // 가구원 삭제
    @PostMapping("delete")
    public ResponseEntity deleteFamilyMember(@RequestBody List<Long> familyMember) {
        Boolean result = familyMemberService.deleteFamilyMember(familyMember);
        return result ? ResponseEntity.ok("삭제 완료") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
    }
}
