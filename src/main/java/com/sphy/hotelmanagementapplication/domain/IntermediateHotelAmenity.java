package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/***
 * created by gp , AKd
 */
@Entity
public class IntermediateHotelAmenity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private HotelAmenity hotelAmenity;

    public IntermediateHotelAmenity() {

    }

    public IntermediateHotelAmenity(Hotel hotel, HotelAmenity hotelAmenity) {
        this.hotel = hotel;
        this.hotelAmenity = hotelAmenity;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HotelAmenity getHotelAmenity() {
        return hotelAmenity;
    }

    public void setHotelAmenity(HotelAmenity hotelAmenity) {
        this.hotelAmenity = hotelAmenity;
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
        return "IntermediateHotelAmenity{" +
                "id=" + super.getId() +
                "hotel=" + hotel +
                ", hotelAmenity=" + hotelAmenity +
                '}';
    }
}
