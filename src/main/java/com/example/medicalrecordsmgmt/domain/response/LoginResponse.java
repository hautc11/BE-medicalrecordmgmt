package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String tokenType = "Bearer";
    private String accessToken;
}
