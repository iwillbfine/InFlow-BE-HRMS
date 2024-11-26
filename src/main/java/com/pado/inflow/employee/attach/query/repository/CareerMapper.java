package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.dto.EducationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CareerMapper {

    // 사원의 경력 조회
    List<CareerDTO> getAllCareers(String employeeId);
}
