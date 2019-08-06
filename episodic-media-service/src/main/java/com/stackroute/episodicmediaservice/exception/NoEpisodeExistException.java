package com.stackroute.episodicmediaservice.exception;

public class NoEpisodeExistException extends Exception {

    private final String message;

    public NoEpisodeExistException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
