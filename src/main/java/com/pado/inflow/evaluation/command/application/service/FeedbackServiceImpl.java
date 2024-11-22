package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.FeedbackEntity;
import com.pado.inflow.evaluation.command.domain.repository.FeedbackRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import com.pado.inflow.evaluation.query.service.EvaluationPolicyService;
import com.pado.inflow.evaluation.query.service.EvaluationService;
import com.pado.inflow.evaluation.query.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("commandFeedbackService")
public class FeedbackServiceImpl implements FeedBackService {

    @Autowired
    private final FeedbackRepository feedbackRepository;
    private final EvaluationPolicyService evaluationPolicyService;
    private final EvaluationService evaluationService;
    private final FeedbackService feedbackService;
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository
                              , EvaluationPolicyService evaluationPolicyService
                              , EvaluationService evaluationService
                              , FeedbackService feedbackService) {

        this.feedbackRepository = feedbackRepository;
        this.evaluationPolicyService = evaluationPolicyService;
        this.evaluationService = evaluationService;
        this.feedbackService = feedbackService;
    }

    @Override
    @Transactional
    public CreateFeedbackResponseDTO createFeedback(CreateFeedbackRequestDTO createFeedbackRequestDTO) {

        // ReqeustDTO에 담긴 평가ID와 연결된 Year, Half로 해당 평가 정책 조회
        EvaluationDTO selectedEvaluation =
                evaluationService.findEvaluationByEvaluationId(createFeedbackRequestDTO.getEvaluationId());
        if (selectedEvaluation == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION);
        }

        // 해당 평가에 연결된 피드백이 존재하는지 확인.
        FeedbackDTO selectedFeedback =
                feedbackService.findFeedbackByempIdAndYearAndHalf(selectedEvaluation.getEmployeeId(), selectedEvaluation.getYear(), selectedEvaluation.getHalf());
        if (selectedFeedback != null) {
            throw new CommonException(ErrorCode.DUPLICATED_FEEDBACK_CREATION);
        }

        List<EvaluationPolicyDTO> selectedEvaluationPolicy =
                evaluationPolicyService.findPolicyWithYearAndHalf(selectedEvaluation.getYear(), selectedEvaluation.getHalf());


        // 또한, 평가 시작일 이후면서 평가 종료일 이전에 보낸 Request라면
        if (selectedEvaluationPolicy == null || selectedEvaluationPolicy.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EVALUATION_POLICY);
        } else {
            if (selectedEvaluationPolicy.get(0).getStartDate().isBefore(LocalDateTime.now())
             || selectedEvaluationPolicy.get(0).getEndDate().isAfter(LocalDateTime.now())) {
                throw new CommonException(ErrorCode.FEEDBACK_CREATE_FAILURE);
            }
        }

        // Feedback 생성
        FeedbackEntity savedEntity =
                feedbackRepository.save(createFeedbackRequestDTO.toEntity());

        CreateFeedbackResponseDTO responseDTO = FeedbackEntity.toDTO(savedEntity);

        return responseDTO;
    }

    @Override
    @Transactional
    public UpdateFeedbackResponseDTO updateFeedback(Long feedbackId, UpdateFeedbackRequestDTO updateFeedbackRequestDTO) {

        // 피드백 조회
        FeedbackDTO selectedFeedback =
                feedbackService.findFeedbackByFeedbackId(feedbackId);

        // 피드백id로 평가 조회 및 년도 반기로 정책 조회
        EvaluationDTO selectedEvaluation =
                evaluationService.findEvaluationByEvaluationId(selectedFeedback.getEvaluationId());

        // 평가 시작일 이후이면서 평가 종료일 이전인지 확인
        List<EvaluationPolicyDTO> selectedEvaluationPolicy =
                evaluationPolicyService.findPolicyWithYearAndHalf(selectedEvaluation.getYear(), selectedEvaluation.getHalf());

        if (selectedEvaluationPolicy.get(0).getStartDate().isAfter(LocalDateTime.now())
                || selectedEvaluationPolicy.get(0).getEndDate().isBefore(LocalDateTime.now())) {
            throw new CommonException(ErrorCode.FEEDBACK_UPDATE_FAILURE);
        }

        selectedFeedback.setContent(updateFeedbackRequestDTO.getContent());
        FeedbackEntity savedEntity = feedbackRepository.save(selectedFeedback.toEntity());

        UpdateFeedbackResponseDTO responseDTO = FeedbackEntity.toUpdateDTO(savedEntity);

        return responseDTO;
    }
}
