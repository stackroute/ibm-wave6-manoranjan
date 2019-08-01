package com.stackroute.exception;

public class EmailIdNotFoundException extends Exception{
    private String message;
    public EmailIdNotFoundException(){}
    public EmailIdNotFoundException(String message){
        super(message);
        this.message=message;
    }
}
