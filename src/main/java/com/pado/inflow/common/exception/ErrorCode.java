package com.pado.inflow.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

//필기. 에러 상태별 메시지
@Getter
@AllArgsConstructor
public enum ErrorCode {
    //400
    WRONG_ENTRY_POINT(40000, HttpStatus.BAD_REQUEST, "잘못된 접근입니다"),
    MISSING_REQUEST_PARAMETER(40001, HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다."),
    INVALID_PARAMETER_FORMAT(40002, HttpStatus.BAD_REQUEST, "요청에 유효하지 않은 인자 형식입니다."),
    BAD_REQUEST_JSON(40003, HttpStatus.BAD_REQUEST, "잘못된 JSON 형식입니다."),
    // 데이터 무결성 위반 추가(ex: db의 NOT NULL 속성)
    DATA_INTEGRITY_VIOLATION(40005, HttpStatus.BAD_REQUEST,
            "데이터 무결성 위반입니다. 필수 값이 누락되었거나 유효하지 않습니다."),
    INVALID_INPUT_VALUE(40010, HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다."),
    INVALID_REQUEST_BODY(40011, HttpStatus.BAD_REQUEST, "잘못된 요청 본문입니다."),
    MISSING_REQUIRED_FIELD(40012, HttpStatus.BAD_REQUEST, "필수 필드가 누락되었습니다."),
    INVALID_VERIFICATION_CODE(40013, HttpStatus.BAD_REQUEST, "잘못된 인증번호입니다. 인증번호를 다시 확인해주세요"),
    INSUFFICIENT_VACATION_DAYS(40014, HttpStatus.BAD_REQUEST, "휴가일수가 부족합니다."),

    // 파일 관련 오류
    UNSUPPORTED_FILE_FORMAT(40020, HttpStatus.BAD_REQUEST, "지원되지 않는 파일 형식입니다."),
    FILE_UPLOAD_ERROR(40021, HttpStatus.BAD_REQUEST, "파일 업로드에 실패했습니다."),
    FILE_CONVERSION_ERROR(40022, HttpStatus.BAD_REQUEST, "파일 변환에 실패했습니다."),
    FILE_SIZE_EXCEEDED(40023, HttpStatus.BAD_REQUEST, "파일 크기가 허용된 최대 크기를 초과했습니다."),

    //401
    INVALID_HEADER_VALUE(40100, HttpStatus.UNAUTHORIZED, "올바르지 않은 헤더값입니다."),

    EXPIRED_TOKEN_ERROR(40101, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN_ERROR(40102, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_MALFORMED_ERROR(40103, HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다."),
    TOKEN_TYPE_ERROR(40104, HttpStatus.UNAUTHORIZED, "토큰 타입이 일치하지 않거나 비어있습니다."),
    TOKEN_UNSUPPORTED_ERROR(40105, HttpStatus.UNAUTHORIZED, "지원하지않는 토큰입니다."),
    TOKEN_GENERATION_ERROR(40106, HttpStatus.UNAUTHORIZED, "토큰 생성에 실패하였습니다."),
    TOKEN_UNKNOWN_ERROR(40107, HttpStatus.UNAUTHORIZED, "알 수 없는 토큰입니다."),
    LOGIN_FAILURE(40108, HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다"),
    UNAUTHORIZED_ACCESS(40110, HttpStatus.UNAUTHORIZED, "인증되지 않은 접근입니다."),
    EXPIRED_SESSION(40111, HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다."),
    EXIST_USER(40112, HttpStatus.UNAUTHORIZED, "이미 회원가입한 회원입니다."),
    DUPLICATE_NICKNAME(40013, HttpStatus.BAD_REQUEST, "이미 사용 중인 닉네임입니다."),
    DUPLICATE_NICKNAME_EXISTS(40014, HttpStatus.BAD_REQUEST, "중복된 닉네임입니다."),
    DUPLICATE_ENTRY(40014, HttpStatus.BAD_REQUEST, "중복된 사원입니다."),
    DUPLICATE_TASK_TYPE(40015, HttpStatus.BAD_REQUEST, "중복된 과제 유형입니다."),

    //403
    FORBIDDEN_ROLE(40300, HttpStatus.FORBIDDEN, "권한이 존재하지 않습니다."),
    ACCESS_DENIED(40310, HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),

    //404
    NOT_FOUND_EMPLOYEE(40401, HttpStatus.NOT_FOUND, "직원이 존재하지 않습니다."),
    NOT_FOUND_FAMILY_MEMBER(40402, HttpStatus.NOT_FOUND, "가족 구성원이 존재하지 않습니다."),
    NOT_FOUND_FAMILY_RELATIONSHIP(40403, HttpStatus.NOT_FOUND, "가족 관계가 존재하지 않습니다."),
    NOT_FOUND_EDUCATION(40404, HttpStatus.NOT_FOUND, "학력 정보가 존재하지 않습니다."),
    NOT_FOUND_CAREER(40405, HttpStatus.NOT_FOUND, "경력 정보가 존재하지 않습니다."),
    NOT_FOUND_CONTRACT(40406, HttpStatus.NOT_FOUND, "계약 정보가 존재하지 않습니다."),
    NOT_FOUND_QUALIFICATION(40407, HttpStatus.NOT_FOUND, "자격 정보가 존재하지 않습니다."),
    NOT_FOUND_LANGUAGE(40408, HttpStatus.NOT_FOUND, "언어 정보가 존재하지 않습니다."),
    NOT_FOUND_LANGUAGE_TEST(40409, HttpStatus.NOT_FOUND, "어학 시험 정보가 존재하지 않습니다."),
    NOT_FOUND_DISCIPLINE_REWARD(40410, HttpStatus.NOT_FOUND, "징계 및 포상 정보가 존재하지 않습니다."),
    NOT_FOUND_APPOINTMENT(40411, HttpStatus.NOT_FOUND, "임용 정보가 존재하지 않습니다."),
    NOT_FOUND_CHECKLIST(40412, HttpStatus.NOT_FOUND, "체크리스트 항목이 존재하지 않습니다."),
    NOT_FOUND_DEPARTMENT_MEMBER(40413, HttpStatus.NOT_FOUND, "부서 구성원이 존재하지 않습니다."),
    NOT_FOUND_VACATION_TYPE(40414, HttpStatus.NOT_FOUND, "휴가 유형이 존재하지 않습니다."),
    NOT_FOUND_VACATION_POLICY(40415, HttpStatus.NOT_FOUND, "휴가 정책이 존재하지 않습니다."),
    NOT_FOUND_VACATION(40416, HttpStatus.NOT_FOUND, "휴가 정보가 존재하지 않습니다."),
    NOT_FOUND_VACATION_REQUEST(40417, HttpStatus.NOT_FOUND, "휴가 요청이 존재하지 않습니다."),
    NOT_FOUND_VACATION_REQUEST_FILE(40418, HttpStatus.NOT_FOUND, "휴가 요청 파일이 존재하지 않습니다."),
    NOT_FOUND_ANNUAL_VACATION_PROMOTION_POLICY(40419, HttpStatus.NOT_FOUND, "연차 승진 정책이 존재하지 않습니다."),
    NOT_FOUND_EARNED_INCOME_TAX(40420, HttpStatus.NOT_FOUND, "근로 소득세 정보가 존재하지 않습니다."),
    NOT_FOUND_MAJOR_INSURANCE(40421, HttpStatus.NOT_FOUND, "주요 보험 정보가 존재하지 않습니다."),
    NOT_FOUND_NON_TAXABLE(40422, HttpStatus.NOT_FOUND, "비과세 정보가 존재하지 않습니다."),
    NOT_FOUND_TAX_CREDIT(40423, HttpStatus.NOT_FOUND, "세액 공제 정보가 존재하지 않습니다."),
    NOT_FOUND_PUBLIC_HOLIDAY(40424, HttpStatus.NOT_FOUND, "공휴일 정보가 존재하지 않습니다."),
    NOT_FOUND_IRREGULAR_ALLOWANCE(40425, HttpStatus.NOT_FOUND, "비정기 수당 항목이 존재하지 않습니다."),
    NOT_FOUND_PAYMENT(40426, HttpStatus.NOT_FOUND, "급여 지급 내역이 존재하지 않습니다."),
    NOT_FOUND_ATTENDANCE_REQUEST_TYPE(40427, HttpStatus.NOT_FOUND, "출석 요청 유형이 존재하지 않습니다."),
    NOT_FOUND_ATTENDANCE_REQUEST(40428, HttpStatus.NOT_FOUND, "출석 요청이 존재하지 않습니다."),
    NOT_FOUND_ATTENDANCE_REQUEST_FILE(40429, HttpStatus.NOT_FOUND, "출석 요청 파일이 존재하지 않습니다."),
    NOT_FOUND_COMMUTE(40430, HttpStatus.NOT_FOUND, "통근 정보가 존재하지 않습니다."),
    NOT_FOUND_LEAVE_RETURN(40431, HttpStatus.NOT_FOUND, "휴직 및 복직 정보가 존재하지 않습니다."),
    NOT_FOUND_BUSINESS_TRIP(40432, HttpStatus.NOT_FOUND, "출장 정보가 존재하지 않습니다."),
    NOT_FOUND_GRADE(40433, HttpStatus.NOT_FOUND, "등급 정보가 존재하지 않습니다."),
    NOT_FOUND_TASK_TYPE(40434, HttpStatus.NOT_FOUND, "과제 유형이 존재하지 않습니다."),
    NOT_FOUND_EVALUATION_POLICY(40435, HttpStatus.NOT_FOUND, "평가 정책이 존재하지 않습니다."),
    NOT_FOUND_EVALUATION(40436, HttpStatus.NOT_FOUND, "평가 정보가 존재하지 않습니다."),
    NOT_FOUND_FEEDBACK(40437, HttpStatus.NOT_FOUND, "피드백 정보가 존재하지 않습니다."),
    NOT_FOUND_TASK(40438, HttpStatus.NOT_FOUND, "과제가 존재하지 않습니다."),
    NOT_FOUND_SEMIANNUAL_DEPARTMENT_PERFORMANCE_RATIO_STATISTICS(40439, HttpStatus.NOT_FOUND, "반기 부서 업무 달성 통계가 존재하지 않습니다."),
    NOT_FOUND_MONTHLY_DEPARTMENT_OVERTIME_ALLOWANCE_STATISTICS(40440, HttpStatus.NOT_FOUND, "월간 부서 초과 근무 수당 통계가 존재하지 않습니다."),
    NOT_FOUND_MONTHLY_EMPLOYEE_NUM_STATISTICS(40441, HttpStatus.NOT_FOUND, "월간 사원 수 통계가 존재하지 않습니다."),
    NOT_FOUND_POSITION(40442, HttpStatus.NOT_FOUND, "직급 정보가 존재하지 않습니다."),
    NOT_FOUND_ROLE(40443, HttpStatus.NOT_FOUND, "역할 정보가 존재하지 않습니다."),
    NOT_FOUND_DUTY(40444, HttpStatus.NOT_FOUND, "직무 정보가 존재하지 않습니다."),
    NOT_FOUND_ATTENDANCE_STATUS_TYPE(40445, HttpStatus.NOT_FOUND, "출석 상태 유형이 존재하지 않습니다."),
    NOT_FOUND_DEPARTMENT(40446, HttpStatus.NOT_FOUND, "부서 정보가 존재하지 않습니다."),
    NOT_FOUND_COMPANY(40447, HttpStatus.NOT_FOUND, "회사 정보가 존재하지 않습니다."),

    //500
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

}