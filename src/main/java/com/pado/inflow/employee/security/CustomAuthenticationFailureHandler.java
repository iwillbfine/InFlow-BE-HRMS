package com.pado.inflow.employee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.error("로그인 실패: {}", exception.getMessage());

        // 403 응답 상태 코드 설정
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        // 실패 메시지 반환
        CommonException customException;
        if (exception.getMessage().equals("아이디가 잘못되었습니다.")) {
            customException = new CommonException(ErrorCode.NOT_FOUND_USER_ID); // 사용자 정의 에러코드로 설정
        } else if (exception.getMessage().equals("비밀번호가 틀렸습니다.")) {
            customException = new CommonException(ErrorCode.INVALID_PASSWORD); // 사용자 정의 에러코드로 설정
        } else if (exception.getMessage().equals("비활성화 회원입니다.")) {
            customException = new CommonException(ErrorCode.INACTIVE_USER); // 사용자 정의 에러코드로 설정
        }
        else {
            customException = new CommonException(ErrorCode.LOGIN_FAILURE); // 기본적으로 비밀번호 틀림 처리
        }

        ResponseDTO<Object> errorResponse = ResponseDTO.fail(customException);

        // JSON 형태로 반환
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
