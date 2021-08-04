package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

@Data
public class BillCreateRequest {
    private double price;
    private double discount;
    private double total;
    private String service;
    private int recordId;
}
