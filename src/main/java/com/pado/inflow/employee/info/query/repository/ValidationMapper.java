package com.pado.inflow.employee.info.query.repository;

import com.pado.inflow.employee.info.query.dto.response.validate.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ValidationMapper {
    List<PositionDTO> getAllPositions();
    List<RoleDTO> getAllRoles();
    List<DutyDTO> getAllDuties();
    List<DepartmentDTO> getAllDepartments();
    List<AppointmentItemDTO> getAllAppointmentItems();
}