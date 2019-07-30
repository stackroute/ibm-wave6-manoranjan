package com.stackroute.recommendationService.exception;

public class ViewerAlreadyExistException extends Exception {
    private String message1;

    public ViewerAlreadyExistException() {}

    public ViewerAlreadyExistException(String message)
    {
        super(message);
        this.message1=message;
    }
}
