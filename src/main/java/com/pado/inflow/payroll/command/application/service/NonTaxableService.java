package com.pado.inflow.payroll.command.application.service;

import com.pado.inflow.payroll.command.application.dto.RequestNonTaxableDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseNonTaxableDTO;

public interface NonTaxableService {

    ResponseNonTaxableDTO createNonTaxable(RequestNonTaxableDTO reqNonTaxableDTO);
    ResponseNonTaxableDTO updateNonTaxable(Long nonTaxableId, RequestNonTaxableDTO reqNonTaxableDTO);
}
