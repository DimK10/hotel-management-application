package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/***
 * created by AKd
 */
@Repository
public interface AmenityHotelRepository extends CrudRepository <HotelAmenity,Long> {

}
