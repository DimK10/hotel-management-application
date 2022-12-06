package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * created by gp
 */
@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    Optional<Room> findByName(String name);

    @Query("SELECT count(*) from rooms")
    int countAll();
}
