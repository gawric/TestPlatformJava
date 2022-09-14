package com.server.testplatform.testplatform.variable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public  class ErrorMessage {

    public static ResponseEntity<Object> getResponceError(String errorName)
    {
        return new ResponseEntity(HttpStatus.FORBIDDEN)
                .status(HttpStatus.FORBIDDEN)
                .body("Error Message: " + errorName);
    }

    public static ResponseEntity<Object> getResponceErrorHttpStatus(String errorName , HttpStatus status)
    {
        return new ResponseEntity(status)
                .status(status)
                .body("Error Message: " + errorName);
    }


}
