package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.Objects;

//import org.hibernate.mapping.Map;

/***
 * created by AKd.gp
 */

@Entity
@Table(name="RAmenity")
@AttributeOverride(name = "id", column = @Column(name = "RAmenity_id"))
public class RoomAmenity extends BaseEntity {

	private String rAmenity;


	public RoomAmenity() {

	}

	public RoomAmenity(Long id, String rAmenity) {
		super(id);
		this.rAmenity = rAmenity;
	}

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public String getrAmenity() {
		return rAmenity;
	}

	public void setrAmenity(String rAmenity) {
		this.rAmenity = rAmenity;
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
		return "RoomAmenity{" +
				"id=" + super.getId() +
				"rAmenity='" + rAmenity + '\'' +
				'}';
	}
}