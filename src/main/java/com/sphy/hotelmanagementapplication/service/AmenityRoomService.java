package com.sphy.hotelmanagementapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sphy.hotelmanagementapplication.repository.AmenityRoomRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;


/***
 * created by AKd
 */

@Service
public class AmenityRoomService {


	private AmenityRoomRepository amenityRoomRepository ;
	
	private RoomRepository roomRepository;

	public AmenityRoomService(AmenityRoomRepository amenityRoomRepository, RoomRepository roomRepository) {
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
		return amenityRoomRepository.findById(id);
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
