package com.week2.SpringRestCrud.advices;

import com.week2.SpringRestCrud.Exceptions.IntegerFormatException;
import com.week2.SpringRestCrud.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFountExceptionHandler(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        List<String> subErrors = exception.getBindingResult()
                .getAllErrors()
                .stream().map(error-> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder().message("given parameters are not valid")
                .subErrors(subErrors).status(HttpStatus.BAD_REQUEST).build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception){
        List<String> subErrors = List.of(exception.getLocalizedMessage());

        ApiError apiError = ApiError.builder()
                .message("given json format is not valid format")
                .subErrors(subErrors)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IntegerFormatException.class)
    public ResponseEntity<ApiError> integerFormatExceptionHandler(IntegerFormatException exception){
        List<String> subErrors = List.of(exception.getMessage());
        ApiError apiError = ApiError.builder()
                .message("we except only integers")
                .subErrors(subErrors)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
