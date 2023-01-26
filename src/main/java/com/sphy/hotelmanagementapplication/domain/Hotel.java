package com.sphy.hotelmanagementapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/***
 * created by gp
 */
@Entity
@Table(name = "hotels")
@NamedEntityGraph(name = "Hotel.rooms",
		attributeNodes = @NamedAttributeNode("rooms")
)
public class Hotel extends BaseEntity {


    @Column(name = "name")
    private String name;
    @Column(name = "stars")
    private int stars;
    @Column(name = "area_name")
    private String areaName;

    private String address;

    private boolean disabled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "hotel", fetch =  FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Room> rooms = new HashSet<>();


    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<IntermediateHotelAmenity> intermediateHotelAmenities = new HashSet<>();


    public Hotel(Long id) {
        super(id);
    }

    public Hotel() {
    }

    public Hotel(Long id, String name, int stars, String areaName, boolean disabled) {
		super(id);
        this.name = name;
        this.stars = stars;
        this.areaName = areaName;
        this.disabled = disabled;
    }

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getAddress() {
        return address;
    }

    public Set<IntermediateHotelAmenity> getIntermediateHotelAmenities() {
        return intermediateHotelAmenities;
    }

    public void setIntermediateHotelAmenities(Set<IntermediateHotelAmenity> intermediateHotelAmenities) {
        this.intermediateHotelAmenities = intermediateHotelAmenities;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Hotel{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", disabled=" + disabled +
                ", owner=" + owner +
//                ", rooms=" + rooms +
                ", intermediateHotelAmenities=" + intermediateHotelAmenities +
                '}';
    }
}
