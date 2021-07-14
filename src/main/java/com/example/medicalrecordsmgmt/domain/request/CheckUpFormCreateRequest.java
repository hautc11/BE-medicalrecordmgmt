package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class CheckUpFormCreateRequest {
    private String symptom;
    private int recordId;
    private int doctorId;
}
