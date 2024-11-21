package com.pado.inflow.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 에러 상태별 메시지 정의 클래스
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400: 잘못된 요청 (Bad Request)
    WRONG_ENTRY_POINT(40000, HttpStatus.BAD_REQUEST, "잘못된 접근입니다"), // 사용자가 잘못된 URL로 접근했을 때 발생
    MISSING_REQUEST_PARAMETER(40001, HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다."), // 요청에 필요한 파라미터가 누락된 경우
    INVALID_PARAMETER_FORMAT(40002, HttpStatus.BAD_REQUEST, "요청에 유효하지 않은 인자 형식입니다."), // 파라미터 형식이 잘못된 경우
    BAD_REQUEST_JSON(40003, HttpStatus.BAD_REQUEST, "잘못된 JSON 형식입니다."), // JSON 요청 형식 오류
    DATA_INTEGRITY_VIOLATION(40005, HttpStatus.BAD_REQUEST,
            "데이터 무결성 위반입니다. 필수 값이 누락되었거나 유효하지 않습니다."), // 데이터베이스 무결성 위반 (예: NOT NULL 컬럼에 NULL 삽입 시도)
    INVALID_INPUT_VALUE(40010, HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다."), // 입력 값이 유효하지 않은 경우
    INVALID_REQUEST_BODY(40011, HttpStatus.BAD_REQUEST, "잘못된 요청 본문입니다."), // 요청 본문에 유효하지 않은 데이터가 포함된 경우
    MISSING_REQUIRED_FIELD(40012, HttpStatus.BAD_REQUEST, "필수 필드가 누락되었습니다."), // JSON 또는 요청 데이터에서 필수 필드가 누락된 경우
    INVALID_VERIFICATION_CODE(40013, HttpStatus.BAD_REQUEST, "잘못된 인증번호입니다. 인증번호를 다시 확인해주세요"), // 인증번호가 잘못되었거나 유효하지 않은 경우
    INSUFFICIENT_VACATION_DAYS(40014, HttpStatus.BAD_REQUEST, "휴가일수가 부족합니다."), // 휴가 신청 시 잔여 휴가일이 부족한 경우
    TASK_TYPE_UPDATE_FAILURE(40015, HttpStatus.BAD_REQUEST, "이미 해당 과제 유형이 사용중이므로 업데이트가 불가능합니다."),
    TASK_TYPE_DELETE_FAILURE(40016, HttpStatus.BAD_REQUEST,"해당 과제 유형이 사용중이므로 삭제할수 없습니다."),
    // 파일 관련 오류
    UNSUPPORTED_FILE_FORMAT(40020, HttpStatus.BAD_REQUEST, "지원되지 않는 파일 형식입니다."), // 업로드된 파일의 형식이 지원되지 않는 경우
    FILE_UPLOAD_ERROR(40021, HttpStatus.BAD_REQUEST, "파일 업로드에 실패했습니다."), // 파일 업로드 중 오류 발생
    FILE_CONVERSION_ERROR(40022, HttpStatus.BAD_REQUEST, "파일 변환에 실패했습니다."), // 업로드된 파일의 변환 작업 중 오류 발생
    FILE_SIZE_EXCEEDED(40023, HttpStatus.BAD_REQUEST, "파일 크기가 허용된 최대 크기를 초과했습니다."), // 파일 크기가 제한을 초과한 경우
    ALREADY_EXIST_POLICY_TYPE(40024, HttpStatus.BAD_REQUEST, "해당 과제 유형에 해당하는 평가 정책이 이미 존재합니다."),
    EXCEED_TOTAL_RATIO(40025, HttpStatus.BAD_REQUEST, "과제 반영 비율의 총 합은 100%를 넘길 수 없습니다."),
    INVALID_START_DATE(40026, HttpStatus.BAD_REQUEST, "평가 시작일은 현재 시점보다 미래여야 합니다."),
    INVALID_END_DATE(400027, HttpStatus.BAD_REQUEST, "평가 종료일은 현재 시점보다 미래여야 합니다."),
    INVALID_DATE_RANGE(40028, HttpStatus.BAD_REQUEST, "평가 종료일은 시작일보다 미래여야 합니다."),
    INVALID_MODIFIABLE_DATE(40029, HttpStatus.BAD_REQUEST, "평가 정책 수정 가능일은 현재 시점보다 미래여야 합니다."),
    INVALID_MODIFIABLE_DATE_RANGE(40030, HttpStatus.BAD_REQUEST, "평가 정책 수정 가능일은 평가 시작일보다 이전이어야 합니다."),
    POLICY_ALREADY_STARTED(40031, HttpStatus.BAD_REQUEST, "이미 시작된 평가 정책은 삭제할 수 없습니다."),
    POLICY_IN_USE(40032, HttpStatus.BAD_REQUEST, "과제 항목에서 참조 중인 평가 정책은 삭제할 수 없습니다."),
    FEEDBACK_CREATE_FAILURE(40033, HttpStatus.BAD_REQUEST, "평가 시작일 이전 및 종료일 이후에는 피드백을 생성할 수 없습니다"),
    DUPLICATED_FEEDBACK_CREATION(40034, HttpStatus.BAD_REQUEST, "해당 사원에 대한 피드백이 이미 존재합니다."),
    FEEDBACK_UPDATE_FAILURE(40035, HttpStatus.BAD_REQUEST, "평가 기간에만 피드백을 수정할 수 있습니다."),


    // 401: 인증 실패 (Unauthorized)
    INVALID_HEADER_VALUE(40100, HttpStatus.UNAUTHORIZED, "올바르지 않은 헤더값입니다."), // 헤더 값이 잘못되었거나 누락된 경우
    EXPIRED_TOKEN_ERROR(40101, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."), // 인증 토큰이 만료된 경우
    INVALID_TOKEN_ERROR(40102, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."), // 토큰이 잘못되었거나 위조된 경우
    TOKEN_MALFORMED_ERROR(40103, HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다."), // 토큰 구조가 올바르지 않은 경우
    TOKEN_TYPE_ERROR(40104, HttpStatus.UNAUTHORIZED, "토큰 타입이 일치하지 않거나 비어있습니다."), // 토큰의 타입이 잘못되었거나 누락된 경우
    TOKEN_UNSUPPORTED_ERROR(40105, HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰입니다."), // 서버가 지원하지 않는 토큰 유형
    TOKEN_GENERATION_ERROR(40106, HttpStatus.UNAUTHORIZED, "토큰 생성에 실패하였습니다."), // 토큰 생성 중 오류 발생
    TOKEN_UNKNOWN_ERROR(40107, HttpStatus.UNAUTHORIZED, "알 수 없는 토큰입니다."), // 알 수 없는 이유로 토큰이 유효하지 않은 경우
    LOGIN_FAILURE(40108, HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다"), // 로그인 실패
    UNAUTHORIZED_ACCESS(40110, HttpStatus.UNAUTHORIZED, "인증되지 않은 접근입니다."), // 인증되지 않은 사용자 접근
    EXPIRED_SESSION(40111, HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다."), // 사용자 세션이 만료된 경우
    EXIST_USER(40112, HttpStatus.UNAUTHORIZED, "이미 회원가입한 회원입니다."), // 이미 회원가입된 사용자
    NOT_FOUND_USER_ID(40113, HttpStatus.UNAUTHORIZED, "아이디를 잘못 입력하셨습니다."), // 잘못된 아이디 입력
    INVALID_PASSWORD(40114, HttpStatus.UNAUTHORIZED, "비밀번호를 잘못 입력하셨습니다."), // 비밀번호가 잘못된 경우

    // 403: 권한 부족 (Forbidden)
    FORBIDDEN_ROLE(40300, HttpStatus.FORBIDDEN, "요청한 리소스에 대한 권한이 없습니다."), // 사용자가 요청한 리소스에 대한 권한이 없는 경우
    ACCESS_DENIED(40310, HttpStatus.FORBIDDEN, "접근 권한이 거부되었습니다."), // 권한 부족으로 접근이 거부된 경우
    INACTIVE_USER(40320, HttpStatus.FORBIDDEN, "탈퇴한 회원입니다. 계정을 활성화 후 로그인해주세요."), // 탈퇴한 사용자가 리소스에 접근하려고 할 때
    INVALID_ENTERPRISE_ROLE(40330, HttpStatus.FORBIDDEN, "인사팀 전용 기능입니다. 접근이 제한되었습니다."), // 인사팀만 접근 가능한 리소스
    INVALID_ADMIN_ROLE(40331, HttpStatus.FORBIDDEN, "관리자 전용 기능입니다. 접근이 제한되었습니다."), // 관리자만 접근 가능한 리소스
    INVALID_MANAGER_ROLE(40340, HttpStatus.FORBIDDEN, "부장 전용 기능입니다. 접근이 제한되었습니다."), // 부장만 접근 가능한 리소스

    // 404: 리소스를 찾을 수 없음 (Not Found)
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

    // 409: 중복 데이터 (Conflict)
    DUPLICATE_ENTRY(40900,  HttpStatus.CONFLICT, "중복된 사원입니다."),
    DUPLICATE_TASK_TYPE(40901, HttpStatus.BAD_REQUEST, "중복된 과제 유형입니다."),

    // 429: 요청 과다 (Too Many Requests)
    TOO_MANY_REQUESTS(42900, HttpStatus.TOO_MANY_REQUESTS, "요청 횟수가 너무 많습니다. 잠시 후 다시 시도해 주세요."),

    // 500: 서버 내부 오류 (Internal Server Error)
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

}
