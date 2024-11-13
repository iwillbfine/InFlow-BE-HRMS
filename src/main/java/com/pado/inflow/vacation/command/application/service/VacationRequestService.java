package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.vacation.command.application.dto.RequestCancelVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationRequestDTO;

public interface VacationRequestService {

    // 휴가 신청 등록
    ResponseVacationRequestDTO registVacationRequest(RequestVacationRequestDTO reqVacationRequestDTO);

    // 휴가 신청 취소
    ResponseVacationRequestDTO cancelVacationRequest(Long vacationRequestId,
                                                     RequestCancelVacationRequestDTO reqCancelVacationRequestDTO);

}
