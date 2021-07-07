package com.example.medicalrecordsmgmt.domain.request;

import lombok.Data;

import java.sql.Date;


@Data
public class RecordCreateRequest {
    private String fullName;
    private Date dob;
    private Byte sex;
    private String address;
    private String phoneNumber;
}
