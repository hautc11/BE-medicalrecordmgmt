package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class BillUpdateRequest {
    private int id;
    private double total;
    private int recordId;
}
