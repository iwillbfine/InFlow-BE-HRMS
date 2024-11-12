package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.command.application.dto.RequestVacationPolicyDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationPolicyDTO;
import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationPolicy;
import com.pado.inflow.vacation.command.domain.aggregate.type.PaidStatus;
import com.pado.inflow.vacation.command.domain.repository.VacationPolicyRepository;

import com.pado.inflow.vacation.command.domain.repository.VacationTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("appVacationPolicyService")
public class VacationPolicyServiceImpl implements VacationPolicyService{

    private final ModelMapper modelMapper;
    private final VacationPolicyRepository vacationPolicyRepository;
    private final VacationTypeRepository vacationTypeRepository;

    @Autowired
    public VacationPolicyServiceImpl(ModelMapper modelMapper,
                                     VacationPolicyRepository vacationPolicyRepository,
                                     VacationTypeRepository vacationTypeRepository) {
        this.modelMapper = modelMapper;
        this.vacationPolicyRepository = vacationPolicyRepository;
        this.vacationTypeRepository = vacationTypeRepository;
    }

    // 휴가 정책 등록
    @Transactional
    @Override
    public ResponseVacationPolicyDTO registVacationPolicy(RequestVacationPolicyDTO reqVacationPolicyDTO) {
        // 유효성 검사
        checkValidVacationPolicyDTO(reqVacationPolicyDTO);

        ResponseVacationPolicyDTO resVacationPolicyDTO = ResponseVacationPolicyDTO
                .builder()
                .vacationPolicyName(reqVacationPolicyDTO.getVacationPolicyName())
                .vacationPolicyDescription(reqVacationPolicyDTO.getVacationPolicyDescription())
                .allocationDays(reqVacationPolicyDTO.getAllocationDays())
                .paidStatus(reqVacationPolicyDTO.getPaidStatus())
                .year(reqVacationPolicyDTO.getYear())
                .createdAt(LocalDateTime.now().withNano(0))
                .autoAllocationCycle(reqVacationPolicyDTO.getAutoAllocationCycle())
                .vacationTypeId(reqVacationPolicyDTO.getVacationTypeId())
                .policyRegisterId(reqVacationPolicyDTO.getPolicyRegisterId())
                .build();

        VacationPolicy vacationPolicy = vacationPolicyRepository.save(modelMapper.map(resVacationPolicyDTO, VacationPolicy.class));

        return modelMapper.map(vacationPolicy, ResponseVacationPolicyDTO.class);
    }

    // 휴가 정책 수정
    @Transactional
    @Override
    public ResponseVacationPolicyDTO modifyVacationPolicy(Long vacationPolicyId, RequestVacationPolicyDTO reqVacationPolicyDTO) {
        // 유효성 검사
        checkValidVacationPolicyDTO(reqVacationPolicyDTO);

        VacationPolicy vacationPolicy = vacationPolicyRepository.findById(vacationPolicyId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_VACATION_POLICY));
        
        vacationPolicy.setVacationPolicyName(reqVacationPolicyDTO.getVacationPolicyName());
        vacationPolicy.setVacationPolicyDescription(reqVacationPolicyDTO.getVacationPolicyDescription());
        vacationPolicy.setAllocationDays(reqVacationPolicyDTO.getAllocationDays());
        vacationPolicy.setPaidStatus(PaidStatus.valueOf(reqVacationPolicyDTO.getPaidStatus()));
        vacationPolicy.setYear(reqVacationPolicyDTO.getYear());
        vacationPolicy.setAutoAllocationCycle(reqVacationPolicyDTO.getAutoAllocationCycle());
        vacationPolicy.setVacationTypeId(reqVacationPolicyDTO.getVacationTypeId());
        
        return modelMapper.map(vacationPolicyRepository.save(vacationPolicy), ResponseVacationPolicyDTO.class);
    }

    private void checkValidVacationPolicyDTO(RequestVacationPolicyDTO reqVacationPolicyDTO) {
        // 휴가 정책 이름이 NULL 이거나 공백일 경우
        if (reqVacationPolicyDTO.getVacationPolicyName() == null || reqVacationPolicyDTO.getVacationPolicyName().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴가 정책 설명이 NULL 이거나 공백일 경우
        if (reqVacationPolicyDTO.getVacationPolicyDescription() == null || reqVacationPolicyDTO.getVacationPolicyDescription().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴가 지급 일수가 NULL 이거나 0이하 일 경우
        if (reqVacationPolicyDTO.getAllocationDays() == null || reqVacationPolicyDTO.getAllocationDays() <= 0) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 유급 휴가 여부가 NULL 이거나 Y,N이 아닐 경우
        if (reqVacationPolicyDTO.getPaidStatus() == null ||
                (!reqVacationPolicyDTO.getPaidStatus().equals("Y") && !reqVacationPolicyDTO.getPaidStatus().equals("N"))) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴가 정책 적용 연도가 NULL 이거나 과거의 연도일 경우
        if (reqVacationPolicyDTO.getYear() == null || reqVacationPolicyDTO.getYear() < LocalDateTime.now().getYear()) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 휴가 자동지급주기가 NULL 이 아닌데 크론 표기법이 아닐 경우
        String cronRegex = "^([0-5]?\\d|\\*)\\s([01]?\\d|2[0-3]|\\*)\\s([1-9]|[12]\\d|3[01]|\\*)\\s([1-9]|1[0-2]|\\*)\\s([0-6]|SUN|MON|TUE|WED|THU|FRI|SAT|\\*)$";

        if (reqVacationPolicyDTO.getAutoAllocationCycle() != null && !reqVacationPolicyDTO.getAutoAllocationCycle().matches(cronRegex)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        // 존재하지 않는 휴가 종류일 경우
        vacationTypeRepository.findById(reqVacationPolicyDTO.getVacationTypeId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_VACATION_TYPE));

        // 존재하지 않는 사원일 경우
//        employeeRepository.findById(reqVacationPolicyDTO.getPolicyRegisterId())
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));
    }

}
