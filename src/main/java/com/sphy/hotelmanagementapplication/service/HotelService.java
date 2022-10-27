package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;

import org.springframework.stereotype.Service;

@Service
public class HotelService {

	private final HotelRepository hotelRepository;

	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	public Hotel findById(Long id) {
		return hotelRepository.findById(id).orElse(null);
	}
}
