package com.pado.inflow.employee.attach.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeptAttachedInfoDTO {
    private String departmentCode;
    private String departmentName;
    private List<AllAttachedInfoDTO> employeesInfo;
}
