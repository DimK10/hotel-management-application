package com.sphy.hotelmanagementapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;


@SpringBootApplication
public class HotelManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new Pbkdf2PasswordEncoder();
	}
}
