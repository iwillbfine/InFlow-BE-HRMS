package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.command.application.dto.RequestVacationDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationDTO;
import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationPolicy;
import com.pado.inflow.vacation.command.domain.aggregate.type.ExpirationStatus;
import com.pado.inflow.vacation.command.domain.repository.VacationPolicyRepository;
import com.pado.inflow.vacation.command.domain.repository.VacationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service("appVacationService")
public class VacationServiceImpl implements VacationService {

    private final ModelMapper modelMapper;
    private final VacationRepository vacationRepository;
    private final VacationPolicyRepository vacationPolicyRepository;

    @Autowired
    public VacationServiceImpl(ModelMapper modelMapper,
                               VacationRepository vacationRepository,
                               VacationPolicyRepository vacationPolicyRepository) {
        this.modelMapper = modelMapper;
        this.vacationRepository = vacationRepository;
        this.vacationPolicyRepository = vacationPolicyRepository;
    }

    // 휴가 지급
    @Transactional
    @Override
    public ResponseVacationDTO registVacation(RequestVacationDTO reqVacationDTO) {
        // 사원 검사
//        employeeRepository.findById(reqVacationDTO.getEmployeeId())
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        // 휴가 정책 검사
        VacationPolicy vacationPolicy = vacationPolicyRepository.findById(reqVacationDTO.getVacationPolicyId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_VACATION_POLICY));

        // 만료일 검사
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        LocalDateTime expiredAt;
        try {
            date = LocalDate.parse(reqVacationDTO.getExpiredAt(), formatter);
            expiredAt = date.atStartOfDay();
            // 이후 expiredAt으로 처리
        } catch (DateTimeParseException e) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 만료일이 현재 시간보다 이전일 경우 처리
        if (expiredAt.isBefore(LocalDateTime.now().withNano(0))) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        ResponseVacationDTO resVacationDTO = ResponseVacationDTO
                .builder()
                .vacationName(vacationPolicy.getVacationPolicyName())
                .vacationLeft(vacationPolicy.getAllocationDays())
                .vacationUsed(0L)
                .createdAt(LocalDateTime.now().withNano(0))
                .expiredAt(expiredAt)
                .expirationStatus(ExpirationStatus.N.name())
                .employeeId(reqVacationDTO.getEmployeeId())
                .vacationPolicyId(reqVacationDTO.getVacationPolicyId())
                .vacationTypeId(vacationPolicy.getVacationTypeId())
                .build();

        Vacation vacation = vacationRepository.save(modelMapper.map(resVacationDTO, Vacation.class));

        return modelMapper.map(vacation, ResponseVacationDTO.class);
    }

}
