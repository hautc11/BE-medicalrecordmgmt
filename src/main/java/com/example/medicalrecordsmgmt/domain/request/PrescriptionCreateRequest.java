package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class PrescriptionCreateRequest {
    private int medicineId;
    private int quantities;
    private int checkupId;
}
