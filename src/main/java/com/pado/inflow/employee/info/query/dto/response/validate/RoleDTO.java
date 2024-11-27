package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDTO {
    @JsonProperty("role_code")
    private String roleCode;

    @JsonProperty("role_name")
    private String roleName;
}
