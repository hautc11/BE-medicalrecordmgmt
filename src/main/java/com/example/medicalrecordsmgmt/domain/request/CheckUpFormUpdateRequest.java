package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class CheckUpFormUpdateRequest {
    private int id;
    private String symptom;
    private int recordId;
    private int doctorId;
}
