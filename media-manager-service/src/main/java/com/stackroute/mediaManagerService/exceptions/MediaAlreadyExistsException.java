package com.stackroute.mediaManagerService.exceptions;

public class MediaAlreadyExistsException extends Exception {
    private final String message;

    public MediaAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
