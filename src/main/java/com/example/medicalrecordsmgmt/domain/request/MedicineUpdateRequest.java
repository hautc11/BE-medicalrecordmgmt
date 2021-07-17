package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class MedicineUpdateRequest {
    private int id;
    private String name;
    private int quantities;
    private Date expirationDate;
}
