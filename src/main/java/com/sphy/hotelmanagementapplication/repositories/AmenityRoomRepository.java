package com.sphy.hotelmanagementapplication.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity.AmenitiesRoom;

/***
 * created by AKd
 */

@Repository
public interface AmenityRoomRepository extends CrudRepository <RoomAmenity,Long> {	
	RoomAmenity findbyAmenitiesRoom(AmenitiesRoom amenitiesR);
	Optional<RoomAmenity> findById(Long id);

}
