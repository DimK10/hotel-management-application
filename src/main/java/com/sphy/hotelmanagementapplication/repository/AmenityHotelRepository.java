package com.sphy.hotelmanagementapplication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;


/***
 * created by AKd
 */

public interface AmenityHotelRepository extends CrudRepository <HotelAmenity,Long> {
	Optional<HotelAmenity> findById(Long id);

}
