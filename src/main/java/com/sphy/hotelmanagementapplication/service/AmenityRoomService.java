package com.sphy.hotelmanagementapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repositories.AmenityRoomRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity.AmenitiesRoom;


/***
 * created by AKd
 */

@Service
public class AmenityRoomService {
	@Autowired
	private AmenityRoomRepository amenityRoomRepository ;
	
	private RoomRepository roomRepository;

	public AmenityRoomService(AmenityRoomRepository amenityRoomRepository, RoomRepository roomRepository) {
		super();
		this.amenityRoomRepository = amenityRoomRepository;
		this.roomRepository = roomRepository;
	}
	
	/***
     * get all amenities for rooms
     * @return a list of all amenities for rooms
     */
	public List<RoomAmenity> getAllAmenitiesForRooms() {
		List<RoomAmenity> roomAmenity = new ArrayList<>();
		amenityRoomRepository.findAll().forEach(roomAmenity::add);
		return roomAmenity;
	}
	
	public Optional<RoomAmenity> getRoomAmenity(Long id) {
		return AmenityRoomRepository.findById(id);
	}
	
	public void addRoomAmenity(RoomAmenity amenitiesR) {
		amenityRoomRepository.save(amenitiesR);
	}
	
    /***
     * updates a room amenity
     */
	public void updateRoomAmenity(Long id, RoomAmenity amenitiesR) {
		amenityRoomRepository.save(amenitiesR);
	}
	
	
    /***
     * delete a room amenity
     */
	public void deleteRoomAmenity(Long id) {
		amenityRoomRepository.deleteById(id);
	}
	
	
	

}
