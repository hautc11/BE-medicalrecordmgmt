package com.example.medicalrecordsmgmt.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
    BAD_REQUEST("Your request is too bad :)!"),
    INVALID_ID("Your request id is not valid!"),
    INVALID_DEPARTMENT_ID("The department ID is not valid!"),
    INVALID_DOCTOR_ID("The doctor ID is not valid!"),
    EXISTED_ACCOUNT("This account already existed!"),
    INVALID_EMAIL_PASSWORD("Invalid email or password!"),
    SERVER_ERROR("Internal Server Error!"),
    INVALID_MEDICINE_ID("The medicine id is not valid"),
    INVALID_CHECKUP_ID("The checkup id is not valid"),
    INVALID_MEDICAL_RECORD_ID("The medical record id is not valid");
    private final String message;

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }
}
