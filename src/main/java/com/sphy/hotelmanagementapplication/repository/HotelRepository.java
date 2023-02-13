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
public interface HotelRepository extends PagingAndSortingRepository<Hotel,Long>{

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

	@Query(value = "select h from Hotel h where h.owner.id = :id")
	Set<Hotel> findAllHotelsByOwner(@Param("id") Long id);

	@Query(value = "select h from Hotel h where h.owner.id = :id",
	countQuery = "select count (h) from  Hotel h where h.owner.id = :id")
	Page<Hotel> findAllHotelsByOwner(@Param("id") Long id, Pageable pageable);


	@Query(value = "select h from Hotel h inner join rooms r on h.id = r.hotel.id " +
            "inner join orders o on r.id = o.room.id " +
            "where (h.name like :NameOrLocation or h.areaName like :NameOrLocation) and h.disabled = false and " +
            " :checkIn not between o.checkInDate and o.checkOutDate and" +
            " :checkOut not between o.checkInDate and o.checkOutDate order by r.price desc")
	Set<Hotel> findByBasicSearch(@Param("checkIn")LocalDate checkIn, @Param("checkOut")LocalDate checkOut
	, @Param("NameOrLocation") String NameOrLocation);

	@Query(value = "select ha from HotelAmenity ha inner join IntermediateHotelAmenity i on i.hotelAmenity.id = ha.id" +
			"  where ha.enabled = true and i.hotel.id = :id order by i.hotel.id")
	Set<HotelAmenity> findAmenityByHotelId(@Param("id") Long id);

//	@Query(value = "select DISTINCT rk.hotel from (select r from rooms r " +
//			"inner join Hotel h on r.hotel.id = h.id inner join " +
//			"IntermediateHotelAmenity ih on h.id = ih.hotel.id " +
//			"inner join HotelAmenity ha on ih.hotelAmenity.id = ha.id " +
//			"inner join IntermediateRoomAmenity ir on r.id = ir.room.id " +
//			"inner join RoomAmenity ra on ra.id = ir.roomAmenity.id inner join " +
//			"orders o on o.room.id = r.id where h.disabled = false and " +
//			"r.capacity = :adults and r.price > :priceFrom and r.price < :priceTo and h.stars = :stars and " +
//			"(h.name like :NameOrLocation or h.areaName like :NameOrLocation) and (((:checkIn < o.checkInDate) and " +
//			"(:checkOut < o.checkInDate)) or ((:checkIn > o.checkOutDate) and (:checkOut > o.checkOutDate))) " +
//			" order by r.price) rk ")
//	Set<Hotel> kjbasdkjbak();

//	@Query(value = "select DISTINCT rk.hotel from (select r from rooms r " +
//			"inner join com.sphy.hotelmanagementapplication.domain.Hotel h on rooms.hotel.id = Hotel.id " +
//			"inner join com.sphy.hotelmanagementapplication.domain.IntermediateHotelAmenity ih on Hotel.id = IntermediateHotelAmenity.hotel.id " +
//			"inner join com.sphy.hotelmanagementapplication.domain.HotelAmenity ha on IntermediateHotelAmenity.hotelAmenity.id = HotelAmenity.id " +
//			"inner join com.sphy.hotelmanagementapplication.domain.IntermediateRoomAmenity ir on rooms.id = IntermediateRoomAmenity.room.id " +
//			"inner join com.sphy.hotelmanagementapplication.domain.RoomAmenity ra on RoomAmenity.id = IntermediateRoomAmenity.roomAmenity.id " +
//			"inner join com.sphy.hotelmanagementapplication.domain.Order o on orders.room.id = rooms.id " +
//			"where Hotel.disabled = false " +
//			"and rooms.capacity = :adults " +
//			"and rooms.price > :priceFrom " +
//			"and rooms.price < :priceTo " +
//			"and Hotel.stars = :stars " +
//			"and (Hotel.name like :NameOrLocation or Hotel.areaName like :NameOrLocation) " +
//			"and (((:checkIn < orders.checkInDate) and (:checkOut < orders.checkInDate)) or ((:checkIn > orders.checkOutDate) " +
//			"and (:checkOut > orders.checkOutDate)))  " +
//			"order by r.price ) rk")
//	Set<Hotel> kjbasdkjbak();

}
