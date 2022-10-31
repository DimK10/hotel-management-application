package com.sphy.hotelmanagementapplication.repositories;

import java.util.Optional;
import java.util.Set;

import com.sphy.hotelmanagementapplication.domain.Hotel;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel,Long> {
    Hotel findByName(String name);

	@Query("select h from Hotel h")
	Set<Hotel> findAllHotels();
}
