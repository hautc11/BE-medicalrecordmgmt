package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.MedicalRecord;
import lombok.Data;

import java.sql.Date;

@Data
public class AdmissionFormResponse {
    private int id;
    private Date dateIn;
    private Date dateOut;
    private RecordResponse recordResponse;
}
