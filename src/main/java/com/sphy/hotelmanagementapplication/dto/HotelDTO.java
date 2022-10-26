package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.Objects;

public class HotelDTO implements Serializable {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HotelDTO hotelDTO = (HotelDTO) o;
		return Objects.equals(id, hotelDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
