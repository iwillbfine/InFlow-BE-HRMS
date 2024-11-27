package com.pado.inflow.employee.info.query.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeNumberRequest {

    @JsonProperty("employee_number")
    private List<String> employeeNumbers;
}
