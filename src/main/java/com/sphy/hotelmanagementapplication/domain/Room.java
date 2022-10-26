package com.sphy.hotelmanagementapplication.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "rooms")
@DiscriminatorValue("rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
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



    public Room() {
    }

    public Room(String name, int luxurity, long price) {
        this.name = name;
        this.luxurity = luxurity;

        this.price = price;
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
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return Id.equals(room.Id);
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

	@Override
	public String toString() {
		return "Room{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", luxurity=" + luxurity +
				", hotel=" + hotel +
				", order=" + orders +
				", price=" + price +
				'}';
	}
}
