package com.pado.inflow.department.command.domain.aggregate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department_member")
@Data
@NoArgsConstructor // 무인자 생성자 추가
@AllArgsConstructor // 모든 필드를 사용하는 생성자 추가
@Builder // Lombok의 빌더 패턴 적용
public class DepartmentMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_member_id", nullable = false, updatable = false)
    private Long departmentMemberId; // 부서 구성원 ID (PK)

    @Column(name = "employee_number", nullable = false, unique = true, length = 255)
    private String employeeNumber; // 사원 번호

    @Column(name = "name", nullable = false, length = 255)
    private String name; // 사원 이름

    @Column(name = "role_name", nullable = false, length = 255)
    private String roleName; // 직책 이름

    @Column(name = "email", nullable = false, length = 255)
    private String email; // 이메일

    @Column(name = "profile_img_url", nullable = false, columnDefinition = "TEXT")
    private String profileImgUrl; // 프로필 이미지 URL

    @Column(name = "phone_number", nullable = false, length = 255)
    private String phoneNumber; // 전화번호

    @Column(name = "attendance_status_type_name", nullable = false, length = 255)
    private String attendanceStatusTypeName; // 근태 상태 이름

    @Column(name = "manager_status", nullable = false, length = 1)
    private String managerStatus; // 관리자 여부 ('Y', 'N')

    @Column(name = "department_code", nullable = false, length = 255)
    private String departmentCode; // 부서 코드

    @Column(name = "employee_id", nullable = false)
    private Long employeeId; // 사원 ID

}
