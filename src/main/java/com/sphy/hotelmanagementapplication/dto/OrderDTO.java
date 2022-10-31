package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDTO implements Serializable {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long clientDTO;

    private Long roomDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(Long clientDTO) {
        this.clientDTO = clientDTO;
    }

    public Long getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(Long roomDTO) {
        this.roomDTO = roomDTO;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", clientDTO=" + clientDTO +
                ", roomDTO=" + roomDTO +
                '}';
    }
}
