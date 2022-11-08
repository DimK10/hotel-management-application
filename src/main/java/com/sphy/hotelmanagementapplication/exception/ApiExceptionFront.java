package com.sphy.hotelmanagementapplication.exception;

/***
 * created by gp
 */
public class ApiExceptionFront extends RuntimeException{
    public ApiExceptionFront(String message) {
        super(message);
    }

    public ApiExceptionFront(String message, Throwable cause) {
        super(message, cause);
    }
}