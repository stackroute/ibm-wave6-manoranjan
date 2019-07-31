package com.stackroute.recommendationService.exception;

public class LanguageNotFoundException extends Exception {
    private String message1;

    public LanguageNotFoundException() {
    }

    public LanguageNotFoundException(String message) {
        super(message);
        this.message1 = message;
    }
}
