package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/***
 * created by gp
 */
@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    Optional<Room> findByName(String name);

    @Query("SELECT count(r) from rooms r where r.hotel.owner.id = :id")
    int countAll(@Param("id") Long id);

    @Query("SELECT count(r) from rooms r where r.hotel.id = :hotelId and r.hotel.owner.id = :userId")
    int countAllByHotelIdAndOwnerId(@Param("hotelId") Long hotelId, @Param("userId") Long userId);

    @Query(value = "select r from rooms r where r.hotel.owner.id = :id",
            countQuery = "select count (r) from  rooms r where r.hotel.owner.id = :id")
    Page<Room> findAllRoomsByOwner(@Param("id") Long id, Pageable pageable);


    @Query(value = "select ra from RoomAmenity ra inner join IntermediateRoomAmenity i on i.roomAmenity.id = ra.id where ra.enabled = true and i.room.id = :id")
    Set<RoomAmenity> findAmenitiesByRoomId(@Param("id") Long id);

    Page<Room> findAllByHotelId(Long hotelId, Pageable pageable);

    @Query(value = "select r from rooms r inner join orders o on r.id = o.room.id where r.hotel.id = :id and " +
            " (((:from < o.checkInDate) and (:to < o.checkInDate)) or ((:from > o.checkOutDate) and (:to > o.checkOutDate))) ")
    List<Room> findAllRoomsAvalable(@Param("from") LocalDate from,@Param("to") LocalDate to,@Param("id") Long hotelId);
}
