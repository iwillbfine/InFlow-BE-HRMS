package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.repository.GradeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{

    private final GradeMapper gradeMapper;

    public GradeServiceImpl(GradeMapper gradeMapper) {
        this.gradeMapper = gradeMapper;
    }

    @Override
    public List<GradeDTO> findGradeByYearAndHalf(Integer year, String half) {

        List<GradeDTO> selectedGradeList = gradeMapper.findGradeByYearAndHalf(year, half);

        if ( selectedGradeList == null || selectedGradeList.isEmpty() ) {
            throw new CommonException(ErrorCode.NOT_FOUND_GRADE);
        }
        return selectedGradeList;
    }

    @Override
    public GradeDTO findGradeByGradeId(Long gradeId) {
        GradeDTO selectedGrade = gradeMapper.getGradeByGradeId(gradeId);
        return selectedGrade;
    }

    // Grade 생성에 필요한 로직
    @Override
    public List<GradeDTO> findByEvaluationPolicyId(Long evaluationPolicyId) {
        List<GradeDTO> selectedGrades = gradeMapper.findGradeByEvaluationPolicyId(evaluationPolicyId);

        if (selectedGrades == null && selectedGrades.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_GRADE);
        }
        return selectedGrades;
    }
}
