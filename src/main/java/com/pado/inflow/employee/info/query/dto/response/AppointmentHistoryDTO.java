package com.pado.inflow.employee.info.query.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppointmentHistoryDTO {
    @JsonProperty("appointment_id")
    private Long appointmentId;

    @JsonProperty("appointed_at")
    private String appointedAt;

    @JsonProperty("employee_name")
    private String employeeName; // 발령 대상자 이름

    @JsonProperty("authorizer_name")
    private String authorizerName; // 발령 권자 이름

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("duty_name")
    private String dutyName;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("position_name")
    private String positionName;

    @JsonProperty("appointment_item_name")
    private String appointmentItemName;
}
