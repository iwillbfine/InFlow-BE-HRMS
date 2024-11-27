package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ValidationResponseDTO {
    private List<String> positions;       // ID 값만 포함
    private List<String> roles;           // ID 값만 포함
    private List<String> duties;          // ID 값만 포함
    private List<String> departments;     // ID 값만 포함
    private List<String> appointmentItems; // ID 값만 포함
}
