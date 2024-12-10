//package com.pado.inflow.employee.attach.command.application.service;
//
//import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class FamilyMemberServiceTests {
//
//    private final FamilyMemberService familyMemberService;
//
//    @Autowired
//    public FamilyMemberServiceTests(FamilyMemberService familyMemberService) {
//        this.familyMemberService = familyMemberService;
//    }
//
//    @DisplayName("사원의 가구원 등록")
//    @Test
//    public void addFamilyMemberTest() {
//        List<FamilyMemberDTO> list = new ArrayList<>();
//        list.add(new FamilyMemberDTO("가구원1",
//                LocalDate.of(2024, 1, 1),
//                3L,
//                "FR004"));
//        list.add(new FamilyMemberDTO("가구원2",
//                LocalDate.of(2023, 1, 1),
//                3L,
//                "FR004"));
//
//        assertTrue(familyMemberService.insertFamilyMembers(list).size() > 0);
//    }
//
//    @DisplayName("사원의 가구원 수정")
//    @Test
//    public void modifyFamilyMemberTest() {
//        List<FamilyMemberDTO> list = new ArrayList<>();
//        list.add(new FamilyMemberDTO(1L,
//                "강수지",
//                LocalDate.of(1986,6,15),
//                1L,
//                "FR001"));
//        assertTrue(familyMemberService.modifyFamilyMembers(list).size() > 0);
//    }
//
//    @DisplayName("사원의 가구원 삭제")
//    @Test
//    public void deleteFamilyMemberTest() {
//        assertTrue(familyMemberService.deleteFamilyMember(new ArrayList<>(Arrays.asList(1L, 2L, 3L))));
//    }
//}