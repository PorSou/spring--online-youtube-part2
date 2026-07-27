package com.ps.spring_file_upload.controller;

import com.ps.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.ps.spring_file_upload.infrastructure.model.body.BodyResponse;
import com.ps.spring_file_upload.infrastructure.model.body.StatusResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseUtilFullUI {

    /**
     * Build standard success response
     */
    public static ResponseEntity<Object> buildSuccessResponse(Object data, String message) {

        BaseBodyResponse response = new BaseBodyResponse();

        StatusResponse status = new StatusResponse();
        status.setCode((short) 200);
        status.setMessage(message);

        BodyResponse body = new BodyResponse();
        body.setData(data);

        response.setSuccess(true);
        response.setStatus(status);
        response.setBody(body);

        return ResponseEntity.ok(response);
    }

    /**
     * Build standard error response
     */

    public static ResponseEntity<Object> buildErrorResponse(HttpStatusCode statusCode, String message) {

        BaseBodyResponse response = new BaseBodyResponse();

        StatusResponse status = new StatusResponse();
        status.setCode((short) statusCode.value());
        status.setMessage(message);

        response.setSuccess(false);
        response.setStatus(status);

        return ResponseEntity.status(statusCode).body(response);
    }

}
