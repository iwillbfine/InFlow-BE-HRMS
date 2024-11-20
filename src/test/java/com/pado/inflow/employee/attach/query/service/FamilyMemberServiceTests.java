package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FamilyMemberServiceTests {

    static FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberServiceTests(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @DisplayName("전 사원 가족구성원 정보 조회")
    @Test
    public void getFamilyMembers() {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberAll();
        Assertions.assertNotNull(result.get(0));
    }

    @DisplayName("사원 한 명의 가족구성원 정보 조회")
    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    public void getOneFamilyMembers(Long id) {
        List<FamilyMemberDTO> result = familyMemberService.getFamilyMemberOne(id);
        Assertions.assertNotNull(result.get(0));
    }
}