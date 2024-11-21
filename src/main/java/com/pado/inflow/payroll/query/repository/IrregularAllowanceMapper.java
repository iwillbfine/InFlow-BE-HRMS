package com.pado.inflow.payroll.query.repository;

import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IrregularAllowanceMapper {

    // 비정기 수당 항목 전체 조회
    List<IrregularAllowanceDTO> findAllIrregularAllowances(@Param("elementsPerPage") Integer elementsPerPage,
                                                           @Param("offset") Integer offset);
    // 총 비정기 수당 항목 개수 조회
    Integer getTotalIrregularAllowances();

}
