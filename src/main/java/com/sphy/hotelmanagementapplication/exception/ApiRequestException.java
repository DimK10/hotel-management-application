package com.sphy.hotelmanagementapplication.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
