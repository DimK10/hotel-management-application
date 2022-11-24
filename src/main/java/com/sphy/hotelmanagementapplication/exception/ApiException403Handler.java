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
public class ApiException403Handler {

    @ExceptionHandler(value = {ApiException403.class})
    public ResponseEntity<Object> handleApiRequestException(ApiException403 e){

        HttpStatus forbidden = HttpStatus.FORBIDDEN;

        ApiException apiException = new ApiException(
                e.getMessage(),
                forbidden,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, forbidden);
    }
}
