package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.time.LocalDate;

/***
 * created by gp
 */
@Table(name = "orders")
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "check_in")
    private LocalDate checkInDate;

    @Column(name = "check_out")
    private LocalDate checkOutDate;

    @Column(name = "is_canceled")
    private boolean canceled;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rooms_id")
    private Room room;


    public Order() {
    }


    public Order(Long id, LocalDate checkInDate, LocalDate checkOutDate, boolean canceled, Client client, Room room) {
		super(id);
		this.checkInDate = checkInDate;
        this.room = room;
        this.checkOutDate = checkOutDate;
        this.canceled = canceled;
        this.client=client;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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
