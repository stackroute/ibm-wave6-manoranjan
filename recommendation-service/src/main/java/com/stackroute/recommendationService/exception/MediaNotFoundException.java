package com.stackroute.recommendationService.exception;

public class MediaNotFoundException extends Exception {
    private String message1;

    public MediaNotFoundException() {}

    public MediaNotFoundException(String message)
    {
        super(message);
        this.message1=message;
    }
}
