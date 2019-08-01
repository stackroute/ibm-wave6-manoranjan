package com.stackroute.mediaManagerService.exceptions;

public class MediaNotFoundException extends Exception {
    private String message;

    //media not found exception method
    public MediaNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
