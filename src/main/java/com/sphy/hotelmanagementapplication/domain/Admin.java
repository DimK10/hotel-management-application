package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "admins")
public class Admin extends User {

    @OneToMany(mappedBy = "owner")
    private Set<Hotel> hotels = new HashSet<>();


    public Admin() {
    }

    public Admin(Long id, boolean emailVerify, String username, String firstname, String lastname, String email) {
        super(id, emailVerify, username, firstname, lastname, email, Role.ADMIN);
    }

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
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
        return "Admin{" +
				"id=" + this.getId() +
//                "hotels=" + hotels +
                '}';
    }
}
