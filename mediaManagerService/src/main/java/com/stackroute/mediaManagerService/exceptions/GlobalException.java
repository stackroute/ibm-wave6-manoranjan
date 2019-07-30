package com.stackroute.mediaManagerService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MediaNotFoundException.class)
    public ResponseEntity<?> handleMediaNotFoundException(MediaNotFoundException mediaNotFound){
        return new ResponseEntity<String>("Media not found", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MediaAlreadyExistsException.class)
    public ResponseEntity<?> handleMediaAlreadyExistsException(MediaAlreadyExistsException mediaAlreadyExists){
        return new ResponseEntity<String>("Media already exists",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception){
        return new ResponseEntity<>("Exception Occured",HttpStatus.CONFLICT);
    }

}
