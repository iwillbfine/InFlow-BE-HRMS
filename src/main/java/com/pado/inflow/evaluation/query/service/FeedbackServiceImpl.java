package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.controller.FeedbackController;
import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import com.pado.inflow.evaluation.query.repository.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public FeedbackDTO findFeedbackByempIdAndYearAndHalf(Long empId, Integer year, String half) {

        FeedbackDTO selectedFeedback = feedbackMapper.findEmpFeedback(empId, year, half);
        if (selectedFeedback == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_FEEDBACK);
        }
        return selectedFeedback;
    }

    @Override
    public FeedbackDTO findFeedbackByFeedbackId(Long feedbackId) {
        FeedbackDTO selectedFeedback = feedbackMapper.findFeedbackByFeedbackId(feedbackId);

        if (selectedFeedback == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_FEEDBACK);
        }
        return selectedFeedback;
    }

    @Override
    @Transactional
    public boolean existsFeedbackByempIdAndYearAndHalf(Long empId, Integer year, String half) {
        FeedbackDTO feedback = feedbackMapper.findEmpFeedback(empId, year, half);
        return feedback != null;

    }
}