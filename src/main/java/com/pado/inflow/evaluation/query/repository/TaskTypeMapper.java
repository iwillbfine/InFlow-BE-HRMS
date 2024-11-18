package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.TaskTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskTypeMapper {
    List<TaskTypeDTO> findAllTaskTypes();
}
