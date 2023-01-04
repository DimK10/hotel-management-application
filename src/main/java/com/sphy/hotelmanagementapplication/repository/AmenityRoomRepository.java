package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/***
 * created by AKd
 */

@Repository
public interface AmenityRoomRepository extends CrudRepository <RoomAmenity,Long> {

}
