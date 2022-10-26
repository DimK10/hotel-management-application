package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private Room rooms;


    public Order() {
    }


    public Order(Long id, LocalDate checkInDate, LocalDate checkOutDate,Client client) {
		super(id);
		this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.client=client;
    }


	public void setId(Long id) {
		super.setId(id);
	}

    public Long getId() {
       return super.getId();
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRooms() {
        return rooms;
    }

    public void setRooms(Room rooms) {
        this.rooms = rooms;
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
        return "Order{" +
                "Id=" + this.getId() +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", client=" + client +
//                ", rooms=" + rooms +
                '}';
    }
}
