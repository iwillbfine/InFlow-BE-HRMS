package com.pado.inflow.employee.attach.command.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FamilyMemberServiceTests {

    private final FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberServiceTests(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @DisplayName("사원의 가구원 등록")
    @Test
    public void addFamilyMemberTest() {

    }

    @DisplayName("사원의 가구원 수정")
    @Test
    public void modifyFamilyMemberTest() {

    }

    @DisplayName("사원의 가구원 삭제")
    @Test
    public void deleteFamilyMemberTest() {

    }
}