package com.pado.inflow.employee.info.query.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseContractDTO;
import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.dto.response.EmployeeDetailResponse;
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

    //  // 설명.4. ID로 상세 정보 조회
    EmployeeDetailResponse findEmployeeDetailById(@Param("employeeId") Long employeeId);


    //설명.5. 재직증명서 발급 데이터 조회
    EmploymentCertificateResponse getEmploymentCertificateInfo(Long employeeId);

    //설명.6.근로계약서 발급 데이터 조회
    EmploymentContractResponse getEmploymentContractInfo(@Param("employeeId") Long employeeId);

    //설명.6.1. 비정기 수당 데이터 데이터 조회
    List<IrregularAllowanceDTO> getIrregularAllowances();

    //설명.7. 비밀 유지 서약서 발급 데이터 조회
    ResponseSecurityAgreementResponse getSecurityAgreementInfo(@Param("employeeId") Long employeeId);

    // 설명.8. 사원별 계약서 리스트 조회
    List<ResponseContractDTO> getContractListByEmployeeId(@Param("employeeId") Long employeeId);

    // 설명.9. 계약서 단건 조회
    ResponseContractDTO findContractByContractId(@Param("contractId") Long contractId);

    // 설명.10. 여러 사번으로 사원들 조회
    List<EmployeeDTO> findEmployeesByEmployeeNumbers(List<String> employeeNumbers);

}
