package com.sphy.hotelmanagementapplication;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelManagementApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

				modelMapper
				.getConfiguration()
				.setFieldMatchingEnabled(true);


		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
