package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskItemDTO {

    @JsonProperty("task_item_id")
    private Long taskItemId;

    @JsonProperty("task_name")
    private String taskName;

    @JsonProperty("task_content")
    private String taskContent;

    @JsonProperty("assigned_employee_count")
    private Long assignedEmployeeCount;

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("department_code")
    private String departmentCode;

    @JsonProperty("evaluation_policy_id")
    private Long evaluationPolicyId;

    @JsonProperty("is_manager_written")
    private Boolean isManagerWritten;

    // DTO를 Entity로 변환하는 메서드
    public TaskItemEntity toEntity() {
        return TaskItemEntity.builder()
                .taskItemId(this.taskItemId)
                .taskName(this.taskName)
                .taskContent(this.taskContent)
                .assignedEmployeeCount(this.assignedEmployeeCount)
                .taskTypeId(this.taskTypeId)
                .employeeId(this.employeeId)
                .departmentCode(this.departmentCode)
                .evaluationPolicyId(this.evaluationPolicyId)
                .isManagerWritten(this.isManagerWritten)
                .build();
    }
}