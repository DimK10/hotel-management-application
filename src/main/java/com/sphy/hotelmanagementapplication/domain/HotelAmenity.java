package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.Objects;

/***
 * created by AKd
 */
@Entity
@Table(name = "HAmenity")
@AttributeOverride(name = "id", column = @Column(name = "HAmenity_id"))
public class HotelAmenity extends BaseEntity {
		
	@Enumerated(EnumType.STRING)
	private AmenitiesHotel amenitiesH;
	
	public enum AmenitiesHotel {
		PARKING,
		RESTAURANT,
		ROOMSERVICE,
		GYM,
		SPA,
		POOL,
		CHARGINGSTATION,
		PETSALLOWED,
		AIRPORTTRANSPORT,
		WHEELCHAIRRAMPS,
		ROOMSACCESSIBLEELEVATOR;
	} // enum AmenitiesHotel

	public HotelAmenity() {
		
	}

	public HotelAmenity(AmenitiesHotel amenitiesH) {
		this.amenitiesH = amenitiesH;
	}// Constructor with fields

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public AmenitiesHotel getAmenitiesH() {
		return amenitiesH;
	}

	public void setAmenitiesH(AmenitiesHotel amenitiesH) {
		this.amenitiesH = amenitiesH;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		HotelAmenity that = (HotelAmenity) o;
		return amenitiesH == that.amenitiesH;

	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), amenitiesH);
	}

	@Override
	public String toString() {
		return "HotelAmenity [id=" + super.getId() + ", amenitiesH=" + amenitiesH + "]";
	}
	
}// Class
