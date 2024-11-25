package com.pado.inflow.payroll.command.application.service;

import com.pado.inflow.payroll.command.application.dto.RequestIrregularAllowanceDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseIrregularAllowanceDTO;

public interface IrregularAllowanceService {
    ResponseIrregularAllowanceDTO createIrregularAllowance(RequestIrregularAllowanceDTO reqAllowanceDTO);

    ResponseIrregularAllowanceDTO updateIrregularAllowance(Long irregularAllowanceId, RequestIrregularAllowanceDTO reqAllowanceDTO);
}
