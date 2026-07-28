package com.ps.spring_file_upload.controller;

import com.ps.spring_file_upload.exception.BadRequestException;
import com.ps.spring_file_upload.exception.ConflictException;
import com.ps.spring_file_upload.exception.InternalServerErrorException;
import com.ps.spring_file_upload.exception.NotFoundException;
import com.ps.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.ps.spring_file_upload.infrastructure.model.body.StatusResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, @NonNull HttpHeaders headers, @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {
        return buildErrorResponse(statusCode, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class, InternalServerErrorException.class})
    public ResponseEntity<BaseBodyResponse> handleException(Exception ex) {
        return ResponseUtilFullUI.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<BaseBodyResponse> handleBadRequestException(BadRequestException ex) {
        return ResponseUtilFullUI.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<BaseBodyResponse> handleConflictException(ConflictException ex) {
        return ResponseUtilFullUI.buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<BaseBodyResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseUtilFullUI.buildErrorResponse(HttpStatus.NO_CONTENT, ex.getMessage());
    }

    // Override need
    private ResponseEntity<Object> buildErrorResponse(HttpStatusCode statusCode, String message) {

        BaseBodyResponse response = new BaseBodyResponse();

        StatusResponse status = new StatusResponse();
        status.setCode((short) statusCode.value());
        status.setMessage(message);

        response.setSuccess(false);
        response.setStatus(status);

        return ResponseEntity.status(statusCode).body(response);
    }
}