package com.sphy.hotelmanagementapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import java.time.LocalDate;


/**
 * Created by Akd
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public void sendEmail(String toEmail, String hotelName, LocalDate checkInDate, LocalDate checkOutDate, Long price){

        String subject = "Order Confirmation";
        String text = "Your order has been saved successfully.\n"
                      + "Order details:\n"
                      + "Hotel: " + hotelName + "\n"
                      + "Check-in date: "+ checkInDate + "\n"
                      + "Check-out date: " + checkOutDate + "\n"
                      + "Total price is: " + price + "\n\n"
                      + "Thank you for choosing our hotel!";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

       emailSender.send(message);
    }
}
