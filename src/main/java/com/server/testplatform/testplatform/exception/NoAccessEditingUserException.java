package com.server.testplatform.testplatform.exception;

public class NoAccessEditingUserException extends Exception{
    public NoAccessEditingUserException(String errorMessage) {
        super(errorMessage);
    }
}
