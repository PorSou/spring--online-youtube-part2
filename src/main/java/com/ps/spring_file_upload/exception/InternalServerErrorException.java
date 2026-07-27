package com.ps.spring_file_upload.exception;

import com.ps.spring_file_upload.infrastructure.exception.BaseException;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
