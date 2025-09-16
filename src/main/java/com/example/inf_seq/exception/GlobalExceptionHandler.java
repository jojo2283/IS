package com.example.inf_seq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleUser(UserAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }


}
