package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.Objects;

//import org.hibernate.mapping.Map;

/***
 * created by AKd
 */

@Entity
@Table(name="RAmenity")
@AttributeOverride(name = "id", column = @Column(name = "RAmenity_id"))
public class RoomAmenity extends BaseEntity{
	
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

	
	public RoomAmenity(AmenitiesRoom amenitiesR) {
		this.amenitiesR = amenitiesR;
	}// Constructor with fields
	
	public Long getId() {
		return super.getId();
	} // method getId()

	public void setId(Long id) {
		super.setId(id);
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
		if (!super.equals(o)) return false;
		RoomAmenity that = (RoomAmenity) o;
		return amenitiesR == that.amenitiesR;

	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), amenitiesR);
	}
	
	@Override
	public String toString() {
		return "RoomAmenity [id=" + super.getId() + ", amenitiesR=" + amenitiesR + "]";
	}
	
	
}// Class
