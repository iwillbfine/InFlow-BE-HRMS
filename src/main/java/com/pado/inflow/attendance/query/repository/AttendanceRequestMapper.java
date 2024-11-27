package com.pado.inflow.attendance.query.repository;

import com.pado.inflow.attendance.query.dto.AttendanceRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AttendanceRequestMapper {

    // 사원별 초과근무 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findOvertimeRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 초과근무 신청 내역 전체 조회
    List<AttendanceRequestDTO> findOvertimeRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                                @Param("elementsPerPage") Integer elementsPerPage,
                                                                @Param("offset") Integer offset,
                                                                @Param("date") LocalDate date);

    // 사원별 초과근무 신청 내역 전체 개수 조회
    Integer getTotalOvertimeRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                 @Param("date") LocalDate date);

    // 사원별 재택근무 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findRemoteRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 재택근무 신청 내역 전체 조회
    List<AttendanceRequestDTO> findRemoteRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                              @Param("elementsPerPage") Integer elementsPerPage,
                                                              @Param("offset") Integer offset,
                                                              @Param("date") LocalDate date);

    // 사원별 재택근무 신청 내역 전체 개수 조회
    Integer getTotalRemoteRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                               @Param("date") LocalDate date);

    // 사원별 출장 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findBusinessTripRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 출장 신청 내역 전체 조회
    List<AttendanceRequestDTO> findBusinessTripRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                                    @Param("elementsPerPage") Integer elementsPerPage,
                                                                    @Param("offset") Integer offset,
                                                                    @Param("date") LocalDate date);

    // 사원별 출장 신청 내역 전체 개수 조회
    Integer getTotalBusinessTripRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                     @Param("date") LocalDate date);

    // 사원별 파견 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findDispatchRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 파견 신청 내역 전체 조회
    List<AttendanceRequestDTO> findDispatchRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                                @Param("elementsPerPage") Integer elementsPerPage,
                                                                @Param("offset") Integer offset,
                                                                @Param("date") LocalDate date);

    // 사원별 파견 신청 내역 전체 개수 조회
    Integer getTotalDispatchRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                 @Param("date") LocalDate date);

    // 사원별 휴직 신청 내역 미리보기 조회
    List<AttendanceRequestDTO> findLeaveRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 휴직 신청 내역 전체 조회
    List<AttendanceRequestDTO> findLeaveRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                             @Param("elementsPerPage") Integer elementsPerPage,
                                                             @Param("offset") Integer offset,
                                                             @Param("date") LocalDate date);

    // 사원별 휴직 신청 내역 전체 개수 조회
    Integer getTotalLeaveRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                              @Param("date") LocalDate date);

}
