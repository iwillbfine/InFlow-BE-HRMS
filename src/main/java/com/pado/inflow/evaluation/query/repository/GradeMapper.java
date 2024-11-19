package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.GradeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeMapper {
    List<GradeDTO> findGradeByYearAndHalf(Integer year, String half);

    GradeDTO getGradeByGradeId(Long gradeId);
}
