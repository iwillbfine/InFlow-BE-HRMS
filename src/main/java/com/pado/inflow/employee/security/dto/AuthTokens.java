package com.pado.inflow.employee.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token_expiry")
    private Long accessTokenExpiry;

    @JsonProperty("refresh_token_expiry")
    private Long refreshTokenExpiry;

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("employee_number")
    private String employeeNumber;
}
