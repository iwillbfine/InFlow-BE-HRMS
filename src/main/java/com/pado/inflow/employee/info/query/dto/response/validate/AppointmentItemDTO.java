package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppointmentItemDTO {
    @JsonProperty("appointment_item_code")
    private String appointmentItemCode;

    @JsonProperty("appointment_item_name")
    private String appointmentItemName;
}
