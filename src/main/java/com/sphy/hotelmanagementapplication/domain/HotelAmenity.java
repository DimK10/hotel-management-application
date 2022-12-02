package com.sphy.hotelmanagementapplication.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * created by AKd
 */
@Entity
@Table(name = "HAmenity")
public class HotelAmenity {
	
	@Id
	@Column(name="amenityHotel_id")
	private Long id;
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

	public HotelAmenity(Long id, AmenitiesHotel amenitiesH) {
		//super();
		this.id = id;
		this.amenitiesH = amenitiesH;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AmenitiesHotel getAmenitiesH() {
		return amenitiesH;
	}

	public void setAmenitiesH(AmenitiesHotel amenitiesH) {
		this.amenitiesH = amenitiesH;
	}

	
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}// hashCode
	
	//@Override
	//public int hashCode() {
	//	return Objects.hash(amenitiesH, id);
	//}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HotelAmenity that = (HotelAmenity) o;
		return Objects.equals(id, that.id);
	}// equals
	
	//public boolean equals(Object obj) {
	//	if (this == obj)
	//		return true;
	//	if (obj == null)
	//		return false;
	//	if (getClass() != obj.getClass())
	//		return false;
	//	HotelAmenity other = (HotelAmenity) obj;
	//	return amenitiesH == other.amenitiesH && Objects.equals(id, other.id);
	//}

	@Override
	public String toString() {
		return "HotelAmenity [id=" + id + ", amenitiesH=" + amenitiesH + "]";
	}
	
}
