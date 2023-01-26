package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.IntermediateRoomAmenity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/***
 * created by gp
 */
@Repository
public interface IntermediateRoomAmenityRepository extends CrudRepository<IntermediateRoomAmenity,Long> {
}
