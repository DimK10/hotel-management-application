package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AdvanceSearch {

    @PersistenceContext
    EntityManager entityManager;

    public Set<Hotel> AdvanceSearch(List<HotelAmenity> hotelAmenities, List<RoomAmenity> roomAmenities, String location, LocalDate checkInDate, LocalDate checkOutDate,
                                    Long priceFrom, Long priceTo, Integer adultsRange, Integer stars, String nameOrLocation){

        String query = "";
    }
}
