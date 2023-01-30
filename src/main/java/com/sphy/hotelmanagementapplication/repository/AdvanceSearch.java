package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvanceSearch implements AdvanceSearchImpl{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Hotel> AdvanceSearchMethode(List<HotelAmenity> hotelAmenities, List<RoomAmenity> roomAmenities, LocalDate checkInDate, LocalDate checkOutDate,
                                            Long priceFrom, Long priceTo, Integer adultsRange, Integer stars, String nameOrLocation) {

        Map<String, Object> parametrMap = new HashMap<>();

        StringBuilder query = new StringBuilder("select h from Hotel h inner join IntermediateHotelAmenity ih on h.id = ih.hotel.id inner join HotelAmenity ha on ih.hotelAmenity.id = ha.id inner " +
                "join rooms r on r.hotel.id = h.id inner join IntermediateRoomAmenity ir on r.id = ir.room.id inner join RoomAmenity ra on ra.id = ir.roomAmenity.id " +
                "inner join orders o on o.room.id = r.id where ");

        if (adultsRange != null) {

            query.append("r.capacity = :adults");

            parametrMap.put("adults", adultsRange);
        }

        if (priceFrom != null) {

            query.append(" and r.price > :priceFrom");
            parametrMap.put("priceFrom", priceFrom);
        }

        if (priceTo != null) {

            query.append(" and r.price < :priceTo");
            parametrMap.put("priceTo", priceTo);
        }

        if (stars != null) {

            query.append(" and h.stars = :stars");
            parametrMap.put("stars", stars);
        }

        if (nameOrLocation != null) {

            query.append(" (h.name like :NameOrLocation or h.areaName like :NameOrLocation)");
            parametrMap.put("NameOrLocation", nameOrLocation);
        }

        if (checkInDate != null && checkOutDate != null) {

            query.append(" and :checkIn not between o.checkInDate and o.checkOutDate and :checkOut not between o.checkInDate and o.checkOutDate");
            parametrMap.put("checkOut", checkOutDate);
            parametrMap.put("checkIn", checkInDate);
        }


        for (int i = 0; i < hotelAmenities.size(); i++) {

            query.append(" and ha.hAmenity = :hAmenity").append(i);
            parametrMap.put("hAmenity" + i, hotelAmenities.get(i).gethAmenity());
        }

        for (int i = 0; i < roomAmenities.size(); i++) {

            query.append(" and ra.rAmenity = :rAmenity").append(i);
            parametrMap.put("rAmenity" + i, roomAmenities.get(i).getrAmenity());
        }


        Query queryFinal = entityManager.createQuery(String.valueOf(query));

        for (String key : parametrMap.keySet()) {

            queryFinal.setParameter(key, parametrMap.get(key));
        }

        return queryFinal.getResultList();

    }
}
