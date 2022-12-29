package com.sphy.hotelmanagementapplication.dto;

import java.time.LocalDate;

/***
 * created by gp
 */
public class BasicSearchDTO {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String NameOrLocation;

    public BasicSearchDTO() {
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

    public String getNameOrLocation() {
        return NameOrLocation;
    }

    public void setNameOrLocation(String nameOrLocation) {
        NameOrLocation = nameOrLocation;
    }

    @Override
    public String toString() {
        return "BasicSearchDTO{" +
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", NameOrLocation='" + NameOrLocation + '\'' +
                '}';
    }
}
