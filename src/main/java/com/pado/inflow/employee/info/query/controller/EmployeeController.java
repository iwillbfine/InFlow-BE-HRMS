package com.pado.inflow.employee.info.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseContractDTO;
import com.pado.inflow.employee.info.query.dto.request.EmploymentCertificateRequest;
import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.dto.response.EmploymentCertificateResponse;
import com.pado.inflow.employee.info.query.dto.response.EmploymentContractResponse;
import com.pado.inflow.employee.info.query.dto.response.ResponseSecurityAgreementResponse;
import com.pado.inflow.employee.info.query.service.EmployeeQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("employeeQueryController")
@RequestMapping("/api/employees")
public class EmployeeController {
    private final Environment env;
    private final ModelMapper modelMapper;
    private final EmployeeQueryService employeeService;

      @Autowired
    public EmployeeController(Environment env, ModelMapper modelMapper, EmployeeQueryService employeeService) {
        this.env = env;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

    // 1.1. 설명: 사원 리스트 전체 조회
    @GetMapping("/")
    public ResponseDTO<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTO = employeeService.getAllEmployees();
        return ResponseDTO.ok(employeeDTO);
    }

    // 1.2. 설명: 사원 리스트 이름으로 조회
    @GetMapping("/name")
    public ResponseDTO<List<EmployeeDTO>> getEmployeesByName(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByName(name);
        return ResponseDTO.ok(employees);
    }

    // 1.3. 설명: 사원 정보 사번으로 조회
    @GetMapping("/number/{employeeNumber}")
    public ResponseDTO<EmployeeDTO> getEmployeeByNumber(@PathVariable(value = "employeeNumber") String employeeNumber) {
        EmployeeDTO employee = employeeService.getEmployeeByNumber(employeeNumber);
        return ResponseDTO.ok(employee);
    }

    // 1.4. 설명: 사원 정보 id로 조회
    @GetMapping("/id/{employeeId}")
    public ResponseDTO<EmployeeDTO> getEmployeeById(@PathVariable(value = "employeeId") Long employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        return ResponseDTO.ok(employee);
    }

    // 설명. 2. 재직증명서 내용 반환
    @PostMapping("/employment-certificate")
    public ResponseDTO<EmploymentCertificateResponse> getEmploymentCertificate(
            @RequestBody EmploymentCertificateRequest request) {

        // employeeQueryService를 통해 정보 조회
        EmploymentCertificateResponse certificateInfo = employeeService.getEmploymentCertificateInfo(request.getEmployeeId());

        // 용도 추가
        certificateInfo.setPurpose(request.getPurpose());

        return ResponseDTO.ok(certificateInfo);
    }

    // 설명. 3. 근로 계약서 내용 반환
    @GetMapping("/{employeeId}/employment-contract")
    public ResponseDTO<EmploymentContractResponse> getEmploymentContract(
            @PathVariable("employeeId") Long employeeId) {

        // EmployeeQueryService를 통해 근로계약서 정보 조회
        EmploymentContractResponse employmentContract = employeeService.getEmploymentContract(employeeId);

        return ResponseDTO.ok(employmentContract);
    }

    // 설명. 4. 비밀 유지 서약서 내용 반환
    @GetMapping("/{employeeId}/security-agreement")
    public ResponseDTO<ResponseSecurityAgreementResponse> getSecurityAgreement(
            @PathVariable("employeeId") Long employeeId) {

        // EmployeeQueryService를 통해 비밀유지서약서 정보 조회
        ResponseSecurityAgreementResponse securityAgreement = employeeService.getSecurityAgreement(employeeId);

        return ResponseDTO.ok(securityAgreement);
    }


    // 설명. 5. 서명된 계약서 리스트 조회
    @GetMapping("/{employeeId}/contracts")
    public ResponseDTO<List<ResponseContractDTO>> getContractList(
            @PathVariable("employeeId") Long employeeId) {
        try {
            // 계약서 리스트 조회
            List<ResponseContractDTO> contractList = employeeService.getContractListByEmployeeId(employeeId);
            return ResponseDTO.ok(contractList);
        } catch (IllegalArgumentException e) {
            throw new CommonException(ErrorCode.NOT_FOUND_CONTRACT);
        }
    }

    // 설명. 6. 서명된 계약서 단건 조회
    @GetMapping("/contracts/{contractId}")
    public ResponseDTO<ResponseContractDTO> getContract(
            @PathVariable("contractId") Long contractId) {
        try {
            // 계약서 단건 조회
            ResponseContractDTO contract = employeeService.getContract(contractId);
            return ResponseDTO.ok(contract);
        } catch (IllegalArgumentException e) {
            throw new CommonException(ErrorCode.NOT_FOUND_CONTRACT);
        }
    }

}
