package com.pado.inflow.employee.info.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.security.JwtUtil;
import com.pado.inflow.employee.security.dto.AuthTokens;
import com.pado.inflow.employee.security.dto.TokenRefreshRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("tokenController")
@RequestMapping("/api/auth")
@Slf4j
public class TokenController {

    private final JwtUtil jwtUtil;

    @Autowired
    public TokenController(JwtUtil jwtUtil) {
        this.jwtUtil=jwtUtil;
    }


    // 설명. 5. 리프레시 토큰으로 액세스 토큰 재발급
    @PostMapping("/refresh-token")
    public ResponseDTO<AuthTokens> refreshToken(@RequestBody TokenRefreshRequest request) {
        log.info("리프레시 토큰 api호출됨");
        // 서비스 계층에 로직 위임
        AuthTokens authTokens = jwtUtil.refreshAccessToken(request.getRefreshToken());
        return ResponseDTO.ok(authTokens);
    }
}
