package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

@Data
public class DoctorResponse {
    private int id;
    private String fullName;
    private String phoneNumber;
    private DepartmentResponse department;
}
