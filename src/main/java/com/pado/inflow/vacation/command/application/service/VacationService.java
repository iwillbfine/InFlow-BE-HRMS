package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.vacation.command.application.dto.RequestVacationDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationDTO;

public interface VacationService {

    ResponseVacationDTO registVacation(RequestVacationDTO reqVacationDTO);

}
