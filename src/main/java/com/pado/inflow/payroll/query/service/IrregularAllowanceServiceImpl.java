package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.repository.IrregularAllowanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrregularAllowanceServiceImpl implements IrregularAllowanceService{

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final IrregularAllowanceMapper irregularAllowanceMapper;

    @Autowired
    public IrregularAllowanceServiceImpl(IrregularAllowanceMapper irregularAllowanceMapper) {
        this.irregularAllowanceMapper = irregularAllowanceMapper;
    }

    // 비정기 수당 항목 전체 조회
    @Override
    public PageDTO<IrregularAllowanceDTO> findAllIrregularAllowances(Integer pageNo) {
        // 페이지 번호 유효성 검사
        if (pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = irregularAllowanceMapper.getTotalIrregularAllowances();
        if (totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_IRREGULAR_ALLOWANCE);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<IrregularAllowanceDTO> irregularAllowances = irregularAllowanceMapper.findAllIrregularAllowances(ELEMENTS_PER_PAGE, offset);
        if (irregularAllowances == null || irregularAllowances.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_IRREGULAR_ALLOWANCE);
        }

        return new PageDTO<>(irregularAllowances, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }
}
