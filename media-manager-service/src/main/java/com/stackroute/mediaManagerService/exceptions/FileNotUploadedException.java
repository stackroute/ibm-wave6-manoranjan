package com.stackroute.mediaManagerService.exceptions;

public class FileNotUploadedException extends Exception {
    private final String message;

    public FileNotUploadedException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
