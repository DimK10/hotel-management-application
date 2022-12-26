package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.dto.RoomAmenityDTO;
import org.springframework.stereotype.Component;

/***
 * created by AKd
 */

@Component
public class RoomAmenityToRoomAmenityDTO {
	
	public RoomAmenityDTO converter(RoomAmenity roomAmenity) {
		
		RoomAmenityDTO roomAmenityDTO = new RoomAmenityDTO();
		
		roomAmenityDTO.setId(roomAmenity.getId());
		
		roomAmenityDTO.setRoomAmenities(String.valueOf(roomAmenity.getAmenitiesR()));
		
		return roomAmenityDTO;
	}

}
