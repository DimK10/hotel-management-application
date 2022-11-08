package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/***
 * created by gp
 */
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean disabled;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rooms_id")
    private Room room;


    public Order() {
    }


    public Order(Long id, LocalDate checkInDate, LocalDate checkOutDate, boolean disabled, Client client) {
		super(id);
		this.checkInDate = checkInDate;

        this.checkOutDate = checkOutDate;
        this.disabled = disabled;
        this.client=client;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
