package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class DoctorUpdateRequest {
    private int id;
    private String fullName;
    private String phoneNumber;
    private int departmentId;
}
