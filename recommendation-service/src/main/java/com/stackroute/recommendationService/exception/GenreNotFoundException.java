package com.stackroute.recommendationService.exception;

public class GenreNotFoundException extends Exception {
    private String message1;

    public GenreNotFoundException() {}

    public GenreNotFoundException(String message)
    {
        super(message);
        this.message1=message;
    }
}
