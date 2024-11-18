package com.pado.inflow.employee.info.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.command.domain.aggregate.entity.DepartmentMember;
import com.pado.inflow.department.command.domain.repository.DepartmentMemberRepository;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestAppointmentDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseAppointmentDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Appointment;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    private final EmployeeRepository employeeRepository;
    private final AppointmentRepository appointmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;
    private final RoleRepository roleRepository;
    private final PositionRepository positionRepository;
    private final DutyRepository dutyRepository;

    @Autowired
    public AppointmentService(EmployeeRepository employeeRepository,
                              AppointmentRepository appointmentRepository,
                              DepartmentMemberRepository departmentMemberRepository,
                              RoleRepository roleRepository,
                              PositionRepository positionRepository,
                              DutyRepository dutyRepository) {
        this.employeeRepository = employeeRepository;
        this.appointmentRepository = appointmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
        this.roleRepository = roleRepository;
        this.positionRepository = positionRepository;
        this.dutyRepository = dutyRepository;
    }

    /**
     * 설명: 인사 발령 처리를 담당하며 트랜잭션으로 관리.
     */
    @Transactional(rollbackFor = Exception.class) // API 호출 실패 시 트랜잭션 롤백 처리.
    public ResponseAppointmentDTO processAppointment(RequestAppointmentDTO appointmentRequestDTO) {
        // 1. 기존 부서 구성원 데이터 삭제
        departmentMemberRepository.deleteByEmployeeId(appointmentRequestDTO.getEmployeeId());

        // 2. 사원 정보 수정
        Employee employee = updateEmployeeInfo(appointmentRequestDTO);

        // 3. 인사발령 이력 추가
        Appointment appointment = addAppointmentHistory(appointmentRequestDTO);

        // 4. 부서 구성원 추가
        addDepartmentMember(employee, appointmentRequestDTO);

        // 5. Response DTO 생성 및 반환
        return buildResponseAppointmentDTO(appointment, employee);
    }

    /**
     * 설명: 사원 정보를 수정하여 부서, 직위, 직책, 직무 정보를 업데이트.
     */
    private Employee updateEmployeeInfo(RequestAppointmentDTO appointmentRequestDTO) {
        Employee employee = employeeRepository.findById(appointmentRequestDTO.getEmployeeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        employee.setDepartmentCode(appointmentRequestDTO.getDepartmentCode());
        employee.setPositionCode(appointmentRequestDTO.getPositionCode());
        employee.setRoleCode(appointmentRequestDTO.getRoleCode());
        employee.setDutyCode(appointmentRequestDTO.getDutyCode());
        return employeeRepository.save(employee);
    }

    /**
     * 설명: 인사 발령 이력을 추가하여 DB에 기록.
     */
    private Appointment addAppointmentHistory(RequestAppointmentDTO appointmentRequestDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointedAt(LocalDateTime.now().withNano(0));
        appointment.setEmployeeId(appointmentRequestDTO.getEmployeeId());
        appointment.setAuthorizerId(appointmentRequestDTO.getAuthorizerId());
        appointment.setDepartmentCode(appointmentRequestDTO.getDepartmentCode());
        appointment.setPositionCode(appointmentRequestDTO.getPositionCode());
        appointment.setRoleCode(appointmentRequestDTO.getRoleCode());
        appointment.setDutyCode(appointmentRequestDTO.getDutyCode());
        appointment.setAppointmentItemCode(appointmentRequestDTO.getAppointmentItemCode());
        return appointmentRepository.save(appointment);
    }

    /**
     * 설명: 부서 구성원 정보를 추가.
     * 중복 데이터 삽입 방지를 위해 employeeNumber의 존재 여부 확인.
     */
    private void addDepartmentMember(Employee employee, RequestAppointmentDTO appointmentRequestDTO) {
        // 설명. 에러 처리
        if (departmentMemberRepository.existsByEmployeeNumber(employee.getEmployeeNumber())) {
            throw new CommonException(ErrorCode.DUPLICATE_ENTRY);
        }

        DepartmentMember departmentMember = DepartmentMember.builder()
                .employeeNumber(employee.getEmployeeNumber())
                .name(employee.getName())
                .roleName(mapRoleCodeToRoleName(appointmentRequestDTO.getRoleCode()))
                .email(employee.getEmail())
                .profileImgUrl(employee.getProfileImgUrl())
                .phoneNumber(employee.getPhoneNumber())
                .attendanceStatusTypeName("정상출근") // 기본값 설정
                .managerStatus(determineManagerStatus(appointmentRequestDTO.getPositionCode()))
                .departmentCode(appointmentRequestDTO.getDepartmentCode())
                .employeeId(employee.getEmployeeId())
                .build();

        departmentMemberRepository.save(departmentMember);
    }

    /**
     * 설명: ResponseAppointmentDTO를 생성하여 반환.
     */
    private ResponseAppointmentDTO buildResponseAppointmentDTO(Appointment appointment, Employee employee) {
        return ResponseAppointmentDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointedAt(appointment.getAppointedAt().toString())
                .employeeId(appointment.getEmployeeId())
                .authorizerId(appointment.getAuthorizerId())
                .departmentCode(appointment.getDepartmentCode())
                .positionCode(appointment.getPositionCode())
                .roleCode(appointment.getRoleCode())
                .dutyCode(appointment.getDutyCode())
                .appointmentItemCode(appointment.getAppointmentItemCode())
                .roleName(mapRoleCodeToRoleName(appointment.getRoleCode()))
                .managerStatus(determineManagerStatus(appointment.getPositionCode()))
                .build();
    }

    /**
     * 설명: 직책 코드에 따라 직책 이름을 조회.
     */
    private String mapRoleCodeToRoleName(String roleCode) {
        return roleRepository.findRoleNameByRoleCode(roleCode)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROLE));
    }

    /**
     * 설명: 직위 코드에 따라 관리자 여부를 확인.
     */
    private String determineManagerStatus(String positionCode) {
        return positionRepository.isManagerPosition(positionCode) ? "Y" : "N";
    }
}
