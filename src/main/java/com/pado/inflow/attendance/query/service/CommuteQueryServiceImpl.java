package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.repository.CommuteMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class CommuteQueryServiceImpl implements CommuteQueryService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final CommuteMapper commuteMapper;

    @Autowired
    public CommuteQueryServiceImpl(CommuteMapper commuteMapper) {
        this.commuteMapper = commuteMapper;
    }

    // 사원별 출퇴근 내역 조회
    @Override
    public PageDTO<CommuteDTO> findCommutesByEmployeeId(Long employeeId, Integer pageNo, String date) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 날짜 형식 유효성 검사 및 변환 (yyyy-MM)
        YearMonth parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            parsedDate = YearMonth.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        LocalDate startOfMonth = parsedDate.atDay(1); // 해당 월의 첫 번째 날

        Integer totalElements = commuteMapper.getTotalCommutesByEmployeeId(employeeId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_COMMUTE);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<CommuteDTO> commutes = commuteMapper.findCommutesByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset, startOfMonth);
        if (commutes == null || commutes.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_COMMUTE);
        }

        return new PageDTO<>(commutes, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
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
