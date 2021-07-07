package com.example.medicalrecordsmgmt.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final String code;
    private final String message;

    public BadRequestException(ErrorCode error) {
        code = error.getCode();
        message = error.getMessage();
    }

    public BadRequestException(ErrorCode error, String customMessage) {
        code = error.getCode();
        message = customMessage;
    }
}
