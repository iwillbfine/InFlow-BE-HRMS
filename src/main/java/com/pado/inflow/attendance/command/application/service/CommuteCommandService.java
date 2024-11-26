package com.pado.inflow.attendance.command.application.service;

public interface CommuteCommandService {

    // 출근
    String checkAndUpdateCommute(Long employeeId);

}
