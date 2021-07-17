package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

@Data
public class PrescriptionResponse {
    private int id;
    private MedicineResponse medicine;
    private CheckUpFormResponse checkupForm;
    private int quantities;
}
