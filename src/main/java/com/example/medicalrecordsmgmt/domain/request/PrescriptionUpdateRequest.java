package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class PrescriptionUpdateRequest {
    private int id;
    private int medicineId;
    private int quantities;
    private int checkupId;
}
