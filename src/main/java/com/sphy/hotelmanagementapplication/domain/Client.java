package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/***
 * created by gp
 */
@Entity(name = "clients")
public class Client extends User {


    @OneToMany(mappedBy = "client")
    private Set<Order> orders=new HashSet<>();

    public Client() {
    }

    public Client(Long id, boolean emailVerify, String username, String firstname, String lastname, String email) {
        super(id, emailVerify, username, firstname, lastname, email, Role.CLIENT);
    }

    public Client(Long id) {
        super.setId(id);
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

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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
        return "Client{" +
				"id=" + this.getId() +
//                "orders=" + orders +
                '}';
    }
}
