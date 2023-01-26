package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "user_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rooms_id")
    private Room room;

    private Long price;

    private String roomName;

    private String hotelName;

    @ElementCollection
    private Set<String> hotelAmenities = new HashSet<>();

    @ElementCollection
    private Set<String> roomAmenities = new HashSet<>();

    public Order() {
    }


    public Order(Long id, LocalDate checkInDate, LocalDate checkOutDate, boolean canceled, User client, Room room, String roomName, String hotelName, Long price) {
		super(id);
		this.checkInDate = checkInDate;
        this.room = room;
        this.checkOutDate = checkOutDate;
        this.canceled = canceled;
        this.client = client;
        this.roomName = roomName;
        this.hotelName = hotelName;
        this.price = price;
    }

    public Order(long l) {
        super(l);
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

    public User getClient() {
        return client;
    }

    public void setClient(User User) {
        this.client = User;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", canceled=" + canceled +
                ", client=" + client +
//                ", room=" + room +
                ", price=" + price +
                ", roomName='" + roomName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAmenities=" + hotelAmenities +
                ", roomAmenities=" + roomAmenities +
                '}';
    }
}
