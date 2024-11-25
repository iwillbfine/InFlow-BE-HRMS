package com.pado.inflow.payroll.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.command.application.dto.RequestNonTaxableDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseNonTaxableDTO;
import com.pado.inflow.payroll.command.domain.aggregate.entity.NonTaxable;
import com.pado.inflow.payroll.command.domain.repository.NonTaxableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commandNonTaxableServiceImpl")
public class NonTaxableServiceImpl implements NonTaxableService{

    private final NonTaxableRepository nonTaxableRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NonTaxableServiceImpl(NonTaxableRepository nonTaxableRepository, ModelMapper modelMapper) {
        this.nonTaxableRepository = nonTaxableRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ResponseNonTaxableDTO createNonTaxable(RequestNonTaxableDTO reqNonTaxableDTO) {
        NonTaxable nonTaxable = modelMapper.map(reqNonTaxableDTO, NonTaxable.class);
        NonTaxable savedEntity = nonTaxableRepository.save(nonTaxable);

        return ResponseNonTaxableDTO.builder()
                .nonTaxableId(savedEntity.getNonTaxableId())
                .nonTaxableName(savedEntity.getNonTaxableName())
                .amount(savedEntity.getAmount())
                .build();

    }

    @Override
    @Transactional
    public ResponseNonTaxableDTO updateNonTaxable(Long nonTaxableId, RequestNonTaxableDTO reqNonTaxableDTO) {

        NonTaxable existingNonTaxable = nonTaxableRepository.findById(nonTaxableId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_NON_TAXABLE));

        if (reqNonTaxableDTO.getNonTaxableName() != null) {
            existingNonTaxable.setNonTaxableName(reqNonTaxableDTO.getNonTaxableName());
        }

        if (reqNonTaxableDTO.getAmount() != null) {
            existingNonTaxable.setAmount(reqNonTaxableDTO.getAmount());
        }

        NonTaxable updatedNonTaxable = nonTaxableRepository.save(existingNonTaxable);

        return ResponseNonTaxableDTO.builder()
                .nonTaxableId(updatedNonTaxable.getNonTaxableId())
                .nonTaxableName(updatedNonTaxable.getNonTaxableName())
                .amount(updatedNonTaxable.getAmount())
                .build();
    }
}
