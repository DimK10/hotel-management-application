package com.sphy.hotelmanagementapplication.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;


/***
 * created by AKd
 */

@Repository
public interface AmenityRoomRepository extends CrudRepository <RoomAmenity,Long> {
	Optional<RoomAmenity> findById(Long id);

}
