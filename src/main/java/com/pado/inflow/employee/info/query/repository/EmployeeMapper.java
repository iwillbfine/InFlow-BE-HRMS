package com.pado.inflow.employee.info.query.repository;

import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.dto.response.EmploymentCertificateResponse;
import com.pado.inflow.employee.info.query.dto.response.EmploymentContractResponse;
import com.pado.inflow.employee.info.query.dto.response.ResponseSecurityAgreementResponse;
import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    // 설명.1. 전체 사원 조회
    List<EmployeeDTO> findAllEmployees();

    // 설명.2. 이름으로 사원 조회
    List<EmployeeDTO> findEmployeesByName(@Param("name") String name);

    // 설명.3. 사번으로 사원 조회
    EmployeeDTO findEmployeeByNumber(@Param("employeeNumber") String employeeNumber);

    // 설명.4. ID로 사원 조회
    EmployeeDTO findEmployeeById(@Param("employeeId") Long employeeId);


    //설명.5. 재직증명서 발급 데이터 조회
    EmploymentCertificateResponse getEmploymentCertificateInfo(String employeeNumber);

    //설명.6.근로계약서 발급 데이터 조회
    EmploymentContractResponse getEmploymentContractInfo(@Param("employeeNumber") String employeeNumber);

    //설명.6.1. 비정기 수당 데이터 데이터 조회
    List<IrregularAllowanceDTO> getIrregularAllowances();

    //설명.7. 비밀 유지 서약서 발급 데이터 조회
    ResponseSecurityAgreementResponse getSecurityAgreementInfo(@Param("employeeNumber") String employeeNumber);

}
