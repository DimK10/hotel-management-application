package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;

import java.time.LocalDate;
import java.util.List;

public interface AdvanceSearchImpl {

    List<Hotel> AdvanceSearchMethode(List<HotelAmenity> hotelAmenities, List<RoomAmenity> roomAmenities, LocalDate checkInDate, LocalDate checkOutDate,
                                     Long priceFrom, Long priceTo, Integer adultsRange, Integer stars, String nameOrLocation);
}
