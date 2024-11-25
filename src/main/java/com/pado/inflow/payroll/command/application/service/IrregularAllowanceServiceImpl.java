package com.pado.inflow.payroll.command.application.service;

import com.pado.inflow.payroll.command.application.dto.RequestIrregularAllowanceDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseIrregularAllowanceDTO;
import com.pado.inflow.payroll.command.domain.aggregate.entity.IrregularAllowance;
import com.pado.inflow.payroll.command.domain.repository.IrregularAllowanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commandIrregularAllowanceService")
public class IrregularAllowanceServiceImpl implements IrregularAllowanceService {

    private final ModelMapper modelMapper;
    private final IrregularAllowanceRepository irregularAllowanceRepository;

    @Autowired
    public IrregularAllowanceServiceImpl(ModelMapper modelMapper,
                                         IrregularAllowanceRepository irregularAllowanceRepository) {
        this.modelMapper = modelMapper;
        this.irregularAllowanceRepository = irregularAllowanceRepository;
    }

    @Override
    @Transactional
    public ResponseIrregularAllowanceDTO createIrregularAllowance(RequestIrregularAllowanceDTO reqAllowanceDTO) {
        IrregularAllowance irregularAllowance = modelMapper.map(reqAllowanceDTO, IrregularAllowance.class);
        IrregularAllowance savedAllowance = irregularAllowanceRepository.save(irregularAllowance);

        return ResponseIrregularAllowanceDTO.builder()
                .irregularAllowanceId(savedAllowance.getIrregularAllowanceId())
                .irregularAllowanceName(savedAllowance.getIrregularAllowanceName())
                .amount(savedAllowance.getAmount())
                .build();
    }
}
