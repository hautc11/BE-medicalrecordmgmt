package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;
    private String password;

}