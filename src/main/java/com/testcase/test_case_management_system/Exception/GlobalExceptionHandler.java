package com.testcase.test_case_management_system.Exception;

import com.testcase.test_case_management_system.Dto.ApiResponse;
import com.testcase.test_case_management_system.Dto.CommonException;
import com.testcase.test_case_management_system.Dto.ErrorResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiResponse response = new ApiResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError err : exception.getBindingResult().getFieldErrors()) {
            errors.put(err.getField(), err.getDefaultMessage());
        }
        ErrorResponse response = new ErrorResponse();
        response.setMessage(errors);
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<ApiResponse> handleCommonException(CommonException ex) {
        ApiResponse response = new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
