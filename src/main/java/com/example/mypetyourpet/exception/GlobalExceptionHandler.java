package com.example.mypetyourpet.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex){
        String causeMessage = ex.getMostSpecificCause().getMessage();
//        if(causeMessage == null && causeMessage.contains("petName_owner")){
//
//        }
//        ApiErrorHandling petCreateError = new ApiErrorHandling(
//                "DUPLICATE",
//                "This Pet is already listed",
//                causeMessage
//        );
//        return new ResponseEntity<>(petCreateError, HttpStatus.BAD_REQUEST);
//        String userMessage = "This Pet is already listed";

        // Optional: refine message based on cause
//        if (causeMessage != null && causeMessage.contains("petName_owner")) {
//            userMessage = "A pet with this name is already registered for this owner.";
//        }

        return ResponseEntity.badRequest().body(Map.of("error", causeMessage));

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
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex){
//        ApiErrorHandling genError = new ApiErrorHandling(
//                "INTERNAL_ERROR",
//                "An unexpected error ocurred",
//                ex.getMessage()
//        );
//        return new ResponseEntity<>(genError, HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An unexpected error occurred: " + ex.getMessage()));

    }
}
