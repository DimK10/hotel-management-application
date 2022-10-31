package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomDTO implements Serializable {

	private Long Id;

	private String name;

	private int luxurity;

	private Long hotelDTO;

	private List<OrderDTO> ordersDTO =new ArrayList<>();

	private long price;

	private boolean disabled;


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

	public Long getHotelDTO() {
		return hotelDTO;
	}

	public void setHotelDTO(Long hotelDTO) {
		this.hotelDTO = hotelDTO;
	}

	public List<OrderDTO> getOrdersDTO() {
		return ordersDTO;
	}

	public void setOrdersDTO(List<OrderDTO> ordersDTO) {
		this.ordersDTO = ordersDTO;
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

	@Override
	public String toString() {
		return "RoomDTO{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", luxurity=" + luxurity +
				", hotel=" + hotelDTO +
				", ordersDTO=" + ordersDTO +
				", price=" + price +
				", disabled=" + disabled +
				'}';
	}
}
