package com.pado.inflow.employee.info.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.info.query.dto.response.AppointmentHistoryDTO;
import com.pado.inflow.employee.info.query.service.AppointmentQueryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentQueryController {

    private final AppointmentQueryService appointmentService;

    public AppointmentQueryController(AppointmentQueryService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/history")
    public ResponseDTO<List<AppointmentHistoryDTO>> getAppointmentHistory(
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam(value = "appointment_item_code") String appointmentItemCode // 스네이크 표기법으로 설정
    ) {
        List<AppointmentHistoryDTO>  appointmentHistorys=appointmentService.getAppointmentHistory(year, month, appointmentItemCode);
        return ResponseDTO.ok(appointmentHistorys);
    }
}
