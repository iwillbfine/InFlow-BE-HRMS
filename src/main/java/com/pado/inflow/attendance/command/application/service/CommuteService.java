package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.CommuteDTO;

public interface CommuteService {

    // 출근
    CommuteDTO goToWork(Long employeeId);

    // 재택근무 출근
    CommuteDTO goToRemoteWork(Long commuteId);

}
