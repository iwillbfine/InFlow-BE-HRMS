package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.LeaveReturnDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.repository.LeaveReturnMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveReturnServiceImpl implements LeaveReturnService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final LeaveReturnMapper leaveReturnMapper;

    @Autowired
    public LeaveReturnServiceImpl(LeaveReturnMapper leaveReturnMapper) {
        this.leaveReturnMapper = leaveReturnMapper;
    }

    // 사웝별 휴복직 내역 조회
    public PageDTO<LeaveReturnDTO> findLeaveReturnsByEmployeeId(Long employeeId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = leaveReturnMapper.getTotalLeaveReturnsByEmployeeId(employeeId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_LEAVE_RETURN);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<LeaveReturnDTO> leaveReturns =
                leaveReturnMapper.findLeaveReturnsByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset);
        if (leaveReturns == null || leaveReturns.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_COMMUTE);
        }

        return new PageDTO<>(leaveReturns, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

}
