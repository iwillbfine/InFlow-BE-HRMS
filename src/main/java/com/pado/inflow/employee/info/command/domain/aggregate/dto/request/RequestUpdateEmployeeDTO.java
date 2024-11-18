package com.pado.inflow.employee.info.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestUpdateEmployeeDTO {

    @JsonProperty("email") // 이메일
    private String email;

    @JsonProperty("phone_number") // 전화번호
    private String phoneNumber;

    @JsonProperty("profile_img_url") // 프로필 이미지 URL
    private String profileImgUrl;

    @JsonProperty("street_address") // 거리 주소
    private String streetAddress;

    @JsonProperty("detailed_address") // 상세 주소
    private String detailedAddress;

}