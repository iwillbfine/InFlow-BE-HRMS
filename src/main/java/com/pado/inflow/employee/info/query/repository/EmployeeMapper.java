package com.pado.inflow.employee.info.query.repository;

import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {

    // 설명.1. 전체 사원 조회
   List<EmployeeDTO> findAllEmployees();

    // 설명.2. 이름으로 사원 조회
   List<EmployeeDTO> findEmployeesByName(@Param("name") String name);

    // 설명.3. 사번으로 사원 조회
    Optional<EmployeeDTO> findEmployeeByNumber(@Param("employeeNumber") String employeeNumber);

    // 설명.4. ID로 사원 조회
    Optional<EmployeeDTO> findEmployeeById(@Param("employeeId") Long employeeId);
}
