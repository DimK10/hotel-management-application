package com.sphy.hotelmanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/***
 * created by gp
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ApiException403 extends RuntimeException{

    public ApiException403(String message) {
        super(message);
    }

    public ApiException403(String message, Throwable cause) {
        super(message, cause);
    }
}
