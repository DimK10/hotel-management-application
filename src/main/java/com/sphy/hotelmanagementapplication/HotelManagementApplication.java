package com.sphy.hotelmanagementapplication;

import com.sphy.hotelmanagementapplication.converter.BaseEntityConverter;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

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
