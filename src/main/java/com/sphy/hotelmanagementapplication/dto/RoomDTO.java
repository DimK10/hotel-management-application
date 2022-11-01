package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomDTO implements Serializable {

	private Long Id;

	private String name;

	private int luxurity;

	private Long hotel;

	private Set<OrderDTO> orders =new HashSet<>();

	private long price;

	private boolean disabled;


	public RoomDTO() {
	}

	public RoomDTO(Long id, String name) {
		Id = id;
		this.name = name;
	}

	public RoomDTO(Long id, String name, int luxurity, Long hotel, Set<OrderDTO> orders, long price, boolean disabled) {
		Id = id;
		this.name = name;
		this.luxurity = luxurity;
		this.hotel = hotel;
		this.orders = orders;
		this.price = price;
		this.disabled = disabled;
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
				'}';
	}
}