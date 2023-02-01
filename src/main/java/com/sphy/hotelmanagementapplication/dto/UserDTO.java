package com.sphy.hotelmanagementapplication.dto;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/***
 * created by gp
 */
public class UserDTO implements Serializable{

    private Long id;

    private boolean emailVerify;

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private String hashedPassword;

    private String role;
    
    private boolean enabled;

    @Transient
    private String password;

	private Set<HotelDTO> hotelDTOS = new HashSet<>();

	private Set<OrderDTO> orderDTOS = new HashSet<>();

    public UserDTO() {
    }

	public UserDTO(Long id) {
		this.id = id;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public Set<HotelDTO> getHotelDTOS() {
		return hotelDTOS;
	}

	public void setHotelDTOS(Set<HotelDTO> hotelDTOS) {
		this.hotelDTOS = hotelDTOS;
	}

	public Set<OrderDTO> getOrderDTOS() {
		return orderDTOS;
	}

	public void setOrderDTOS(Set<OrderDTO> orderDTOS) {
		this.orderDTOS = orderDTOS;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", emailVerify=" + emailVerify +
				", username='" + username + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				", hashedPassword='" + hashedPassword + '\'' +
				", role='" + role + '\'' +
                ", enabled='" + enabled + '\'' +
				", password='" + password + '\'' +
				", hotelDTOS=" + hotelDTOS +
				", orderDTOS=" + orderDTOS +
				'}';
	}
}
