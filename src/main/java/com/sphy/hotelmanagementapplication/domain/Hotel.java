package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "hotels")

public class Hotel extends BaseEntity {


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

    public Hotel(Long id, String name, int stars, String areaName) {
		super(id);

        this.name = name;
        this.stars = stars;
        this.areaName = areaName;
    }

	public void setId(Long id) {
		super.setId(id);
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
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
    public String toString() {
        return "Hotel{" +
                "id=" + super.getId() +

                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", areaName='" + areaName + '\'' +
                ", owner=" + owner +
//                ", rooms=" + rooms +
                '}';
    }
}
