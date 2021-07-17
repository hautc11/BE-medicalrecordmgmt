package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

import java.sql.Date;

@Data
public class MedicineResponse {
    private int id;
    private String name;
    private int quantities;
    private Date expirationDate;
}
