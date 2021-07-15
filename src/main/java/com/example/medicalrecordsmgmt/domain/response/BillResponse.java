package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BillResponse {
    private int id;
    private double total;
    private String service;
    private Timestamp createAt;
    private RecordResponse recordResponse;
}
