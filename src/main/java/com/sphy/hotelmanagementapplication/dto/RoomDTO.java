package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoomDTO implements Serializable {

	private Long Id;

	private String name;

	private int luxurity;

	private Long hotel;

	private Set<Long> orders =new HashSet<>();

	private long price;

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

	public Set<Long> getOrders() {
		return orders;
	}

	public void setOrders(Set<Long> orders) {
		this.orders = orders;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoomDTO roomDTO = (RoomDTO) o;
		return Objects.equals(Id, roomDTO.Id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public String toString() {
		return "RoomDTO{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", luxurity=" + luxurity +
				", hotel=" + hotel +
				", order=" + orders +
				", price=" + price +
				'}';
	}
}
