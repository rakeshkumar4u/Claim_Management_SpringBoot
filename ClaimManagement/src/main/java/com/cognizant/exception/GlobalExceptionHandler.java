
package com.cognizant.exception;
 
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Centralized Exception handling for REST Controller
@RestControllerAdvice
public class GlobalExceptionHandler {

// Methods that will handle special type of exception during processing of HTTP Request
    @ExceptionHandler(InvalidPolicyException.class)
    public ResponseEntity<String> handleInvalidPolicyException(InvalidPolicyException ex ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(InvalidSurveyorException.class)
    public ResponseEntity<String> handleInvalidSurveyorException(InvalidSurveyorException ex ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
 
    @ExceptionHandler(MaximumClaimLimitReachedException.class)
    public ResponseEntity<String> handleMaximumClaimLimitReachedException(MaximumClaimLimitReachedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
 
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
 
    @ExceptionHandler(NoEligibleSurveyorException.class)
    public ResponseEntity<String> handleNoEligibleSurveyorException(NoEligibleSurveyorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
      
    //Exception Handling for Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) { 
    Map<String,String> resp = new HashMap<>(); 
    ex.getBindingResult().getAllErrors().forEach((error) -> {  
    String fieldName = ((FieldError) error).getField();    
    String message=error.getDefaultMessage();     
    resp.put(fieldName, message);   
    });   
    return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);   
    }   
}