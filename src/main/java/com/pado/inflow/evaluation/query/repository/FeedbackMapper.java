package com.pado.inflow.evaluation.query.repository;

import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper {

    FeedbackDTO findEmpFeedback(Long empId, Integer year, String half);

    FeedbackDTO findFeedbackByFeedbackId(Long feedbackId);
}
