package com.pado.inflow.department.command.application.service;

import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO;
import com.pado.inflow.department.command.domain.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("DepartmentService")
public class DepartmentCommandServiceImpl implements DepartmentCommandService {



    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentCommandServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }


    // 드롭다운 서비스
    @Override
    @Transactional

    public List<DepartmentDropdownDTO> getDepartmentsForDropdown() {
        return departmentRepository.findAllForDropdown();
    }


//    @Override
//    @Transactional
//    public DepartmentResponseDTO addDepartment(AddDepartmentRequestDTO addDepartmentRequestDTO) {
//
//        // 예외 처리
//        if (addDepartmentRequestDTO.getDepartmentCode() == null || addDepartmentRequestDTO.getDepartmentCode().trim().isEmpty()) {
//            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
//        }
//
//        // 부서 저장
//        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
//
//        // 부서 코드
//        departmentResponseDTO.setDepartmentCode(addDepartmentRequestDTO.getDepartmentCode());
//        // 부서명
//        departmentResponseDTO.setDepartmentName(addDepartmentRequestDTO.getDepartmentName());
//        // 상위 부서명
//
//        // 최소 인원수
//        departmentResponseDTO.setMinEmployeeNum(addDepartmentRequestDTO.getMinEmployeeNum());
//        // 부서장
//
//
//
//    }

}
