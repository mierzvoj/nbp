package com.pjatk.nbpApp.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiError> handle4xx(HttpClientErrorException ex){
        HttpStatus statusCode = ex.getStatusCode();
        System.out.println(ex);
        return ResponseEntity.status(statusCode)
        .body(new ApiError(statusCode, ex.getResponseBodyAsString()));
    }

}
