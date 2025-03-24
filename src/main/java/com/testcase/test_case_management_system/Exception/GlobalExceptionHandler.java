package com.testcase.test_case_management_system.Exception;

import com.testcase.test_case_management_system.Controller.TestCaseController;
import com.testcase.test_case_management_system.Dto.ExceptionResponse.ApiResponse;
import com.testcase.test_case_management_system.Dto.ExceptionResponse.ValidateErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @ExceptionHandler(value = TestCaseNotFoundException.class)
    public ResponseEntity<ApiResponse> handleTestCaseNotFoundException(TestCaseNotFoundException exception) {
        ApiResponse response = new ApiResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError err : exception.getBindingResult().getFieldErrors()) {
            errors.put(err.getField(), err.getDefaultMessage());
        }
        ValidateErrorResponse response = new ValidateErrorResponse();
        response.setMessage(errors);
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        ApiResponse response = new ApiResponse();
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        response.setMessage("Internal Server Error");
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
