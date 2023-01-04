package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity.AmenitiesRoom;
import com.sphy.hotelmanagementapplication.dto.RoomAmenityDTO;
import org.springframework.stereotype.Component;

/***
 * created by AKd
 */

@Component
public class RoomAmenityDTOToRoomAmenity {
	
	public RoomAmenity converter(RoomAmenityDTO roomAmenityDTO) {
		
		RoomAmenity roomAmenity = new RoomAmenity();
		
		roomAmenity.setId(roomAmenityDTO.getId());
		
		roomAmenity.setAmenitiesR(AmenitiesRoom.valueOf(roomAmenityDTO.getRoomAmenities()));
		
		return roomAmenity;
		
	}
	
}
