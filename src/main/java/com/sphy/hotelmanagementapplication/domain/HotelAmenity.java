package com.sphy.hotelmanagementapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/***
 * created by AKd,gp
 */
@Entity
@Table(name = "HAmenity")
@AttributeOverride(name = "id", column = @Column(name = "HAmenity_id"))
public class HotelAmenity extends BaseEntity {

	private String hAmenity;

	@OneToMany(mappedBy = "hotelAmenity", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<IntermediateHotelAmenity> intermediateHotelAmenities = new HashSet<>();

	public HotelAmenity() {
		
	}

	public HotelAmenity(String hAmenity) {

		this.hAmenity = hAmenity;
	}

	public String gethAmenity() {
		return hAmenity;
	}

	public void sethAmenity(String hAmenity) {
		this.hAmenity = hAmenity;
	}

	public Set<IntermediateHotelAmenity> getIntermediateHotelAmenities() {
		return intermediateHotelAmenities;
	}

	public void setIntermediateHotelAmenities(Set<IntermediateHotelAmenity> intermediateHotelAmenities) {
		this.intermediateHotelAmenities = intermediateHotelAmenities;
	}

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}


	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "HotelAmenity{" +
				"id=" + super.getId() +
				"hAmenity='" + hAmenity + '\'' +
				", intermediateHotelAmenities=" + intermediateHotelAmenities +
				'}';
	}
}// Class
