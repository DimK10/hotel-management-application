package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "rooms")
@DiscriminatorValue("rooms")
public class Room {
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
    private Set<Order> order=new HashSet<>();

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

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
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
                ", order=" + order +
                ", price=" + price +
                '}';
    }
}
