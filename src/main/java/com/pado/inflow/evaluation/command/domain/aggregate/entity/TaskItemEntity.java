package com.pado.inflow.evaluation.command.domain.aggregate.entity;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskItemResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_item_id")
    private Long taskItemId;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_content", nullable = false)
    private String taskContent;

    @Column(name = "assigned_employee_count", nullable = false)
    private Long assignedEmployeeCount;

    @Column(name = "is_manager_written")
    private Boolean isManagerWritten = false;

    @Column(name = "task_type_id", nullable = false)
    private Long taskTypeId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "department_code", nullable = false)
    private String departmentCode;

    @Column(name = "evaluation_policy_id", nullable = false)
    private Long evaluationPolicyId;


    public TaskItemResponseDTO toResponseDTO() {
        return TaskItemResponseDTO.builder()
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