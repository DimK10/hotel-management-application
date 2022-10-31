package com.sphy.hotelmanagementapplication.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO {

    private Long id;
    private boolean emailVerify;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String hashedPassword;
    private String transactionId;
    private List<OrderDTO> orderDTO = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<OrderDTO> getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(List<OrderDTO> orderDTO) {
        this.orderDTO = orderDTO;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", emailVerify=" + emailVerify +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", orderDTO=" + orderDTO +
                '}';
    }
}
