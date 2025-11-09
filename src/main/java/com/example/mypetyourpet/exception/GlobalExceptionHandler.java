package com.example.mypetyourpet.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorHandling> handleDataIntegrityViolation(DataIntegrityViolationException ex){
        String causeMessage = ex.getMostSpecificCause().getMessage();
        if(causeMessage == null && causeMessage.contains("petName_owner")){

        }
        ApiErrorHandling petCreateError = new ApiErrorHandling(
                "DUPLICATE",
                "This Pet is already listed",
                causeMessage
        );
        return new ResponseEntity<>(petCreateError, HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ApiErrorHandling> handleDuplicatedViolation(DataIntegrityViolationException ex){
//        ApiErrorHandling petCreateError = new ApiErrorHandling(
//                "DUPLICATE",
//                "This Pet is already listed",
//                ex.getMostSpecificCause().getMessage()
//        );
//        return new ResponseEntity<>(petCreateError, HttpStatus.BAD_REQUEST);
//
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorHandling> handleGeneric(Exception ex){
        ApiErrorHandling genError = new ApiErrorHandling(
                "INTERNAL_ERROR",
                "An unexpected error ocurred",
                ex.getMessage()
        );
        return new ResponseEntity<>(genError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
