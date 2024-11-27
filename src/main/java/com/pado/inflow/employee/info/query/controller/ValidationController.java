package com.pado.inflow.employee.info.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.info.query.dto.response.validate.ValidationResponseDTO;
import com.pado.inflow.employee.info.query.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validations")
@RequiredArgsConstructor
public class ValidationController {
    private final ValidationService validationService;

    @GetMapping("/")
    public ValidationResponseDTO getValidationResponse() {
        return validationService.getValidationResponse();
    }
}