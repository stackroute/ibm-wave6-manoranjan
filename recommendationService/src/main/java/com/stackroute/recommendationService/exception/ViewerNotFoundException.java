package com.stackroute.recommendationService.exception;

public class ViewerNotFoundException extends Exception {
    private String message1;

    public ViewerNotFoundException() {}

    public ViewerNotFoundException(String message)
    {
        super(message);
        this.message1=message;
    }
}
