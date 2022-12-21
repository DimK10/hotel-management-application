package com.sphy.hotelmanagementapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.repositories.AmenityHotelRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;

/***
 * created by AKd
 */


public class AmenityHotelService {
	
	private AmenityHotelRepository amenityHotelRepository;
	private HotelRepository hotelRepository;
	
	public AmenityHotelService(AmenityHotelRepository amenityHotelRepository, HotelRepository hotelRepository) {
		this.amenityHotelRepository = amenityHotelRepository;
		this.hotelRepository = hotelRepository;
	}
	
	/***
     * get all amenities for hotels
     * @return a list of all amenities for hotels
     */
	public List<HotelAmenity> getAllAmenitiesForHotels() {
		
		List<HotelAmenity> hotelAmenity = new ArrayList<>();
		amenityHotelRepository.findAll().forEach(hotelAmenity::add);
		return hotelAmenity;
	}
	
	public Optional<HotelAmenity> getHotelAmenity(Long id) {
		return amenityHotelRepository.findById(id);
	}
	
	public void addHotelAmenity(HotelAmenity amenitiesH) {
		amenityHotelRepository.save(amenitiesH);
	}
	
    /***
     * updates a hotel amenity
     */
	
	public void updateHotelAmenity(Long id, HotelAmenity amenitiesH) {
		amenityHotelRepository.save(amenitiesH);
	}
	
	
    /***
     * delete a hotel amenity
     */
	
	public void deleteHotelAmenity(Long id) {
		amenityHotelRepository.deleteById(id);
	}
}

	
	


