package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/***
 * created by gp
 */
@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    Optional<Room> findByName(String name);

    @Query("SELECT count(*) from rooms")
    int countAll();

   // @Query("select r.roomAmenity from  rooms r where r.id = id")
    //Set<RoomAmenity> findByRoomID(Long id);
}
