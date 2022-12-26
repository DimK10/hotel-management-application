package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity.AmenitiesHotel;
import com.sphy.hotelmanagementapplication.dto.HotelAmenityDTO;
import org.springframework.stereotype.Component;

/***
 * created by AKd
 */

@Component
public class HotelAmenityDTOToHotelAmenity {
			
		public HotelAmenity converter (HotelAmenityDTO hotelAmenityDTO) {
			
			HotelAmenity hotelAmenity = new HotelAmenity();
			
			hotelAmenity.setId(hotelAmenityDTO.getId());
			
			hotelAmenity.setAmenitiesH(AmenitiesHotel.valueOf(hotelAmenityDTO.getHotelAmenities()));
			
			return hotelAmenity;
			
		}
	}

	

