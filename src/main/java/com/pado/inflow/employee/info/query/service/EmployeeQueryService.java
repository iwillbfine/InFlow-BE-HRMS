package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseContractDTO;
import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.dto.response.EmployeeDetailResponse;
import com.pado.inflow.employee.info.query.dto.response.EmploymentCertificateResponse;
import com.pado.inflow.employee.info.query.dto.response.EmploymentContractResponse;
import com.pado.inflow.employee.info.query.dto.response.ResponseSecurityAgreementResponse;
import com.pado.inflow.employee.info.query.repository.EmployeeMapper;
import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("employeeQueryService")
public class EmployeeQueryService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeQueryService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    //설명.1.1.  사원 리스트 전체 조회

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = employeeMapper.findAllEmployees();
        if (employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employees;
    }

    //설명.1.2. 사원 리스트 이름으로 조회

    public List<EmployeeDTO> getEmployeesByName(String name) {
        List<EmployeeDTO> employees = employeeMapper.findEmployeesByName(name);
        if (employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employees;
    }

    //설명.1.3  사원 정보 사번으로 조회
    public EmployeeDTO getEmployeeByNumber(String employeeNumber) {
       EmployeeDTO employee =  employeeMapper.findEmployeeByNumber(employeeNumber);
        if (employee == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        return employee;
    }

    //설명.1.4 사원 정보 id로 조회
    public EmployeeDTO getEmployeeById(Long employeeId) {

        EmployeeDTO employee =employeeMapper.findEmployeeById(employeeId);
        if (employee == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employee;
    }

    //설명.1.5. 사원 정보 id로 상세 조회
    public EmployeeDetailResponse getEmployeeDetail(Long employeeId) {
        return employeeMapper.findEmployeeDetailById(employeeId);
    }

    //설명.1.5 여러 사번으로 사원들 조회
    public List<EmployeeDTO> getEmployeesByEmployeeNumbers(List<String> employeeNumbers) {
        List<EmployeeDTO> employees = employeeMapper.findEmployeesByEmployeeNumbers(employeeNumbers);

        // 조회 결과가 비어 있는 경우 예외 처리
        if (employees == null || employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        return employees;
    }

    // 설명.2. 재직증명서 내용 반환
    public EmploymentCertificateResponse getEmploymentCertificateInfo(Long employeeId) {
        EmploymentCertificateResponse certificate = employeeMapper.getEmploymentCertificateInfo(employeeId);
        if (certificate == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        // 발급 날짜 추가
        certificate.setIssueDate(LocalDate.now().toString());

        // 내용 추가 (임의 값)
        certificate.setContent("본 재직증명서는 해당 사원이 현재 소속 회사에서 근무 중임을 증명합니다.");

        return certificate;
    }

    // 설명.3.근로계약서 내용 반환
    public EmploymentContractResponse getEmploymentContract(Long employeeId) {
        // 사원 근로 계약 정보 조회
        EmploymentContractResponse contract = employeeMapper.getEmploymentContractInfo(employeeId);

        // 비정기 수당 정보 조회
        List<IrregularAllowanceDTO> allowances = employeeMapper.getIrregularAllowances();
        contract.setIrregularAllowances(allowances);

        // 업무 장소 설정
        contract.setWorkLocation(contract.getCompanyAddress());

        // 직무 설명 설정
        String jobDescription = generateJobDescription(contract.getDepartmentName(), contract.getDutyName());
        contract.setJobDescription(jobDescription);

        // 계약 종료일 설정 (입사일로부터 1년 후)
        contract.setContractEndDate(contract.getContractStartDate().plusYears(1));

        // 계약 작성일 설정 (현재 날짜)
        LocalDate currentDate = LocalDate.now();
        contract.setIssueDate(currentDate);

        return contract;
    }

    //설명.3.1 직무 설명 메서드
    private String generateJobDescription(String departmentName, String dutyName) {
        if (departmentName == null || dutyName == null) {
            return "부서 또는 직무 정보가 부족하여 설명을 제공할 수 없습니다.";
        }

        switch (departmentName) {
            case "경영지원부":
                switch (dutyName) {
                    case "기획":
                        return "회사의 전략적 목표를 설정하고 사업 계획을 수립합니다.";
                    case "지원":
                        return "회사의 경영 활동을 지원하고 부서 간 조율 업무를 수행합니다.";
                    default:
                        return "경영지원부에서 " + dutyName + " 업무를 수행합니다.";
                }

            case "인사부":
                switch (dutyName) {
                    case "채용":
                        return "회사의 인재를 채용하고 직원 관리 업무를 수행합니다.";
                    case "교육":
                        return "직원 교육 프로그램을 기획하고 실행합니다.";
                    default:
                        return "인사부에서 " + dutyName + " 업무를 수행합니다.";
                }

            case "재무부":
                switch (dutyName) {
                    case "회계":
                        return "회사의 재무 기록을 관리하고 결산 업무를 수행합니다.";
                    case "예산관리":
                        return "회사의 예산을 수립하고 자금을 효율적으로 관리합니다.";
                    default:
                        return "재무부에서 " + dutyName + " 업무를 수행합니다.";
                }

            case "영업부":
                switch (dutyName) {
                    case "영업관리":
                        return "고객과의 계약 체결 및 관리 업무를 수행합니다.";
                    case "고객지원":
                        return "고객 요청 사항에 대응하고 문제를 해결합니다.";
                    default:
                        return "영업부에서 " + dutyName + " 업무를 수행합니다.";
                }

            case "마케팅부":
                switch (dutyName) {
                    case "브랜드 관리":
                        return "회사의 브랜드 이미지를 관리하고 마케팅 전략을 수립합니다.";
                    case "프로모션":
                        return "마케팅 프로모션을 기획하고 실행합니다.";
                    default:
                        return "마케팅부에서 " + dutyName + " 업무를 수행합니다.";
                }

            case "IT기술지원부":
                switch (dutyName) {
                    case "기술지원":
                        return "사내 IT 기술 문제를 해결하고 사용자 지원 업무를 수행합니다.";
                    case "시스템 관리":
                        return "시스템을 설치하고 유지보수 업무를 수행합니다.";
                    default:
                        return "IT기술지원부에서 " + dutyName + " 업무를 수행합니다.";
                }

            case "영업1팀":
            case "영업2팀":
                return "영업부 소속으로 고객 관리 및 계약 체결 업무를 수행합니다.";

            case "인사관리팀":
                return "인사부 소속으로 직원 관리 및 인사 관련 업무를 수행합니다.";

            case "재무회계팀":
                return "재무부 소속으로 회계 및 재무 보고 업무를 수행합니다.";

            case "기획팀":
                return "영업부 소속으로 회사의 전략적 목표를 수립하고 프로젝트를 관리합니다.";

            case "기술지원팀":
                return "IT기술지원부 소속으로 기술 문제 해결 및 시스템 유지보수 업무를 수행합니다.";

            default:
                return departmentName + "에서 " + dutyName + " 업무를 수행합니다.";
        }
    }

    // 설명.4.비밀 유지 서약서 내용 반환
    public ResponseSecurityAgreementResponse getSecurityAgreement(Long employeeId) {
        ResponseSecurityAgreementResponse agreement = employeeMapper.getSecurityAgreementInfo(employeeId);

        if (agreement == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        // 서약 날짜 설정 (현재 날짜)
        agreement.setAgreementDate(LocalDate.now().toString());

        return agreement;
    }

    // 설명.5. 계약서 리스트 조회
    public List<ResponseContractDTO> getContractListByEmployeeId(Long employeeId) {
        List<ResponseContractDTO> contracts = employeeMapper.getContractListByEmployeeId(employeeId);
        if (contracts.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_CONTRACT);
        }
        return contracts;
    }

    // 설명.6. 단건 계약서 조회
    public ResponseContractDTO getContract(Long contractId) {
        ResponseContractDTO contract = employeeMapper.findContractByContractId(contractId);
        if (contract == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_CONTRACT);
        }
        return contract;
    }

}
