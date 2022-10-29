package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdminDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private boolean emailVerify;

    private Set<Long> hotels = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(boolean emailVerify) {
        this.emailVerify = emailVerify;
    }

    public Set<Long> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Long> hotels) {
        this.hotels = hotels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(id, adminDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", emailVerify=" + emailVerify +
                ", hotels=" + hotels +
                '}';
    }
}
