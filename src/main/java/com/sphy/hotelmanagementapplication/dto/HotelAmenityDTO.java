package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;

/***
 * created by AKd
 */

public class HotelAmenityDTO implements Serializable {
	
	private Long id;
	private String hotelAmenities;
	
	
	public HotelAmenityDTO() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getHotelAmenities() {
		return hotelAmenities;
	}

	public void setHotelAmenities(String hotelAmenities) {
		this.hotelAmenities = hotelAmenities;
	}


	@Override
	public String toString() {
		return "HotelAmenityDTO [id=" + id + ", hotelAmenities=" + hotelAmenities + "]";
	}

}
