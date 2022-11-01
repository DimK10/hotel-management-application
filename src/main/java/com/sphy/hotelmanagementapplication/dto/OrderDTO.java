package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDTO implements Serializable {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long client;

    private Long room;

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

	@Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", client=" + client +
                ", room=" + room +
                '}';
    }
}
