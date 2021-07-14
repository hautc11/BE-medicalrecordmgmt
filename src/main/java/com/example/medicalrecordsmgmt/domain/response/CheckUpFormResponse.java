package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

@Data
public class CheckUpFormResponse {
    private int id;
    private String symptom;
    private DoctorResponse doctorResponse;
    private RecordResponse recordResponse;
}
