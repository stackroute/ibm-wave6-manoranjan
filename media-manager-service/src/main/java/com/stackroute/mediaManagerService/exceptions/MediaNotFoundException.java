package com.stackroute.mediaManagerService.exceptions;

public class MediaNotFoundException extends Exception {
    private final String message;

    public MediaNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
