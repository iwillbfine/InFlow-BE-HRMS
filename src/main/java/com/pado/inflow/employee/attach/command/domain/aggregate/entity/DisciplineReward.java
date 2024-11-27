package com.pado.inflow.employee.attach.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "DisciplineReward")
@Table(name = "discipline_reward")
public class DisciplineReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_reward_id")
    private Long disciplineRewardId;

    @Column(name = "discipline_reward_name")
    private String disciplineRewardName;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "employee_id")
    private Long employeeId;
}





