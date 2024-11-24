package com.pado.inflow.evaluation.query.dto;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskItemDTO {

    private Long taskItemId;
    private String taskName;
    private String taskContent;
    private Long assignedEmployeeCount;
    private Long taskTypeId;
    private Long employeeId;
    private String departmentCode;
    private Long evaluationPolicyId;
    private Boolean isManagerWritten;       // 부서장 작성 여부

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