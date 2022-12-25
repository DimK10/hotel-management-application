package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;

/***
 * created by gp, AKd
 */
public class RoomDTO implements Serializable {

	private Long Id;

	private String name;

	private int luxurity;

	private Long hotel;

	private Set<OrderDTO> orders =new HashSet<>();

	private Set<RoomAmenityDTO> roomAmenityDTO = new HashSet<>();

	private long price;

	private boolean disabled;

	private int capacity;

	public RoomDTO() {
	}

    public RoomDTO(long l) {
    }

    public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLuxurity() {
		return luxurity;
	}

	public void setLuxurity(int luxurity) {
		this.luxurity = luxurity;
	}

	public Long getHotel() {
		return hotel;
	}

	public void setHotel(Long hotel) {
		this.hotel = hotel;
	}

	public Set<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDTO> orders) {
		this.orders = orders;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<RoomAmenityDTO> getRoomAmenityDTO() {
		return roomAmenityDTO;
	}

	public void setRoomAmenityDTO(Set<RoomAmenityDTO> roomAmenityDTO) {
		this.roomAmenityDTO = roomAmenityDTO;
	}

	@Override
	public String toString() {
		return "RoomDTO{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", luxurity=" + luxurity +
				", hotel=" + hotel +
				", orders=" + orders +
				", price=" + price +
				", disabled=" + disabled +
				", capacity=" + capacity +
				", roomAmenity=" + roomAmenityDTO + "}";
	}
}
