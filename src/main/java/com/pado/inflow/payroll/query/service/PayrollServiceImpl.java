package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.query.dto.AllPaymentsDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.dto.PayrollDTO;
import com.pado.inflow.payroll.query.repository.PayrollMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final PayrollMapper payrollMapper;

    public PayrollServiceImpl(PayrollMapper payrollMapper) {
        this.payrollMapper = payrollMapper;
    }

    // 사원별 연월별 급여 명세서 조회
    @Override
    public PayrollDTO findPaymentDetail(Long employeeId, Integer year, Integer month) {
       PayrollDTO payment = payrollMapper.findPaymentByEmployeeIdAndYearAndMonth(employeeId, year, month);
       if (payment == null) {
           throw new CommonException(ErrorCode.NOT_FOUND_PAYMENT);
       }
       return payment;
    }

    // 사원별 전체 급여 내역 조회
    @Override
    public PageDTO<AllPaymentsDTO> findPaymentsByEmployeeId(Long employeeId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = payrollMapper.getTotalPaymentsByEmployeeId(employeeId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_PAYMENT);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<AllPaymentsDTO> payments = payrollMapper.findPaymentsByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset);
        if(payments == null || payments.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PAYMENT);
        }

        return new PageDTO<>(payments, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);

    }

    @Override
    public List<AllPaymentsDTO> findPaymentsByYear(Long employeeId, int year) {
        List<AllPaymentsDTO> payments = payrollMapper.findPaymentsByYear(employeeId, year);
        if(payments == null || payments.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PAYMENT);
        }
        return payments;
    }

    @Override
    public List<AllPaymentsDTO> findPeriodicPayments(Long employeeId, int startMonth, int endMonth) {
        List<AllPaymentsDTO> payments = payrollMapper.findPeriodicPayments(employeeId, startMonth, endMonth);
        if(payments == null || payments.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PAYMENT);
        }
        return payments;
    }
}
