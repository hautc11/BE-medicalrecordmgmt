package com.example.medicalrecordsmgmt.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
    BAD_REQUEST("Your request is too bad :)!"),
    INVALID_ID("Id không tồn tại!"),
    INVALID_DEPARTMENT_ID("Mã Khoa không tồn tại trong cơ sở dữ liệu!"),
    INVALID_DOCTOR_ID("Mã Bác Sĩ không tồn tại trong cơ sở dữ liệu!"),
    EXISTED_ACCOUNT("This account already existed!"),
    INVALID_EMAIL_PASSWORD("Sai tài khoản hoặc mật khẩu!"),
    SERVER_ERROR("Server gặp vấn đề!"),
    INVALID_MEDICINE_ID("Mã Thuốc không tồn tại trong cơ sở dữ liệu!"),
    INVALID_CHECKUP_ID("Mã Phiếu Khám không tồn tại trong cơ sở dữ liệu!"),
    INVALID_MEDICAL_RECORD_ID("Mã Hồ Sơ BN không tồn tại trong cơ sở dữ liệu!");
    private final String message;

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }
}
