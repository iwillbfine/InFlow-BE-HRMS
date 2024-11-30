package com.pado.inflow.vacation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationRequestDTO;
import com.pado.inflow.vacation.query.repository.VacationRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class VacationRequestServiceImpl implements VacationRequestService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final VacationRequestMapper vacationRequestMapper;

    @Autowired
    public VacationRequestServiceImpl(VacationRequestMapper vacationRequestMapper) {
        this.vacationRequestMapper = vacationRequestMapper;
    }

    // 사원별 휴가 신청 내역 미리보기 조회
    @Override
    public List<VacationRequestDTO> findVacationRequestPreviewsByEmployeeId(Long employeeId) {
        List<VacationRequestDTO> vacationRequestPreviews =
                vacationRequestMapper.findVacationRequestPreviewsByEmployeeId(employeeId);

        if (vacationRequestPreviews == null || vacationRequestPreviews.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION_REQUEST);
        }

        return vacationRequestPreviews;
    }

    // 사원별 휴가 신청 내역 전체 조회
    @Override
    public PageDTO<VacationRequestDTO> findVacationRequestsByEmployeeId(Long employeeId, Integer pageNo, String date) {
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

        Integer totalElements = vacationRequestMapper.getTotalVacationRequestsByEmployeeId(employeeId, startOfMonth);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION_REQUEST);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<VacationRequestDTO> vacationRequests =
                vacationRequestMapper.findVacationRequestsByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset, startOfMonth);
        if (vacationRequests == null || vacationRequests.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION_REQUEST);
        }

        return new PageDTO<>(vacationRequests, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 당일기준 휴가중인지 검사
    @Override
    public Boolean isVacationNow(Long employeeId) {
        return vacationRequestMapper.findTodayVacationByEmployeeId(employeeId) != null;
    }

}
