package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
