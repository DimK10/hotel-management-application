package com.sphy.hotelmanagementapplication.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApiRequestExceptionProd {

    public ApiRequestExceptionProd(String message) {
        message = "The command you wont to execute it can't be done";
    }

}
