package com.pado.inflow.employee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.application.service.EmployeeCommandService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
//필기. JwtFilter가 OncePerRequestFilter를 상속 받는 이유는 doFilterInternal를 오버라이딩 한다.
// (한번만 실행되는 필터)

public class JwtFilter extends OncePerRequestFilter {

    private final EmployeeCommandService employeeService;
    private final JwtUtil jwtUtil;

    public JwtFilter(EmployeeCommandService employeeService, JwtUtil jwtUtil) {
        this.employeeService = employeeService;
        this.jwtUtil = jwtUtil;
    }

    /*설명. 들고 온(Request Header) 토큰이 유효한지 판별 및 인증(Authentication 객체로 관리)*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("UsernamePasswordAuthenticationFilter보다 먼저 동작하는 필터");

        String requestURI = request.getRequestURI();
        log.info("Request URI: {}", requestURI);

        // 로그인 요청은 필터를 통과시킴
        if ("/api/login".equals(requestURI) || "/api/auth/refresh-token".equals(requestURI)) {
            log.info("로그인/리프레시 재발급 요청은 필터를 통과합니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);

        // Authorization 헤더가 null이거나 Bearer로 시작하지 않는 경우
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("Authorization header가 없거나 잘못된 형식입니다.");

            // 401 상태 코드와 에러 응답 반환
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            CommonException customException = new CommonException(ErrorCode.TOKEN_TYPE_ERROR);
            ResponseDTO<Object> errorResponse = ResponseDTO.fail(customException);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
            response.getWriter().write(jsonResponse);

            return; // 필터 체인 종료
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            log.info("토큰 값: " + token);

            try {
                if (jwtUtil.validateAccessToken(token)) {
                    Authentication authentication = jwtUtil.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("JwtFilter를 통과한 유효한 토큰을 통해 security가 관리할 principal 객체: {}", authentication);
                }
            } catch (CommonException ex) {
                // 예외를 잡고 ResponseDTO 형식으로 응답
                response.setStatus(ex.getErrorCode().getHttpStatus().value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");  // 응답 인코딩을 UTF-8로 설정

                ResponseDTO<Object> errorResponse = ResponseDTO.fail(ex);
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(errorResponse);

                response.getWriter().write(jsonResponse);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

