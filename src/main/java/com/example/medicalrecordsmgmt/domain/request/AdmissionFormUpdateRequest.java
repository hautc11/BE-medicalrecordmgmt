package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class AdmissionFormUpdateRequest {
    private int id;
    private Date dateIn;
    private Date dateOut;
    private int recordId;
}
