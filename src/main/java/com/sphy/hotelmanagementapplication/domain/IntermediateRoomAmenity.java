package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/***
 * created by gp , AKd
 */
@Entity
public class IntermediateRoomAmenity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private RoomAmenity roomAmenity;

    public IntermediateRoomAmenity() {
    }

    public IntermediateRoomAmenity(Room room, RoomAmenity roomAmenity) {
        this.room = room;
        this.roomAmenity = roomAmenity;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomAmenity getRoomAmenity() {
        return roomAmenity;
    }

    public void setRoomAmenity(RoomAmenity roomAmenity) {
        this.roomAmenity = roomAmenity;
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
        return "IntermediateRoomAmenity{" +
                "id=" + super.getId() +
                "room=" + room +
                ", roomAmenity=" + roomAmenity +
                '}';
    }
}
