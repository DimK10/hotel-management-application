package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


/***
 * created by AKd
 */

public interface AmenityHotelRepository extends CrudRepository <HotelAmenity,Long> {

    @Query(value = "select ha from HotelAmenity ha where ha.enabled = true ")
    Set<HotelAmenity> findAllEnabled();
}
