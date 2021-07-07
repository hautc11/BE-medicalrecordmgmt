package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

import java.sql.Date;

@Data
public class TestingFormResponse {
    private int id;
    private String testName;
    private Date testDate;
    private Byte result;
    private int recordId;
}
