package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HotelDTO implements Serializable {

    private Long id;

    private String name;

    private int stars;

    private String areaName;

    private Long owner;

    private boolean disabled;

    private List<RoomDTO> roomsDTO =new ArrayList<>();

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

    public List<RoomDTO> getRoomsDTO() {
        return roomsDTO;
    }

    public void setRoomsDTO(List<RoomDTO> roomsDTO) {
        this.roomsDTO = roomsDTO;
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
                ", roomsDTO=" + roomsDTO +
                '}';
    }
}
