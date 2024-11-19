package com.pado.inflow.evaluation.command.domain.aggregate.entity;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_type")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_type_id")
    private Long taskTypeId;

    @Column(name = "task_type_name")
    private String taskTypeName;

}