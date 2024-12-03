package com.pado.inflow.employee.info.query.repository;

import com.pado.inflow.employee.info.query.dto.HomeInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HomeInfoMapper {
    List<HomeInfoDTO> getEvents(Long employeeId, int month);
}
