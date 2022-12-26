package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.dto.HotelAmenityDTO;
import org.springframework.stereotype.Component;

/***
 * created by AKd
 */

@Component
public class HotelAmenityToHotelAmenityDTO {
	
	
	
	 public HotelAmenityDTO converter(HotelAmenity hotelAmenity) {
		 
        HotelAmenityDTO hotelAmenityDTO = new HotelAmenityDTO();
        
        hotelAmenityDTO.setId(hotelAmenity.getId());
       
        hotelAmenityDTO.setHotelAmenities(String.valueOf(hotelAmenity.getAmenitiesH()));
        
        return hotelAmenityDTO;
    }

}
