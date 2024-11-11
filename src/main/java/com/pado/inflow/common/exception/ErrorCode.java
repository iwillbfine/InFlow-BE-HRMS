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

    //403
    FORBIDDEN_ROLE(40300, HttpStatus.FORBIDDEN, "권한이 존재하지 않습니다."),
    ACCESS_DENIED(40310, HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),

    //404
    NOT_FOUND_USER(40401, HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    NOT_FOUND_STUDY_GROUP(40402, HttpStatus.NOT_FOUND, "스터디 그룹이 존재하지 않습니다."),
    NOT_FOUND_RECRUITMENT_BOARD(40403, HttpStatus.NOT_FOUND, "모집 게시판이 존재하지 않습니다."),
    NOT_FOUND_RECRUITMENT_BOARD_COMMENT(40404, HttpStatus.NOT_FOUND, "모집 게시판 댓글이 존재하지 않습니다."),
    NOT_FOUND_RECRUITMENT_BOARD_REPLY(40405, HttpStatus.NOT_FOUND, "모집 게시판 답글이 존재하지 않습니다."),
    NOT_FOUND_STUDY_GROUP_MEMBER(40406, HttpStatus.NOT_FOUND, "스터디 그룹 멤버가 존재하지 않습니다."),
    NOT_FOUND_STUDY_GROUP_BOARD(40407, HttpStatus.NOT_FOUND,    "스터디 그룹 게시판이 존재하지 않습니다."),
    NOT_FOUND_STUDY_GROUP_BOARD_COMMENT(40408, HttpStatus.NOT_FOUND, "스터디 그룹 게시판 댓글이 존재하지 않습니다."),
    NOT_FOUND_STUDY_GROUP_BOARD_REPLY(40409, HttpStatus.NOT_FOUND, "스터디 그룹 게시판 답글이 존재하지 않습니다."),
    NOT_FOUND_STUDY_SCHEDULE(40410, HttpStatus.NOT_FOUND, "스터디 일정이 존재하지 않습니다."),
    NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT(40411, HttpStatus.NOT_FOUND, "스터디 일정 참가자가 존재하지 않습니다."),
    NOT_FOUND_PROBLEM(40412, HttpStatus.NOT_FOUND, "문제가 존재하지 않습니다."),
    NOT_FOUND_CHOICE(40413, HttpStatus.NOT_FOUND, "문제 선택지가 존재하지 않습니다."),
    NOT_FOUND_SUBMITTED_ANSWER(40414, HttpStatus.NOT_FOUND, "제출된 답변이 존재하지 않습니다."),
    NOT_FOUND_STUDY_GROUP_NOTICE(40415, HttpStatus.NOT_FOUND, "스터디 그룹 공지가 존재하지 않습니다."),
    EMAIL_VERIFICATION_REQUIRED(40416, HttpStatus.BAD_REQUEST
            , "이메일 인증이 안된 이메일입니다. 이메일 인증을 완료해주세요."),

    //500
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

}