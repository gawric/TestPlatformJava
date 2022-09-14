package com.server.testplatform.testplatform.exception;

public class NotFoundUserException extends Exception  {
    public NotFoundUserException(String errorMessage) {
        super(errorMessage);
    }
}