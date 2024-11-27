package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ValidationResponseDTO {
    @JsonProperty("positions")
    private List<String> positions;       // ID 값만 포함

    @JsonProperty("roles")
    private List<String> roles;           // ID 값만 포함

    @JsonProperty("duties")
    private List<String> duties;          // ID 값만 포함

    @JsonProperty("departments")
    private List<String> departments;     // ID 값만 포함

    @JsonProperty("appointment_items")
    private List<String> appointmentItems; // ID 값만 포함
}
