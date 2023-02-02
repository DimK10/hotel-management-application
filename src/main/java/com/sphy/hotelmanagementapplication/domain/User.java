package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/***
 * crated by gp
 */
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity {


	private boolean emailVerify;

	private String username;

	private String firstname;

	private String lastname;

	private String email;

	private String hashedPassword;

	private boolean enabled;

	@Transient
	protected String password;

	public enum Role {
		CLIENT, ADMIN, SUPERUSER
	}

	@Enumerated(EnumType.STRING)
	protected Role role;

	@OneToMany(mappedBy = "owner")
	private Set<Hotel> hotels = new HashSet<>();

	@OneToMany(mappedBy = "client")
	private Set<Order> orders = new HashSet<>();


	public User() {
		super();
	}

	public User(Long id) {
		super(id);
	}

	public User(Long id, boolean emailVerify, String username, String firstname, String lastname, String email, String password, boolean enabled, Role role, Set<Hotel> hotels, Set<Order> orders) {
		super(id);
		this.emailVerify = emailVerify;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.hotels = hotels;
		this.orders = orders;
	}

	


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;   
	}

	public boolean isEmailVerify() {
		return emailVerify;
	}

	public void setEmailVerify(boolean emailVerify) {
		this.emailVerify = emailVerify;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
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
		return "User{" +
				"id=" + super.getId() +
				"emailVerify=" + emailVerify +
				", username='" + username + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				", hashedPassword='" + hashedPassword + '\'' +
				", enabled='" + enabled + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
//				", hotels=" + hotels +
//				", orders=" + orders +
				'}';
	}
}
