package com.stackroute.recommendationService.exception;

public class MediaAlreadyExistException extends Exception {
    private String message1;

    public MediaAlreadyExistException() {
    }

    public MediaAlreadyExistException(String message) {
        super(message);
        this.message1 = message;
    }
}
