package com.pado.inflow.payroll.query.service;

import com.pado.inflow.payroll.query.dto.NonTaxableDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;

public interface NonTaxableService {
    PageDTO<NonTaxableDTO> findAllNonTaxablePayrolls(Integer pageNo);
}
