package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.employee.info.query.dto.response.AppointmentHistoryDTO;
import com.pado.inflow.employee.info.query.repository.AppointmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentQueryService {
    private final AppointmentMapper appointmentMapper;

    public AppointmentQueryService(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentHistoryDTO> getAppointmentHistory(int year, int month, String appointmentItemCode) {

        List<AppointmentHistoryDTO>  appointmentHistorys =appointmentMapper.getAppointmentHistory(year, month, appointmentItemCode);

        log.info("appointmentHistorys: {}",appointmentHistorys);
        return appointmentHistorys;
    }
}
