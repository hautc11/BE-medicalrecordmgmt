package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class RecordUpdateRequest {
    private int id;
    private String fullName;
    private Date dob;
    private Byte sex;
    private String address;
    private String phoneNumber;
}
