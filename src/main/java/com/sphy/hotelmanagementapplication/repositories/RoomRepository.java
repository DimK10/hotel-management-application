package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * created by gp
 */
@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
    Optional<Room> findByName(String name);
}
