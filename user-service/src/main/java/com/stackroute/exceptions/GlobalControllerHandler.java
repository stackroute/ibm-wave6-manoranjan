package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerHandler {
    //user already exists exception handler
    @ExceptionHandler(UserAllReadyExistException.class)
    public ResponseEntity<?> handleUserAllReadyExistException(UserAllReadyExistException e) {
        return new ResponseEntity<>("User Already exist", HttpStatus.CONFLICT);
    }

    //user not found exception handler
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>("User Not Found", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<?> handleDataAlreadyExistException(DataAlreadyExistException e) {
        return new ResponseEntity<String>("Data Already exist", HttpStatus.CONFLICT);
    }
}
