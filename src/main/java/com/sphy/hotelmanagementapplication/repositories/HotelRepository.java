package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.Hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/***
 * created by gp
 */
@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel,Long> {

    Optional<Hotel> findByName(String name);

	// fixme produces LazyInitializationException if getRooms is called
	@EntityGraph(value = "Hotel.rooms", type = EntityGraphType.FETCH)
	@Query("from Hotel")
	Set<Hotel> findAllHotels();

	boolean existsByName(String name);

	@Query("SELECT count(h) from Hotel")
	int countAll();

	@Query(value = "select h from Hotel h where h.owner.id = :id",
	countQuery = "select count (h) from  Hotel h where h.owner.id = :id")
	Page<Hotel> findAllHotelsByOwner(@Param("id") Long id, Pageable pageable);
}
