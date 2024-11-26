package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.ResponseCommuteDTO;

public interface CommuteCommandService {

    // 출근
    String checkAndUpdateCommute(Long employeeId);

}
