package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "rooms")
    private Set<Order> orders =new HashSet<>();

    private long price;

    private boolean disabled;



    public Room() {
    }

    public Room(Long id,String name, int luxurity, long price, boolean disabled) {
        this.name = name;
        this.luxurity = luxurity;
        id = super.getId();
        this.price = price;
        this.disabled = disabled;
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
        super.setId(id);;
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
                "id=" + this.getId() +
                "name='" + name + '\'' +
                ", luxurity=" + luxurity +
                ", hotel=" + hotel +
                ", orders=" + orders +
                ", price=" + price +
                ", disabled=" + disabled +
                '}';
    }
}
