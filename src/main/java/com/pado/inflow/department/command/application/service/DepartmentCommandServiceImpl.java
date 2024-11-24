package com.pado.inflow.department.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.command.domain.aggregate.dto.AddDepartmentRequestDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentResponseDTO;
import com.pado.inflow.department.command.domain.aggregate.entity.Department;
import com.pado.inflow.department.command.domain.aggregate.entity.DepartmentMember;
import com.pado.inflow.department.command.domain.repository.DepartmentMemberRepository;
import com.pado.inflow.department.command.domain.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public DepartmentResponseDTO addDepartment(AddDepartmentRequestDTO addDepartmentRequestDTO) {

        System.out.println("Request DTO: " + addDepartmentRequestDTO);

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
}
