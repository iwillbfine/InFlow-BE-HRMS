package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.TaskTypeDTO;

import java.util.List;

public interface TaskTypeService {
    List<TaskTypeDTO> findAllTaskTypes();
}
