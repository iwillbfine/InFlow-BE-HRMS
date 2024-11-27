package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.AttendanceRequestDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;

import java.util.List;

public interface AttendanceRequestService {

    // 사원별 초과근무 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findOvertimeRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 초과근무 신청 내역 전체 조회
    PageDTO<AttendanceRequestDTO> findOvertimeRequestsByEmployeeId(Long employeeId, Integer pageNo, String date);

    // 사원별 재택근무 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findRemoteRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 재택근무 신청 내역 전체 조회
    PageDTO<AttendanceRequestDTO> findRemoteRequestsByEmployeeId(Long employeeId, Integer pageNo, String date);

    // 사원별 출장 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findBusinessTripRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 출장 신청 내역 전체 조회
    PageDTO<AttendanceRequestDTO> findBusinessTripRequestsByEmployeeId(Long employeeId, Integer pageNo, String date);

    // 사원별 파견 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findDispatchRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 파견 신청 내역 전체 조회
    PageDTO<AttendanceRequestDTO> findDispatchRequestsByEmployeeId(Long employeeId, Integer pageNo, String date);

    // 사원별 휴직 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findLeaveRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 휴직 신청 내역 전체 조회
    PageDTO<AttendanceRequestDTO> findLeaveRequestsByEmployeeId(Long employeeId, Integer pageNo, String date);

    // 사원별 복직 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findReturnRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 복직 신청 내역 전체 조회
    PageDTO<AttendanceRequestDTO> findReturnRequestsByEmployeeId(Long employeeId, Integer pageNo, String date);

}
