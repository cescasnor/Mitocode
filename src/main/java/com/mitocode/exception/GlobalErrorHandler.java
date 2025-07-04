package com.mitocode.exception;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllException (Exception ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return  new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException e, WebRequest webRequest){
        CustomErrorResponse resp = new CustomErrorResponse(LocalDateTime.now(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
    */

    //Desde Spring Boot 3
    @ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail hanndleModelNotFoundException (ModelNotFoundException ex, WebRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Model not found Exception");
        pd.setType(URI.create(request.getContextPath()));
        pd.setProperty("var1" , "value1");//Podemos agregar cualquier valor adicional
        return pd;
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

        String message = ex.getBindingResult().getFieldErrors().stream().map( e -> e.getField() + " : " + e.getDefaultMessage()).collect(Collectors.joining(","));

        CustomErrorResponse resp = new CustomErrorResponse(LocalDateTime.now(), message, request.getDescription(false));

        return new  ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }


}
