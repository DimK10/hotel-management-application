package com.sphy.hotelmanagementapplication.dto;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/***
 * created by gp
 */
public class OrderDTO implements Serializable {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long client;

    private Long room;

    private Long price;

    private String roomName;

    private String hotelName;

    @ElementCollection
    private Set<String> hotelAmenities = new HashSet<>();

    @ElementCollection
    private Set<String> roomAmenities = new HashSet<>();

    private boolean canceled;

    public OrderDTO() {
    }

    public OrderDTO(long l) {
        this.id = l;
    }


    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public Long getRoom() {
		return room;
	}

	public void setRoom(Long room) {
		this.room = room;
	}

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Set<String> getHotelAmenities() {
        return hotelAmenities;
    }

    public void setHotelAmenities(Set<String> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public Set<String> getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(Set<String> roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return canceled == orderDTO.canceled && Objects.equals(id, orderDTO.id) && Objects.equals(checkInDate, orderDTO.checkInDate) && Objects.equals(checkOutDate, orderDTO.checkOutDate) && Objects.equals(client, orderDTO.client) && Objects.equals(room, orderDTO.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkInDate, checkOutDate, client, room, canceled);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", client=" + client +
                ", room=" + room +
                ", price=" + price +
                ", roomName='" + roomName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAmenities=" + hotelAmenities +
                ", roomAmenities=" + roomAmenities +
                ", canceled=" + canceled +
                '}';
    }
}
