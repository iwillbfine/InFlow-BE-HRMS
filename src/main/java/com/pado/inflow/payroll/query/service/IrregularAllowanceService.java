package com.pado.inflow.payroll.query.service;

import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;

public interface IrregularAllowanceService {
    PageDTO<IrregularAllowanceDTO> findAllIrregularAllowances(Integer pageNo);
}
