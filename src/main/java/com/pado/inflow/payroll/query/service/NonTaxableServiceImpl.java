package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.query.dto.NonTaxableDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.repository.NonTaxableMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NonTaxableServiceImpl implements NonTaxableService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final NonTaxableMapper nonTaxableMapper;

    public NonTaxableServiceImpl(final NonTaxableMapper nonTaxableMapper) {
        this.nonTaxableMapper = nonTaxableMapper;
    }

    @Override
    public PageDTO<NonTaxableDTO> findAllNonTaxablePayrolls(Integer pageNo) {
        // 페이지 번호 유효성 검사
        if (pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = nonTaxableMapper.getTotalNonTaxablePayrolls();
        if (totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_NON_TAXABLE);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<NonTaxableDTO> nonTaxableDTOS = nonTaxableMapper.findNonTaxablePayrolls(ELEMENTS_PER_PAGE, offset);
        if (nonTaxableDTOS == null || nonTaxableDTOS.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_NON_TAXABLE);
        }

        return new PageDTO<>(nonTaxableDTOS, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }
}
