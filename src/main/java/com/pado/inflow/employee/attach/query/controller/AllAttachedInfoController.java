package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.AllAttachedInfoDTO;
import com.pado.inflow.employee.attach.query.service.AllAttachedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("AAIController")
@RequestMapping("/api/employees/attached")
public class AllAttachedInfoController {

    private final AllAttachedInfoService allAttachedInfoService;

    @Autowired
    public AllAttachedInfoController(AllAttachedInfoService allAttachedInfoService) {
        this.allAttachedInfoService = allAttachedInfoService;
    }

    // 전 사원의 등록된 정보(자격증, 경력, ...) 정보 가져오기
    @GetMapping
    public ResponseDTO<List<AllAttachedInfoDTO>> getAttachedInfo() {
        List<AllAttachedInfoDTO> result = allAttachedInfoService.getAllAttechedInfoList(null);
        return ResponseDTO.ok(result);
    }

    // 한 사원의 등록된 정보(자격증, 경력, ...) 정보 가져오기
    @GetMapping("/{empId}")
    public ResponseDTO<List<AllAttachedInfoDTO>> getAttachedInfoOne(@PathVariable("empId") String empId) {
        List<AllAttachedInfoDTO> result = allAttachedInfoService.getAllAttechedInfoList(empId);
        return ResponseDTO.ok(result);
    }

    // 부서별 사원의 등록된 정보(자격증, 경력, ...) 정보 가져오기
    @GetMapping("/department/{deptCode}")
    public ResponseDTO<List<AllAttachedInfoDTO>> getAttachedInfo(@PathVariable("deptCode") String deptCode) {
        List<AllAttachedInfoDTO> result = allAttachedInfoService.getAllAttechedInfoDept(deptCode);
        return ResponseDTO.ok(result);
    }

}
