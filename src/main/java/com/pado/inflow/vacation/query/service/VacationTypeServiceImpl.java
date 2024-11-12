package com.pado.inflow.vacation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.query.dto.VacationTypeDTO;
import com.pado.inflow.vacation.query.repository.VacationTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationTypeServiceImpl implements VacationTypeService {

    private final VacationTypeMapper vacationTypeMapper;

    @Autowired
    public VacationTypeServiceImpl(VacationTypeMapper vacationTypeMapper) {
        this.vacationTypeMapper = vacationTypeMapper;
    }

    // 휴가 종류 전체 조회
    @Override
    public List<VacationTypeDTO> findVacationTypes() {
        List<VacationTypeDTO> vacationTypes = vacationTypeMapper.findVacationTypes();
        if (vacationTypes == null || vacationTypes.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION_TYPE);
        }
        return vacationTypes;
    }

}
