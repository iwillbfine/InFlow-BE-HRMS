package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaluationPolicyMapper {

    List<EvaluationPolicyDTO> findPolicyByYearAndHalf(Integer year, String half);
}
