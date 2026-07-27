package com.ps.spring_file_upload.exception;

import com.ps.spring_file_upload.infrastructure.exception.BaseException;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }
}
