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
    private long accessTokenExpiry;

    @JsonProperty("refresh_token_expiry")
    private long refreshTokenExpiry;

    @JsonProperty("user_auth_id")
    private String userAuthId;
}
