package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class MedicineCreateRequest {
    private String name;
    private int quantities;
    private Date expirationDate;
}
