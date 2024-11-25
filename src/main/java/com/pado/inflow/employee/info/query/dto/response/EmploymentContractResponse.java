package com.pado.inflow.employee.info.query.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmploymentContractResponse {

    @JsonProperty("employee_id")
    private Long employeeId; // 직원 ID

    @JsonProperty("employee_name")
    private String employeeName; // 직원 이름

    @JsonProperty("employee_address")
    private String employeeAddress; // 직원 주소

    @JsonProperty("employee_phone_number")
    private String employeePhoneNumber; // 직원 연락처

    @JsonProperty("birth_date")
    private LocalDate birthDate; // 직원 생년월일

    @JsonProperty("department_name")
    private String departmentName; // 부서명

    @JsonProperty("position_name")
    private String positionName; // 직위명

    @JsonProperty("role_name")
    private String roleName; // 직책명

    @JsonProperty("duty_name")
    private String dutyName; // 직무명

    @JsonProperty("contract_start_date")
    private LocalDate contractStartDate; // 근로계약 시작일

    @JsonProperty("contract_end_date")
    private LocalDate contractEndDate; // 근로계약 종료일

    @JsonProperty("work_location")
    private String workLocation; // 근무 장소

    @JsonProperty("job_description")
    private String jobDescription; // 업무 내용

    @JsonProperty("work_start_time")
    private String workStartTime = "09시 00분"; // 근무 시작 시간

    @JsonProperty("work_end_time")
    private String workEndTime = "18시 00분"; // 근무 종료 시간

    @JsonProperty("break_start_time")
    private String breakStartTime = "12시 00분"; // 휴게 시작 시간

    @JsonProperty("break_end_time")
    private String breakEndTime = "13시 20분"; // 휴게 종료 시간

    @JsonProperty("work_days")
    private Integer workDays = 5; // 근무일수

    @JsonProperty("rest_days")
    private String restDays = "토요일, 일요일"; // 주휴일

    @JsonProperty("salary_type")
    private String salaryType="월급"; // 월급/일급/시간급

    @JsonProperty("salary_amount")
    private Long salaryAmount; // 급여 금액

    @JsonProperty("has_bonus")
    private Boolean hasBonus = true; // 상여금 여부

    @JsonProperty("irregular_allowances")
    private List<IrregularAllowanceDTO> irregularAllowances; // 비정기 수당 목록

    @JsonProperty("payment_day")
    private Integer paymentDay = 20; // 급여 지급일

    @JsonProperty("payment_method")
    private String paymentMethod = "근로자 명의 예금통장에 입금"; // 지급 방법

    @JsonProperty("company_name")
    private String companyName; // 회사명

    @JsonProperty("ceo_name")
    private String ceoName; // 대표자명

    @JsonProperty("company_address")
    private String companyAddress; // 회사 주소

    @JsonProperty("company_phone_number")
    private String  companyPhoneNumber; // 회사 연락처

    @JsonProperty("ceo_signature")
    private String ceoSignature; // 대표 사인

    @JsonProperty("issue_date")
    private LocalDate issueDate; // 계약 작성일

    @JsonProperty("contract_id")
    private Long contractId; // 계약서id
}
