package com.sphy.hotelmanagementapplication.dto;

import com.sphy.hotelmanagementapplication.domain.BaseEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/***
 * created by gp
 */
public class HotelDTO implements Serializable{

    private Long id;

    private String name;

    private int stars;

    private String areaName;

    private Long owner;

    private boolean disabled;

    private Set<RoomDTO> rooms =new HashSet<>();

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


    @Override
    public String toString() {
        return "HotelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", areaName='" + areaName + '\'' +
                ", owner=" + owner +
                ", disabled=" + disabled +
                ", rooms=" + rooms +
                '}';
    }
}
