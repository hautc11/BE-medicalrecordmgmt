package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class BillCreateRequest {
    private double total;
    private int recordId;
}