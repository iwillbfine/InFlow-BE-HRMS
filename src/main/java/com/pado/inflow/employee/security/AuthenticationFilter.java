package com.pado.inflow.employee.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.application.service.EmployeeCommandService;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import com.pado.inflow.employee.info.enums.ResignationStatus;
import com.pado.inflow.employee.security.dto.RequestLoginVO;
import com.pado.inflow.employee.security.dto.ResponseLoginVO;
import com.pado.inflow.payroll.common.Common;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    private final EmployeeCommandService employeeService;
    private final EmployeeRepository employeeRepository;
    private final Environment env;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 추가

    public AuthenticationFilter(AuthenticationManager authenticationManager,
//                                EmployeeCommandService employeeService,
                                EmployeeRepository employeeRepository,
                                Environment env,
                                BCryptPasswordEncoder bCryptPasswordEncoder) { // 추가
        super(authenticationManager);
//        this.employeeService = employeeService;
        this.employeeRepository=employeeRepository;
        this.env = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder; // 필드 초기화
    }

    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }


    /*설명. 스프링 시큐리티는 BadCredentialsException로 에러를 잡을 수 있다.
                필터는 서블릿 디스패치 이전에 실행되므로 필터에서 에러가 발생한다면
                커스텀 에러를 발생시킬수 없다. 따라서 필터에서 에러가 발생하면 그것을
                BadCredentialsException로 잡고, 이를 AuthenticationFailureHandler에서
                처리한다. 이를 커스텀하게 해서 응답값을 json으로 하면 된다.
         */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 1. 요청 데이터 파싱
            log.info("로그인 요청 데이터 수신 중...");
            RequestLoginVO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginVO.class);
            log.info("로그인 요청 데이터: {}", creds);

            // 설명. EmployeeNumber로 사원 조회
            String employeeNumber = creds.getEmployeeNumber();
            log.info("사용자 조회 중: userAuthId = {}", employeeNumber);

            // 2. 사용자 조회 및 예외 처리 (employeeNumber를 기준으로 조회)
            Employee loginEmployee = employeeRepository.findByEmployeeNumber(employeeNumber)
                    .orElseThrow(() -> {
                        log.error("아이디가 잘못되었습니다. employeeNumber = {}", employeeNumber);
                        return new BadCredentialsException("아이디를 잘못 입력하셨습니다.");
                    });

            log.info("사용자 조회 성공: {}",loginEmployee);

            // 4. 사용자 퇴사 여부 확인
            if (loginEmployee.getResignationStatus() == ResignationStatus.Y) {
                log.error("퇴사한 사원 입니다. employeeNumber = {}", loginEmployee);
                throw new BadCredentialsException("퇴사한 사원 입니다."); // 퇴사한 사원에 대한 예외
            }

            // 5. 비밀번호 체크
            log.info("비밀번호 검증 중...");
            if (!bCryptPasswordEncoder.matches(creds.getPassword(), loginEmployee.getPassword())) {
                log.error("비밀번호가 틀렸습니다. loginEmployee = {}", loginEmployee);
                throw new BadCredentialsException("비밀번호를 잘못 입력하셨습니다."); // 비밀번호가 틀린 경우 예외 처리
            }

            // 6. 인증 토큰 생성
            log.info("인증 토큰 생성 중...");
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(employeeNumber, creds.getPassword(), new ArrayList<>());

            authToken.setDetails(creds);

            log.info("인증 토큰 생성 완료. 인증 처리 중...");
            return getAuthenticationManager().authenticate(authToken);
        } catch (IOException e) {
            log.error("요청 데이터를 읽는 중 오류 발생", e);
            throw new AuthenticationServiceException("요청 데이터를 읽는 중 오류 발생", e);
        } catch (AuthenticationException e) {
            log.error("인증 처리 중 오류 발생: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("로그인 성공하고 security가 관리하는 principal객체(authResult): {}", authResult);

        // 사용자 인증 정보 및 식별자 생성
        String employeeNumber = ((User) authResult.getPrincipal()).getUsername();

        // 사용자 정보 조회 후 id 반환
        Employee loginEmployee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));
        
        // Claims 및 역할 정보 설정
        Claims claims = Jwts.claims().setSubject(employeeNumber);
        List<String> roles = authResult.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        claims.put("auth", roles);
        claims.put("employeeId", loginEmployee.getEmployeeId());

        // 만료 시간 설정
        long accessExpiration = System.currentTimeMillis() + getExpirationTime(env.getProperty("token.access-expiration-time"));
        long refreshExpiration = System.currentTimeMillis() + getExpirationTime(env.getProperty("token.refresh-expiration-time"));

        // 액세스 토큰 생성
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(accessExpiration))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // 리프레시 토큰 생성
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(refreshExpiration))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // 로그인 응답 객체 생성
        ResponseLoginVO loginResponseVO = new ResponseLoginVO(
                accessToken,
                new Date(accessExpiration),
                refreshToken,
                new Date(refreshExpiration),
                loginEmployee.getEmployeeId(),
                employeeNumber
        );

        // 응답 객체를 JSON 형태로 반환
        ResponseDTO<ResponseLoginVO> responseDTO = ResponseDTO.ok(loginResponseVO);
        String jsonResponse = new ObjectMapper().writeValueAsString(responseDTO);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }


    private long getExpirationTime(String expirationTime) {
        if (expirationTime == null) {
            // 기본 만료 시간을 설정합니다. 예를 들어, 1시간(3600000ms)으로 설정
            return 3600000;
        }
        return Long.parseLong(expirationTime);
    }

}