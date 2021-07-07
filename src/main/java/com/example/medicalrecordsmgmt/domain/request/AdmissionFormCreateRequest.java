package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class AdmissionFormCreateRequest {
    private Date dateIn;
    private Date dateOut;
    private int recordId;
}
