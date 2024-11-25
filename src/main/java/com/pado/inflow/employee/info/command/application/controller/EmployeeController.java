package com.pado.inflow.employee.info.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.application.service.EmployeeCommandService;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestUpdateEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseContractDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseEmployeeDTO;
import com.pado.inflow.employee.info.query.service.EmployeeQueryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("employeeCommandController")
@RequestMapping("/api/employees")
public class EmployeeController {

    private final Environment env;
    private final EmployeeCommandService employeeCommandService;


    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${coolsms.api.number}")
    private String fromPhoneNumber;

    @Autowired
    public EmployeeController(Environment env,EmployeeCommandService employeeCommandService) {
        this.env = env;
        this.employeeCommandService=employeeCommandService;
    }

    // 설명. 0. 헬스 체크
    @GetMapping("/health")
    public String status() {
        return "임직원 정보 서비스 서버 헬스 체크 용 API (/api/employee-info) "
                + env.getProperty("local.server.port");
    }

    /* 설명. 1. 사원 등록
        사원 등록시 여러 사원이 등록 가능함. 따라서 요청 바디를 리스트 형식으로 요청
    */
    @PostMapping()
    public ResponseDTO<List<ResponseEmployeeDTO>> registerEmployees(
            @RequestBody List<RequestEmployeeDTO> employeeDTOs) {

        List<ResponseEmployeeDTO> createdEmployees =  employeeCommandService.registerEmployees(employeeDTOs);

        return ResponseDTO.ok(createdEmployees);

    }

    // 설명. 2. 사원 기본 정보 수정
    /* 설명. 2.1 사원 정보 수정 (ID 기준) */
    @PatchMapping("/employee-id/{employeeId}")
    public ResponseDTO<ResponseEmployeeDTO> updateEmployeeById(
            @PathVariable(value = "employeeId") Long employeeId,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone_number", required = false) String phoneNumber,
            @RequestParam(value = "street_address", required = false) String streetAddress,
            @RequestParam(value = "detailed_address", required = false) String detailedAddress,
            @RequestParam(value = "profile_img", required = false) MultipartFile profileImg) {

        ResponseEmployeeDTO updatedEmployee = employeeCommandService.updateEmployeeById(
                employeeId, email, phoneNumber, streetAddress, detailedAddress, profileImg);
        return ResponseDTO.ok(updatedEmployee);
    }


//    /* 설명. 2.2 사원 정보 수정 (사번 기준) */
//    @PatchMapping("/employee-number/{employeeNumber}")
//    public ResponseDTO<ResponseEmployeeDTO> updateEmployeeByEmployeeNumber(
//            @PathVariable(value="employeeNumber") String employeeNumber,
//            @RequestBody RequestUpdateEmployeeDTO updateEmployeeDTO) {
//
//        ResponseEmployeeDTO updatedEmployee = employeeCommandService.updateEmployeeByEmployeeNumber(employeeNumber, updateEmployeeDTO);
//        return ResponseDTO.ok(updatedEmployee);
//    }


    // 설명. 3. 비밀번호 재설정
    @PatchMapping("/{employee_number}/re-password")
    public ResponseDTO<String> updatePassword(
            @PathVariable(value="employee_number") String employeeNumber, // 명시적으로 이름 지정
            @RequestParam("new_password") String newPassword) { // 명시적으로 이름 지정

        employeeCommandService.resetPassword(employeeNumber, newPassword);
        return ResponseDTO.ok("비밀번호가 성공적으로 재설정되었습니다.");
    }


    // 설명. 4. 서명된 계약서 등록
    @PostMapping("/contracts")
    public ResponseDTO<ResponseContractDTO> uploadContract(
            @RequestParam("contract_id") Long contractId,           // 계약서 ID
            @RequestParam("contract_file") MultipartFile file     // 업로드 파일
    ) {
        try {
            // 서비스 호출 및 결과 반환
            ResponseContractDTO response = employeeCommandService.updateContract(contractId, file);
            return ResponseDTO.ok(response);
        } catch (IllegalArgumentException e) {
            // 잘못된 요청 데이터 처리
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        } catch (IOException e) {
            // S3 업로드 실패 처리
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
        }
    }

}