package com.pado.inflow.department.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.command.domain.aggregate.dto.*;
import com.pado.inflow.department.command.domain.aggregate.entity.Department;
import com.pado.inflow.department.command.domain.aggregate.entity.DepartmentMember;
import com.pado.inflow.department.command.domain.repository.DepartmentMemberRepository;
import com.pado.inflow.department.command.domain.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service("DepartmentService")
public class DepartmentCommandServiceImpl implements DepartmentCommandService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;

    public DepartmentCommandServiceImpl(DepartmentRepository departmentRepository,
                                        DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }


    // 드롭다운 서비스
    @Override
    @Transactional
    public List<DepartmentDropdownDTO> getDepartmentsForDropdown() {
        return departmentRepository.findAllForDropdown();
    }

    // 부서 추가
    @Override
    @Transactional
    public DepartmentResponseDTO addDepartment(AddDepartmentRequestDTO addDepartmentRequestDTO) {

        // 1. 부서 정보 저장
        Department department = new Department();
        department.setDepartmentCode(addDepartmentRequestDTO.getDepartmentCode());
        department.setDepartmentName(addDepartmentRequestDTO.getDepartmentName());
        department.setUpperDepartmentCode(addDepartmentRequestDTO.getUpperDepartmentCode());
        department.setMinEmployeeNum(addDepartmentRequestDTO.getMinEmployeeNum());

        // 부서 저장
        Department savedDepartment = departmentRepository.save(department);

        // 2. 부서장 정보 수정
        DepartmentMember departmentHead = departmentMemberRepository.findByName(addDepartmentRequestDTO.getDepartmentHeadName())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 부서장의 역할과 부서 정보 업데이트
        departmentHead.setRoleName("부서장");
        departmentHead.setDepartmentCode(savedDepartment.getDepartmentCode());
        departmentMemberRepository.save(departmentHead);

        // 3. 반환 DTO 생성
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        responseDTO.setDepartmentCode(savedDepartment.getDepartmentCode());
        responseDTO.setDepartmentName(savedDepartment.getDepartmentName());
        responseDTO.setUpperDepartmentCode(savedDepartment.getUpperDepartmentCode());
        responseDTO.setMinEmployeeNum(savedDepartment.getMinEmployeeNum());
        responseDTO.setDepartmentHeadName(departmentHead.getName());

        return responseDTO;
    }

    // 부서 삭제
    @Override
    @Transactional
    public void deleteDepartment(String departmentCode){
        // 부서 존재 여부 확인
        Department department = departmentRepository.findById(departmentCode)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));

        // 현재 시간
        LocalDateTime now = new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        department.setDisbandedAt(now);
        departmentRepository.save(department); // 저장

    }

    // 부서 수정
    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(String departmentCode, UpdateDepartmentRequestDTO updateDepartmentRequestDTO) {
        // 1. 부서 존재 여부 확인
        Department department = departmentRepository.findById(departmentCode)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));

        // 2. 전달된 필드만 업데이트
        if (updateDepartmentRequestDTO.getDepartmentName() != null) {
            department.setDepartmentName(updateDepartmentRequestDTO.getDepartmentName());
        }
        if (updateDepartmentRequestDTO.getMinEmployeeNum() != null) {
            department.setMinEmployeeNum(updateDepartmentRequestDTO.getMinEmployeeNum());
        }
        if (updateDepartmentRequestDTO.getUpperDepartmentCode() != null) {
            department.setUpperDepartmentCode(updateDepartmentRequestDTO.getUpperDepartmentCode());
        }

        // 3. 부서장 변경 로직 추가
        if (updateDepartmentRequestDTO.getDepartmentHeadName() != null) {
            // 3-1. 부서 구성원 테이블에서 해당 이름의 사원이 존재하는지 확인
            DepartmentMember newDepartmentHead = departmentMemberRepository.findByName(updateDepartmentRequestDTO.getDepartmentHeadName())
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

            // 3-2. 기존 부서장의 역할 변경
            departmentMemberRepository.updateRoleAndDepartmentCodeByDepartmentCode(departmentCode, "직원");

            // 3-3. 새로운 부서장의 역할 및 부서 코드 설정
            newDepartmentHead.setRoleName("부서장");
            newDepartmentHead.setDepartmentCode(departmentCode);
            departmentMemberRepository.save(newDepartmentHead);
        }

        // 4. 부서 업데이트 후 저장
        Department updatedDepartment = departmentRepository.save(department);

        // 5. 응답 DTO 생성 및 반환
        return new DepartmentResponseDTO(
                updatedDepartment.getDepartmentCode(),
                updatedDepartment.getDepartmentName(),
                updatedDepartment.getMinEmployeeNum(),
                updatedDepartment.getUpperDepartmentCode(),
                updateDepartmentRequestDTO.getDepartmentHeadName() // 새로운 부서장 이름 반환
        );
    }

}
