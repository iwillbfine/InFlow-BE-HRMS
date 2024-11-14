package com.pado.inflow.attendance.query.repository;

import com.pado.inflow.attendance.query.dto.AttendanceRequestTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceRequestTypeMapper {

    // 근태 신청 유형 전체 조회
    List<AttendanceRequestTypeDTO> findAttendanceRequestTypes();

}
