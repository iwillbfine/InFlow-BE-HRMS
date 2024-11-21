package com.pado.inflow.department.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyDTO {
    @JsonProperty("company_id")
    private Long companyId;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("ceo")
    private String ceo;

    @JsonProperty("business_registration_number")
    private String businessRegistrationNumber;

    @JsonProperty("company_address")
    private String companyAddress;

    @JsonProperty("company_phone_number")
    private String companyPhoneNumber;

    @JsonProperty("company_stamp_url")
    private String companyStampUrl;

    @JsonProperty("company_logo_url")
    private String companyLogoUrl;
}
