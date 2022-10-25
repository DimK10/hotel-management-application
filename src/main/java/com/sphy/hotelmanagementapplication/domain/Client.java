package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "clients")
public class Client extends User{

    @OneToMany(mappedBy = "client")
    private Set<Order> orders=new HashSet<>();

    public Client() {
    }

    public Client(boolean emailVerify, String username, String firstname, String lastname, String email) {
        super(emailVerify, username, firstname, lastname, email, Role.CLIENT);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "orders=" + orders +
                '}';
    }
}
