package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


/***
 * created by AKd
 */

@Repository
public interface AmenityRoomRepository extends CrudRepository <RoomAmenity,Long> {

    @Query(value = "select ra from RoomAmenity ra where ra.enabled = true order by ra.rAmenity desc ")
    Set<RoomAmenity> findAllEnabled();

}
