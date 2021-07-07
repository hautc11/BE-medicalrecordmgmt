package com.example.medicalrecordsmgmt.domain.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class RecordResponse {
    private int id;
    private String fullName;
    private Date dob;
    private Byte sex;
    private String address;
    private String phoneNumber;
    private Timestamp createAt;
    private Timestamp expirationDate;
}
