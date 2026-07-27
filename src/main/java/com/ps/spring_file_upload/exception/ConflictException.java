package com.ps.spring_file_upload.exception;

import com.ps.spring_file_upload.infrastructure.exception.BaseException;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }
}
