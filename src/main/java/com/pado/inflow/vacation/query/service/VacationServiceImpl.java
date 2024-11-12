package com.pado.inflow.vacation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationDTO;
import com.pado.inflow.vacation.query.repository.VacationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final VacationMapper vacationMapper;

    @Autowired
    public VacationServiceImpl(VacationMapper vacationMapper) {
        this.vacationMapper = vacationMapper;
    }

    // 사원별 잔여 휴가 조회
    @Override
    public PageDTO<VacationDTO> findLeftVacationsByEmployeeId(Long employeeId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = vacationMapper.getTotalLeftVacationsByEmployeeId(employeeId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<VacationDTO> vacations = vacationMapper.findLeftVacationsByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset);
        if (vacations == null || vacations.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION);
        }

        return new PageDTO<>(vacations, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 사원별 사용 휴가 조회
    @Override
    public PageDTO<VacationDTO> findUsedVacationsByEmployeeId(Long employeeId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = vacationMapper.getTotalUsedVacationsByEmployeeId(employeeId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<VacationDTO> vacations = vacationMapper.findUsedVacationsByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset);
        if (vacations == null || vacations.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION);
        }

        return new PageDTO<>(vacations, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

}
