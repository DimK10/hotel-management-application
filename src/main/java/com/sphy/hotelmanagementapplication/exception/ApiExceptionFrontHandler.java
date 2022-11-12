package com.sphy.hotelmanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/***
 * created by gp
 */
@RestControllerAdvice
public class ApiExceptionFrontHandler {

    @ExceptionHandler(value = {ApiExceptionFront.class})
    public ResponseEntity<Object> handleApiRequestException(ApiExceptionFront e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
