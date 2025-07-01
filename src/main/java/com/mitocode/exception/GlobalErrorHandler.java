package com.mitocode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException e, WebRequest webRequest){
        CustomErrorResponse resp = new CustomErrorResponse(LocalDateTime.now(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    /*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(MethodArgumentNotValidException e, WebRequest webRequest){
        CustomErrorResponse resp = new CustomErrorResponse(LocalDateTime.now(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = "";
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            message += fieldError.getField() + " : " + fieldError.getDefaultMessage();
        }

        CustomErrorResponse resp = new CustomErrorResponse(LocalDateTime.now(), message, request.getDescription(false));

        return new  ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
