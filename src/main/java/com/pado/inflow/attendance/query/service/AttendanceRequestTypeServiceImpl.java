package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.AttendanceRequestTypeDTO;
import com.pado.inflow.attendance.query.repository.AttendanceRequestTypeMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRequestTypeServiceImpl implements AttendanceRequestTypeService {

    private final AttendanceRequestTypeMapper attendanceRequestTypeMapper;

    @Autowired
    public AttendanceRequestTypeServiceImpl(AttendanceRequestTypeMapper attendanceRequestTypeMapper) {
        this.attendanceRequestTypeMapper = attendanceRequestTypeMapper;
    }

    // 근태 신청 유형 전체 조회
    @Override
    public List<AttendanceRequestTypeDTO> findAttendanceRequestTypes() {
        List<AttendanceRequestTypeDTO> attendanceRequestTypes = attendanceRequestTypeMapper.findAttendanceRequestTypes();
        if (attendanceRequestTypes == null || attendanceRequestTypes.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_ATTENDANCE_REQUEST_TYPE);
        }
        return attendanceRequestTypes;
    }

}
