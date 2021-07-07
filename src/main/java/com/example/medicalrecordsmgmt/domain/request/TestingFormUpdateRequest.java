package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class TestingFormUpdateRequest {
    private int id;
    private String testName;
    private Date testDate;
    private Byte result;
    private int recordId;
}
