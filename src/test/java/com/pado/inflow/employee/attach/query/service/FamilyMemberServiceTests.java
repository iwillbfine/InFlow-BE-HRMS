package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FamilyMemberServiceTests {

    private final FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberServiceTests(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @DisplayName("전 사원 가족구성원 정보 조회")
    @Test
    public void getFamilyMembers() {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberAll("1");
        Assertions.assertNotNull(result.get(0));
    }

}