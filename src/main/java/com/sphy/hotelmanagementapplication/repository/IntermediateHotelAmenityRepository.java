package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.IntermediateHotelAmenity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/***
 * created by gp
 */
@Repository
public interface IntermediateHotelAmenityRepository extends CrudRepository<IntermediateHotelAmenity,Long> {
}
