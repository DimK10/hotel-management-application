package com.sphy.hotelmanagementapplication.dto;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/***
 * created by gp
 */
public class HotelDTO implements Serializable {

    private Long id;

    private String name;

    private int stars;

    private String areaName;

    private String address;

    private String description;

    private Long owner;

    private boolean disabled;

    private Set<RoomDTO> rooms = new HashSet<>();

    private Set<HotelAmenity> amenities = new HashSet<>();

    public HotelDTO(long id) {
        this.id = id;
    }

    public HotelDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Set<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDTO> rooms) {
        this.rooms = rooms;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<HotelAmenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<HotelAmenity> amenities) {
        this.amenities = amenities;
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
        return "HotelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", disabled=" + disabled +
                ", rooms=" + rooms +
                ", amenities=" + amenities +
                '}';
    }
}
