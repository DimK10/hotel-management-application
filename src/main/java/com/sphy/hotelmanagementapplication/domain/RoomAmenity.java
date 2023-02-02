package com.sphy.hotelmanagementapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.servlet.SessionTrackingMode;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//import org.hibernate.mapping.Map;

/***
 * created by AKd.gp
 */

@Entity
@Table(name="RAmenity")
@AttributeOverride(name = "id", column = @Column(name = "RAmenity_id"))
public class RoomAmenity extends BaseEntity {

	private String rAmenity;
	private boolean enabled;

	@OneToMany(mappedBy = "roomAmenity",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<IntermediateRoomAmenity> intermediateRoomAmenities = new HashSet<>();


	public RoomAmenity() {

	}

	public RoomAmenity(String rAmenity, boolean enabled) {

		this.rAmenity = rAmenity;
		this.enabled = enabled;
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
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;   
	}

	public Set<IntermediateRoomAmenity> getIntermediateRoomAmenities() {
		return intermediateRoomAmenities;
	}

	public void setIntermediateRoomAmenities(Set<IntermediateRoomAmenity> intermediateRoomAmenities) {
		this.intermediateRoomAmenities = intermediateRoomAmenities;
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
				"enabled='" + enabled + '\'' +
				", intermediateRoomAmenities=" + intermediateRoomAmenities +
				'}';
	}
}