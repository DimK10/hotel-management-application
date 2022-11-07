package com.sphy.hotelmanagementapplication.exception;



/***
 * created by gp
 */
public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
