package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

@Data
public class BillResponse {
    private int id;
    private double total;
    private int recordId;
}
