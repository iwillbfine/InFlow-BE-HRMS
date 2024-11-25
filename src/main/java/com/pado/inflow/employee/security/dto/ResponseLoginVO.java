package com.pado.inflow.employee.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginVO {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("access_token_expiry")
    private Date accessTokenExpiry;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("refresh_token_expiry")
    private Date refreshTokenExpiry;

    @JsonProperty("employeeId")
    private Long employee_id;

    @JsonProperty("employeeNumber")
    private String employee_number;
}
