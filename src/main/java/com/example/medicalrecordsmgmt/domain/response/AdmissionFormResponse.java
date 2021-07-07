package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

import java.sql.Date;

@Data
public class AdmissionFormResponse {
    private int id;
    private Date dateIn;
    private Date dateOut;
    private int recordId;
}
