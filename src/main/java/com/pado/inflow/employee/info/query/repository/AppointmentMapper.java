package com.pado.inflow.employee.info.query.repository;

import com.pado.inflow.employee.info.query.dto.response.AppointmentHistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppointmentMapper {

    List<AppointmentHistoryDTO> getAppointmentHistory(
            @Param("year") int year,
            @Param("month") int month,
            @Param("appointmentItemCode") String appointmentItemCode
    );

}