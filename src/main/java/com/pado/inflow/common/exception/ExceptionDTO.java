package com.pado.inflow.common.exception;

import lombok.Getter;

//필기. 에러 응답 형식(코드,메시지)
@Getter
public class ExceptionDTO {
    private final Integer code;
    private final String message;
    public ExceptionDTO(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ExceptionDTO of(ErrorCode errorCode){
        return new ExceptionDTO(errorCode);
    }
}
