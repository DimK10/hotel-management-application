package com.sphy.hotelmanagementapplication.domain;

import java.time.LocalDate;
import java.util.List;

public class AdvancedSearch extends BaseEntity{

    private List<HotelAmenity> hotelAmenities;

    private List<RoomAmenity> roomAmenities;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long priceFrom;

    private Long priceTo;

    private Integer adultsRange;

    private Integer stars;

    private String nameOrLocation;

    public AdvancedSearch() {
    }

    public AdvancedSearch(List<HotelAmenity> hotelAmenities, List<RoomAmenity> roomAmenities, LocalDate checkInDate, LocalDate checkOutDate, Long priceFrom, Long priceTo, Integer adultsRange, Integer stars, String nameOrLocation) {
        this.hotelAmenities = hotelAmenities;
        this.roomAmenities = roomAmenities;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.adultsRange = adultsRange;
        this.stars = stars;
        this.nameOrLocation = nameOrLocation;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public List<HotelAmenity> getHotelAmenities() {
        return hotelAmenities;
    }

    public void setHotelAmenities(List<HotelAmenity> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public List<RoomAmenity> getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(List<RoomAmenity> roomAmenities) {
        this.roomAmenities = roomAmenities;
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

    public Long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Long priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getAdultsRange() {
        return adultsRange;
    }

    public void setAdultsRange(Integer adultsRange) {
        this.adultsRange = adultsRange;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getNameOrLocation() {
        return nameOrLocation;
    }

    public void setNameOrLocation(String nameOrLocation) {
        this.nameOrLocation = nameOrLocation;
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
        return "AdvancedSearch{" +
                "id=" + super.getId() +
                "hotelAmenities=" + hotelAmenities +
                ", roomAmenities=" + roomAmenities +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", adultsRange=" + adultsRange +
                ", stars=" + stars +
                ", nameOrLocation='" + nameOrLocation + '\'' +
                '}';
    }
}

