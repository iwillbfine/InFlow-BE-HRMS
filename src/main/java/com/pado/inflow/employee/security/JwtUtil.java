package com.pado.inflow.employee.security;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.application.service.EmployeeCommandService;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import com.pado.inflow.employee.security.dto.AuthTokens;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Component
public class JwtUtil {

    private final Key secretKey;
    private final long accessExpirationTime;
    private final long refreshExpirationTime;
    private final EmployeeCommandService employeeService;
    private final EmployeeRepository employeeRepository;

    public JwtUtil(
            @Value("${token.secret}") String secretKey,
            @Value("${token.access-expiration-time}") long accessExpirationTime,
            @Value("${token.refresh-expiration-time}") long refreshExpirationTime,
            EmployeeCommandService employeeService,
            EmployeeRepository employeeRepository
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
        this.employeeService = employeeService;
        this.employeeRepository=employeeRepository;
    }

    // 설명. 리프레시 토큰으로 액세스 토큰 재발급하는 로직 처리
    public AuthTokens refreshAccessToken(String refreshToken) {
        if (!validateToken(refreshToken)) {
            throw new CommonException(ErrorCode.EXPIRED_TOKEN_ERROR);
        }

        String employeeNumber = getEmployeeNumber(refreshToken);

        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        String role = employee.getEmployeeRole().name();
        String newAccessToken = generateToken(employee, Collections.singletonList(role));
        log.info("employee: ", employee);

        return new AuthTokens(
                newAccessToken,
                refreshToken,
                "Bearer",
                getAccessTokenExpiration(),
                getRefreshTokenExpiration(),
                employee.getEmployeeId(),
                employeeNumber
        );
    }

    // 설명. Token 검증 메소드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token {}", e.getMessage());
            throw new CommonException(ErrorCode.INVALID_TOKEN_ERROR);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token {}", e.getMessage());
            throw new CommonException(ErrorCode.EXPIRED_TOKEN_ERROR);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token {}", e.getMessage());
            throw new CommonException(ErrorCode.TOKEN_UNSUPPORTED_ERROR);
        } catch (IllegalArgumentException e) {
            log.info("JWT Token claims empty {}", e.getMessage());
            throw new CommonException(ErrorCode.TOKEN_MALFORMED_ERROR);
        }
    }

    // 설명. Token에서 인증 객체 추출
    public Authentication getAuthentication(String token) {
        String employeeNumber = this.getEmployeeNumber(token);
        UserDetails userDetails  = employeeService.loadUserByUsername(employeeNumber);

        Claims claims = parseClaims(token);
        log.info("넘어온 AccessToken claims 확인: {}", claims);

        String authClaim = claims.get("auth") != null ? claims.get("auth").toString() : null;
        if (authClaim == null || authClaim.trim().isEmpty()) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities
                = Arrays.stream(authClaim.replace("[", "").replace("]", "").split(", "))
                .map(String::trim)
                .filter(role -> !role.isEmpty()) // 빈 문자열 제거
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    // 설명. Token에서 Claims 추출
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    // 설명. Token에서 employeeNumber 추출
    public String getEmployeeNumber(String token) {
        return parseClaims(token).getSubject();
    }

    // 설명. 액세스 토큰 생성 메소드
    public String generateToken(Employee employee, List<String> roles) {
        return Jwts.builder()
                .setSubject(employee.getEmployeeNumber()) // 사번(employeeNumber)로 식별
                .claim("auth", roles)
                .claim( "employeeId",employee.getEmployeeId())
                .claim("employeeNumber", employee.getEmployeeNumber())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // 설명. 액세스 토큰 만료 시간 가져오기
    public long getAccessTokenExpiration() {
        return System.currentTimeMillis() + accessExpirationTime;
    }

    // 설명. 리프레시 토큰 만료 시간 가져오기
    public long getRefreshTokenExpiration() {
        return System.currentTimeMillis() + refreshExpirationTime;
    }
}
