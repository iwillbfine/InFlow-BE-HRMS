package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateGradeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.GradeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.GradeEntity;
import com.pado.inflow.evaluation.command.domain.repository.GradeRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import com.pado.inflow.evaluation.query.repository.GradeMapper;
import com.pado.inflow.evaluation.query.service.EvaluationPolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("commandGradeService")
@Transactional
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final EvaluationPolicyService evaluationPolicyService;
    private final EvaluationPolicyMapper evaluationPolicyMapper;
    private final GradeMapper gradeMapper;
    private final com.pado.inflow.evaluation.query.service.GradeService gradeService;

    public GradeServiceImpl(GradeRepository gradeRepository,
                            EvaluationPolicyService evaluationPolicyService,
                            EvaluationPolicyMapper evaluationPolicyMapper,
                            GradeMapper gradeMapper,
                            com.pado.inflow.evaluation.query.service.GradeService gradeService) {
        this.gradeRepository = gradeRepository;
        this.evaluationPolicyService = evaluationPolicyService;
        this.evaluationPolicyMapper = evaluationPolicyMapper;
        this.gradeMapper = gradeMapper;
        this.gradeService = gradeService;
    }

    @Override
    public List<GradeResponseDTO> createGrade(CreateGradeRequestDTO createGradeRequestDTO, Integer year, String half) {
        // 비율 및 점수 검증
        if (createGradeRequestDTO.getStartRatio() < 0.0 || createGradeRequestDTO.getEndRatio() > 1.0) {
            throw new RuntimeException("등급 비율은 0에서 1 사이여야 합니다");
        }
        if (createGradeRequestDTO.getAbsoluteGradeRatio() < 0 || createGradeRequestDTO.getAbsoluteGradeRatio() > 100) {
            throw new RuntimeException("절대평가 점수는 0에서 100 사이여야 합니다");
        }

        // 평가 정책 목록 조회
        List<EvaluationPolicyDTO> selectedPolicies = evaluationPolicyMapper.findPolicyByYearAndHalf(year, half);
        List<GradeResponseDTO> createdGrades = new ArrayList<>();

        // 각 정책별로 등급 생성
        for (EvaluationPolicyDTO policy : selectedPolicies) {
            List<GradeDTO> existingGrades = gradeService.findByEvaluationPolicyId(policy.getEvaluationPolicyId());

            if (existingGrades.isEmpty()) {
                createGradeRequestDTO.setEvaluationPolicyId(policy.getEvaluationPolicyId());
                createdGrades.add(gradeRepository.save(createGradeRequestDTO.toEntity()).toResponseDTO());
                continue;
            }

            GradeDTO lastGrade = existingGrades.get(existingGrades.size() - 1);

            if (lastGrade.getEndRatio() >= createGradeRequestDTO.getStartRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_RATIO);
            }

            if (createGradeRequestDTO.getAbsoluteGradeRatio() > lastGrade.getAbsoluteGradeRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_SCORE);
            }

            createGradeRequestDTO.setEvaluationPolicyId(policy.getEvaluationPolicyId());
            createdGrades.add(gradeRepository.save(createGradeRequestDTO.toEntity()).toResponseDTO());
        }

        return createdGrades;
    }

    @Override
    public GradeResponseDTO updateGrade(Long gradeId) {
        // 수정할 등급 조회
        GradeEntity targetGrade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_GRADE));

        // 해당 평가정책의 모든 등급 조회
        List<GradeDTO> grades = gradeService.findByEvaluationPolicyId(targetGrade.getEvaluationPolicyId());

        // 현재 등급의 위치 찾기
        int currentIndex = -1;
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getGradeId().equals(gradeId)) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex == -1) {
            throw new CommonException(ErrorCode.NOT_FOUND_GRADE);
        }

        int totalGrades = grades.size();

        // 첫 번째 등급인 경우 (예: S등급)
        if (currentIndex == 0) {
            if (totalGrades > 1) {
                GradeDTO nextGrade = grades.get(1);
                if (targetGrade.getEndRatio() >= nextGrade.getStartRatio()) {
                    throw new CommonException(ErrorCode.INVALID_GRADE_RATIO);
                }
                if (targetGrade.getAbsoluteGradeRatio() <= nextGrade.getAbsoluteGradeRatio()) {
                    throw new CommonException(ErrorCode.INVALID_GRADE_SCORE);
                }
            }
        }
        // 마지막 등급인 경우
        else if (currentIndex == totalGrades - 1) {
            if (targetGrade.getEndRatio() > 1.0) {
                throw new CommonException(ErrorCode.INVALID_GRADE_RATIO);
            }
            GradeDTO prevGrade = grades.get(currentIndex - 1);
            if (targetGrade.getStartRatio() <= prevGrade.getEndRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_RATIO);
            }
            if (targetGrade.getAbsoluteGradeRatio() >= prevGrade.getAbsoluteGradeRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_SCORE);
            }
        }
        // 중간 등급인 경우
        else {
            GradeDTO prevGrade = grades.get(currentIndex - 1);
            GradeDTO nextGrade = grades.get(currentIndex + 1);

            // 비율 검증
            if (targetGrade.getStartRatio() <= prevGrade.getEndRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_RATIO);
            }
            if (targetGrade.getEndRatio() >= nextGrade.getStartRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_RATIO);
            }

            // 절대평가 점수 검증
            if (targetGrade.getAbsoluteGradeRatio() >= prevGrade.getAbsoluteGradeRatio() ||
                    targetGrade.getAbsoluteGradeRatio() <= nextGrade.getAbsoluteGradeRatio()) {
                throw new CommonException(ErrorCode.INVALID_GRADE_SCORE);
            }
        }

        return gradeRepository.save(targetGrade).toResponseDTO();
    }

}