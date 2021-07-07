package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class DoctorCreateRequest {
    private String fullName;
    private String phoneNumber;
    private int departmentId;
}
