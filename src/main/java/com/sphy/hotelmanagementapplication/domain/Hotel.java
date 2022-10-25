package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "hotels")

public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", updatable = false, nullable = false)
    private Long Id;

    @Column(name = "name")
    private String name;
    @Column(name = "stars")
    private int stars;
    @Column(name = "area_name")
    private String areaName;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin owner;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {
    }

    public Hotel(String name, int stars, String areaName) {
        this.name = name;
        this.stars = stars;
        this.areaName = areaName;
    }

    public Admin getOwner() {
        return owner;
    }

    public void setOwner(Admin owner) {
        this.owner = owner;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return Id.equals(hotel.Id);
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", areaName='" + areaName + '\'' +
                ", owner=" + owner +
                ", rooms=" + rooms +
                '}';
    }
}
