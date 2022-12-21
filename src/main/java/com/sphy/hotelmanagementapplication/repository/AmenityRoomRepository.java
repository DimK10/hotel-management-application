package com.sphy.hotelmanagementapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;


/***
 * created by AKd
 */

@Repository
public interface AmenityRoomRepository extends CrudRepository <RoomAmenity,Long> {

}
