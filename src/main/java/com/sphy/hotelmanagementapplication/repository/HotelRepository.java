package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

/***
 * created by gp, dk , AKd
 */
@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel,Long> {

	@Query("from Hotel h where h.id = :id and h.owner.id = :userId ")
	Optional<Hotel> findHotelByIdAndOwner(@Param("id") Long id, @Param("userId") Long userId);

    Optional<Hotel> findByName(String name);

	// fixme produces LazyInitializationException if getRooms is called
	@EntityGraph(value = "Hotel.rooms", type = EntityGraphType.FETCH)
	@Query("from Hotel")
	Set<Hotel> findAllHotels();

	boolean existsByName(String name);


	@Query("SELECT count(h) from Hotel h where h.owner.id = :id")
	int countAll(@Param("id") Long id);

	@Query(value = "select h from Hotel h where h.owner.id = :id",
	countQuery = "select count (h) from  Hotel h where h.owner.id = :id")
	Page<Hotel> findAllHotelsByOwner(@Param("id") Long id, Pageable pageable);


	@Query(value = "select h from Hotel h inner join rooms r on h.id = r.hotel.id " +
            "inner join orders o on r.id = o.room.id " +
            "where (h.name = :NameOrLocation or h.areaName = :NameOrLocation) and " +
            " :checkIn not between o.checkInDate and o.checkOutDate and" +
            " :checkOut not between o.checkInDate and o.checkOutDate")
	Set<Hotel> findByBasicSearch(@Param("checkIn")LocalDate checkIn, @Param("checkOut")LocalDate checkOut
	, @Param("NameOrLocation") String NameOrLocation);

	@Query(value = "select ha from HotelAmenity ha inner join IntermediateHotelAmenity i on i.hotelAmenity.id = ha.id" +
			"  where ha.enabled = true and i.hotel.id = :id order by i.hotel.id")
	Set<HotelAmenity> findAmenityByHotelId(@Param("id") Long id);

}
