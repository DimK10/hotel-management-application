package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/***
 * created by gp
 */
@Entity(name = "rooms")
@DiscriminatorValue("rooms")
public class Room extends BaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "luxurity")
    private int luxurity;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch =  FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Order> orders =new HashSet<>();

    @OneToMany(mappedBy = "room", fetch =  FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<IntermediateRoomAmenity> intermediateRoomAmenities = new HashSet<>();

    private long price;

	private boolean disabled;

    private int capacity; // The capacity of people in the room

    public Room() {
    }



    public Room(Long id, String name, int luxurity, long price, boolean disabled) {
        super(id);
        this.name = name;
        this.luxurity = luxurity;
        this.price = price;
		this.disabled = disabled;
	}

    public Room(Long id) {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getLuxurity() {
        return luxurity;
    }

    public void setLuxurity(int luxurity) {
        this.luxurity = luxurity;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {

        super.setId(id);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> order) {
        this.orders = order;

    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<IntermediateRoomAmenity> getIntermediateRoomAmenities() {
        return intermediateRoomAmenities;
    }

    public void setIntermediateRoomAmenities(Set<IntermediateRoomAmenity> intermediateRoomAmenities) {
        this.intermediateRoomAmenities = intermediateRoomAmenities;
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
        return "Room{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", luxurity=" + luxurity +
                ", hotel=" + hotel +
//                ", orders=" + orders +
                ", intermediateRoomAmenities=" + intermediateRoomAmenities +
                ", price=" + price +
                ", disabled=" + disabled +
                ", capacity=" + capacity +
                '}';
    }
}
