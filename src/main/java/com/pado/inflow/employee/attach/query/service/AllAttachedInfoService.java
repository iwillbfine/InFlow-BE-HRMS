package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.AllAttachedInfoDTO;
import com.pado.inflow.employee.attach.query.repository.AllAttachedInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("AAIService")
public class AllAttachedInfoService {

    private final AllAttachedInfoMapper allAttachedInfoMapper;

    @Autowired
    public AllAttachedInfoService(AllAttachedInfoMapper allAttachedInfoMapper) {
        this.allAttachedInfoMapper = allAttachedInfoMapper;
    }

    // 사원의 등록된 정보(자격증, 경력, ...) 정보 가져오기
    public List<AllAttachedInfoDTO> getAllAttechedInfoList(String empId) {
        return Optional.of(allAttachedInfoMapper.getAIList(empId))
                .filter(xx -> !xx.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 부서별 사원의 등록된 정보(자격증, 경력, ...) 정보 가져오기
    public List<AllAttachedInfoDTO> getAllAttechedInfoDept(String deptCode) {
        return Optional.of(allAttachedInfoMapper.getAIListDept(deptCode))
                .filter(xx -> !xx.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
