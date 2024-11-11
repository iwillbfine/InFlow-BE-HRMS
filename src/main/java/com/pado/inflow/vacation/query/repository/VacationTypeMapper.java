package com.pado.inflow.vacation.query.repository;

import com.pado.inflow.vacation.query.dto.VacationTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationTypeMapper {

    // 휴가 종류 전체 조회
    List<VacationTypeDTO> findVacationTypes();

}
