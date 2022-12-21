package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/***
 * created by gp
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query("SELECT count(*) from orders o where o.room=?3 and" +
            "((o.checkInDate>?1 and o.checkOutDate>?2 and o.checkInDate<?2) or" +
            "(o.checkInDate<?1 and o.checkOutDate<?2 and o.checkOutDate>?1) or" +
            "(o.checkInDate<=?1 and o.checkOutDate>=?2))")
    int OrderConflict(LocalDate checkIn, LocalDate checkOut, Room room);
}
