package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserAllReadyExistException.class)
    public ResponseEntity<?> handleUserAllReadyExistException(UserAllReadyExistException e) {
        return new ResponseEntity<String>("User Already exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<String>("User Not Found", HttpStatus.CONFLICT);
    }
}
