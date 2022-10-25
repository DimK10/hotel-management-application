package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "admins")
public class Admin extends User{

    @OneToMany(mappedBy = "owner")
    private Set<Hotel> hotels = new HashSet<>();


    public Admin() {
    }

    public Admin(boolean emailVerify, String username, String firstname, String lastname, String email) {
        super(emailVerify, username, firstname, lastname, email, Role.ADMIN);
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "hotels=" + hotels +
                '}';
    }
}
