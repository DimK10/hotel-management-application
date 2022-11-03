package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelRepository extends CrudRepository<Hotel,Long> {

    Optional<Hotel> findByName(String name);

	// fixme produces LazyInitializationException if getRooms is called
	@EntityGraph(value = "Hotel.rooms", type = EntityGraphType.FETCH)
	@Query("from Hotel")
	Set<Hotel> findAllHotels();
}
