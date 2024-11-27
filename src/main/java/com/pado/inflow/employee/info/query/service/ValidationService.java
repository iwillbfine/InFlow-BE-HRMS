package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.employee.info.query.dto.response.validate.ValidationResponseDTO;
import com.pado.inflow.employee.info.query.repository.ValidationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationMapper validationMapper;

    public ValidationResponseDTO getValidationResponse() {
        ValidationResponseDTO response = new ValidationResponseDTO();

        // ID 값만 추출하여 설정
        response.setPositions(validationMapper.getAllPositions()
                .stream()
                .map(position -> position.getPositionCode())
                .collect(Collectors.toList()));

        response.setRoles(validationMapper.getAllRoles()
                .stream()
                .map(role -> role.getRoleCode())
                .collect(Collectors.toList()));

        response.setDuties(validationMapper.getAllDuties()
                .stream()
                .map(duty -> duty.getDutyCode())
                .collect(Collectors.toList()));

        response.setDepartments(validationMapper.getAllDepartments()
                .stream()
                .map(department -> department.getDepartmentCode())
                .collect(Collectors.toList()));

        response.setAppointmentItems(validationMapper.getAllAppointmentItems()
                .stream()
                .map(item -> item.getAppointmentItemCode())
                .collect(Collectors.toList()));

        return response;
    }
}