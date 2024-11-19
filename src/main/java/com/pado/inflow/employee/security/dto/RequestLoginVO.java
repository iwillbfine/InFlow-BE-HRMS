package com.pado.inflow.employee.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RequestLoginVO {

    @JsonProperty("employee_number")
    private String employeeNumber;

    @JsonProperty("password")
    private String password;
}
