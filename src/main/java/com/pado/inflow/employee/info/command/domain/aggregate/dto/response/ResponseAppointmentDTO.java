package com.pado.inflow.employee.info.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAppointmentDTO {

    @JsonProperty("appointment_id") // 인사발령 ID
    private Long appointmentId;

    @JsonProperty("appointed_at") // 발령 날짜
    private String appointedAt;

    @JsonProperty("employee_id") // 발령 대상 사원 ID
    private Long employeeId;

    @JsonProperty("authorizer_id") // 발령 권한자 ID
    private Long authorizerId;

    @JsonProperty("department_code") // 부서 코드
    private String departmentCode;

    @JsonProperty("position_code") // 직위 코드
    private String positionCode;

    @JsonProperty("role_code") // 직책 코드
    private String roleCode;

    @JsonProperty("duty_code") // 직무 코드
    private String dutyCode;

    @JsonProperty("appointment_item_code") // 인사발령 유형 코드
    private String appointmentItemCode;

    @JsonProperty("role_name") // 역할 이름 (서버에서 매핑)
    private String roleName;

    @JsonProperty("manager_status") // 관리자 여부 ('Y' or 'N')
    private String managerStatus;
}
