package com.pado.inflow.employee.info.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.info.command.application.service.AppointmentService;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestAppointmentDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("appointmentsCommandController")
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * 설명. 1. 인사발령
     *  인사팀이 부서 구성원을 추가하고 인사발령 이력을 관리.
     *  인사팀은 부서에 구성원을 추가한다.
     *   1. 기존 부서구성원 데이터 삭제
     *   2. 사원테이블의 부서 코드, 직위 코드, 직책 코드, 직무코드 등의 정보 수정
     *   3. 인사발령 이력애 해당 사원의 정보 삽입(이때 어떤 유형인지 조회 후 삽입 )
     *   4. 직책 조회 후 부서장 여부 판단
     *   5. 부서구성원 테이블에 추가
     *
     */
    @PostMapping
    public ResponseDTO<ResponseAppointmentDTO> appointEmployee(
            @RequestBody RequestAppointmentDTO appointmentRequestDTO) {

        // 서비스 호출 및 발령 처리
        ResponseAppointmentDTO response = appointmentService.processAppointment(appointmentRequestDTO);

        // 처리 결과 응답 반환
        return ResponseDTO.ok(response);
    }

}
