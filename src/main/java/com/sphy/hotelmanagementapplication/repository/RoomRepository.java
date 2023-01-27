package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    Optional<Room> findByName(String name);

    @Query("SELECT count(r) from rooms r where r.hotel.owner.id = :id")
    int countAll(@Param("id") Long id);

    @Query(value = "select r from rooms r where r.hotel.owner.id = :id",
            countQuery = "select count (r) from  rooms r where r.hotel.owner.id = :id")
    Page<Room> findAllRoomsByOwner(@Param("id") Long id, Pageable pageable);


    @Query(value = "select ra from RoomAmenity ra inner join IntermediateRoomAmenity i on i.roomAmenity.id = ra.id where i.room.id = :id")
    Set<RoomAmenity> findAmenitiesByRoomId(@Param("id") Long id);
}
