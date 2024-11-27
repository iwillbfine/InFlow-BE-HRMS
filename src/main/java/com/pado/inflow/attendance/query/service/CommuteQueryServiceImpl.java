package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.dto.ResponseCommuteDTO;
import com.pado.inflow.attendance.query.repository.CommuteMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommuteQueryServiceImpl implements CommuteQueryService {

    private final CommuteMapper commuteMapper;

    @Autowired
    public CommuteQueryServiceImpl(CommuteMapper commuteMapper) {
        this.commuteMapper = commuteMapper;
    }

    // 사원별 출퇴근 내역 조회
    @Override
    public List<ResponseCommuteDTO> findCommutesByEmployeeId(Long employeeId, String date) {
        // 날짜 형식 유효성 검사 및 변환 (yyyy-MM)
        YearMonth parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            parsedDate = YearMonth.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        LocalDate startOfMonth = parsedDate.atDay(1); // 해당 월의 첫 번째 날

        List<CommuteDTO> commutes = commuteMapper.findCommutesByEmployeeId(employeeId, startOfMonth);
        if (commutes == null || commutes.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_COMMUTE);
        }

        List<ResponseCommuteDTO> parsedCommutes = new ArrayList<>();
        CommuteDTO overtime = null;

        for (CommuteDTO commute : commutes) {
            // 초과근무인 경우 저장
            if ("Y".equals(commute.getOvertimeStatus())) {
                overtime = commute;
                continue;
            }

            // 초과근무 내역이 있고, 출근 내역과 같은 일자라면 병합
            if (overtime != null && overtime.getStartTime().toLocalDate().equals(commute.getStartTime().toLocalDate())) {
                long overtimeMinutes = Duration.between(overtime.getStartTime(), overtime.getEndTime()).toMinutes();

                ResponseCommuteDTO parsedCommute = ResponseCommuteDTO.builder()
                        .commuteId(commute.getCommuteId())
                        .startTime(commute.getStartTime())
                        .endTime(commute.getEndTime())
                        .remoteStatus(commute.getRemoteStatus())
                        .overtime(overtimeMinutes) // 초과근무 시간 (분 단위)
                        .employeeId(commute.getEmployeeId())
                        .build();

                parsedCommutes.add(parsedCommute);
                overtime = null; // 사용한 초과근무 내역 초기화
                continue;
            }

            // 초과근무가 없는 일반 출근 내역
            ResponseCommuteDTO parsedCommute = ResponseCommuteDTO.builder()
                    .commuteId(commute.getCommuteId())
                    .startTime(commute.getStartTime())
                    .endTime(commute.getEndTime())
                    .remoteStatus(commute.getRemoteStatus())
                    .overtime(null) // 초과근무 없음
                    .employeeId(commute.getEmployeeId())
                    .build();

            parsedCommutes.add(parsedCommute);
        }

        // 초과근무 내역이 남아 있는 경우 비정상적인 데이터이므로 로그 남기기
        if (overtime != null) {
            log.error("사원ID: " + overtime.getEmployeeId()+", 비정상적인 출퇴근 데이터가 존재합니다.");
        }

        return parsedCommutes;
    }

    // 당일 재택 출퇴근 내역 조회
    @Override
    public CommuteDTO findTodayRemoteByEmployeeId(Long employeeId) {
        return commuteMapper.findTodayRemoteByEmployeeId(employeeId);
    }

    // 당일 출퇴근 내역 조회
    @Override
    public CommuteDTO findTodayCommuteByEmployeeId(Long employeeId) {
        return commuteMapper.findTodayCommuteByEmployeeId(employeeId);
    }

}
