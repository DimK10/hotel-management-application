package com.sphy.hotelmanagementapplication.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

//import org.hibernate.mapping.Map;

/***
 * created by AKd
 */

@Entity
@Table(name="amenityRoom")
public class RoomAmenity{
	
	@Id
	@Column(name="amenityRoom_id")
	private Long id;
	@Enumerated(EnumType.STRING)
	private AmenitiesRoom amenitiesR;
	
	public enum AmenitiesRoom {
	    FREEWIFI,
	    VIEWTOSEAMOUNTAIN,
	    AIRCONDITIONING,
	    FIREPLACE,
	    KITCHEN, 
	    REFRIGERATOR, 
	    MINIBAR, 
	    WASHINGMACHINE, 
	    COFFEETEAMACHINE,
	    TV, 
	    TOILETGRABRAILS,
	    BATHTUBGRABRAILS,
	    SHOWERCHAIR,
	    RAISEDCHAIR,
	    EMERGENCYPHONES,
	    SAFEDEPOSITBOX, 
	    BATHROBE, 
	    HAIRDRYER, 
	    BABYHIGHCHAIR;
	} // enum AmenitiesRoom
	
	

	public RoomAmenity() {
	
	}

	
	public RoomAmenity(Long id, AmenitiesRoom amenitiesR) {
		super();
		this.id = id;
		this.amenitiesR = amenitiesR;
	}// Constructor with fields
	
	public Long getId() {
		return id;
	} // method getId()

	public void setId(Long id) {
		this.id = id;
	} // method setId()

	public AmenitiesRoom getAmenitiesR() {
		return amenitiesR;
	} //method getAmenitiesR()
	
	public void setAmenitiesR(AmenitiesRoom amenitiesR) {
		this.amenitiesR = amenitiesR;
	} // method setAmenitiesR()
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoomAmenity that = (RoomAmenity) o;
		return Objects.equals(id, that.id);
	}// equals

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}// hashCode

	@Override
	public String toString() {
		return "RoomAmenity [id=" + id + ", amenitiesR=" + amenitiesR + "]";
	}// toString

}// Class
