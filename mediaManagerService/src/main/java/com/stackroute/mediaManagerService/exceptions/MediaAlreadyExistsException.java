package com.stackroute.mediaManagerService.exceptions;

public class MediaAlreadyExistsException extends Exception {
    private String message;

    public MediaAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
