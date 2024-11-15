package com.pado.inflow.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.common.exception.ExceptionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Data
public class ResponseDTO<T> {

    @JsonIgnore
    private HttpStatus httpStatus;

    @NotNull
    private boolean success;

    @Nullable
    private T content;  // 'data'를 'content'로 변경

    @Nullable
    private ExceptionDTO error;

    // 기본 생성자
    public ResponseDTO() {
    }

    // 모든 필드를 받는 생성자
    public ResponseDTO(HttpStatus httpStatus, boolean success, @Nullable T content, @Nullable ExceptionDTO error) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.content = content;
        this.error = error;
    }

    // static 팩토리 메소드
    public static <T> ResponseDTO<T> ok(T content) {
        return new ResponseDTO<>(
                HttpStatus.OK,
                true,
                content,
                null
        );
    }

    public static ResponseDTO<Object> fail(@NotNull CommonException e) {
        return new ResponseDTO<>(
                e.getErrorCode().getHttpStatus(),
                false,
                null,
                ExceptionDTO.of(e.getErrorCode())
        );
    }

    public static ResponseDTO<Object> fail(final MissingServletRequestParameterException e) {
        return new ResponseDTO<>(
                HttpStatus.BAD_REQUEST,
                false,
                null,
                ExceptionDTO.of(ErrorCode.MISSING_REQUEST_PARAMETER)
        );
    }

    public static ResponseDTO<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new ResponseDTO<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                false,
                null,
                ExceptionDTO.of(ErrorCode.INVALID_PARAMETER_FORMAT)
        );
    }
}