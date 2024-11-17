package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import com.pado.inflow.employee.info.enums.EmployeeRole;
import com.pado.inflow.employee.info.enums.Gender;
import com.pado.inflow.employee.info.enums.ResignationStatus;
import com.pado.inflow.employee.info.enums.JoinType; // 올바른 JoinType 임포트

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_number", nullable = false, unique = true)
    private String employeeNumber;

    @Column(name = "employee_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "resident_registration_number", nullable = false)
    private String residentRegistrationNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_img_url", nullable = false)
    private String profileImgUrl;

    @Column(name = "join_date", nullable = false)
    private LocalDateTime joinDate;

    @Column(name = "join_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private JoinType joinType; // 올바른 JoinType 클래스 사용

    @Column(name = "resignation_date")
    private LocalDateTime resignationDate;

    @Column(name = "resignation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ResignationStatus resignationStatus;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "monthly_salary", nullable = false)
    private Long monthlySalary;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "detailed_address", nullable = false)
    private String detailedAddress;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "department_code", nullable = false)
    private String departmentCode;

    @Column(name = "attendance_status_type_code", nullable = false)
    private String attendanceStatusTypeCode;

    @Column(name = "position_code", nullable = false)
    private String positionCode;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "duty_code", nullable = false)
    private String dutyCode;
}
