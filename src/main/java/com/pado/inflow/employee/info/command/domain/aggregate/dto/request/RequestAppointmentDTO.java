package com.pado.inflow.employee.info.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestAppointmentDTO {

    @JsonProperty("employee_number") // 발령 대상 사원 사번
    private String employeeNumber;

    @JsonProperty("authorizer_id") // 발령 권한자 ID
    private Long authorizerId;

    @JsonProperty("department_code") // 새로운 부서 코드
    private String departmentCode;

    @JsonProperty("position_code") // 새로운 직위 코드
    private String positionCode;

    @JsonProperty("role_code") // 새로운 직책 코드
    private String roleCode;

    @JsonProperty("duty_code") // 새로운 직무 코드
    private String dutyCode;

    @JsonProperty("appointment_item_code") // 인사발령 유형 코드
    private String appointmentItemCode;
}
