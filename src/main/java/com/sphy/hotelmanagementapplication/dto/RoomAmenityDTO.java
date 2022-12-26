package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;

/***
 * created by AKd
 */

public class RoomAmenityDTO implements Serializable {
	
	private Long id;
	private String roomAmenities;
	

	public RoomAmenityDTO() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoomAmenities() {
		return roomAmenities;
	}


	public void setRoomAmenities(String roomAmenities) {
		this.roomAmenities = roomAmenities;
	}


	@Override
	public String toString() {
		return "RoomAmenityDTO [id=" + id + ", roomAmenities=" + roomAmenities + "]";
	}


}
