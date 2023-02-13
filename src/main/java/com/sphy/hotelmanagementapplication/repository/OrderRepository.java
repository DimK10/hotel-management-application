package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;

/***
 * created by gp
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query("SELECT count(o) from orders o where o.room=:id " +
            "and (o.checkInDate>:checkIn and o.checkOutDate>:checkOut and o.checkInDate<:checkOut) or " +
            "(o.checkInDate<:checkIn and o.checkOutDate<:checkOut and o.checkOutDate>:checkIn) or" +
            "(o.checkInDate<=:checkIn and o.checkOutDate>=:checkOut)")
    int OrderConflict(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut, @Param("id") Room room);


    @Query(value = "select o from orders o where o.client.id = :id and o.canceled = false")
    List<Order> findAllClient(Long id);

    @Query(value = "select o from orders o where o.room.hotel.owner.id = :id and o.canceled = false")
    List<Order> findAllAdmin(Long id);
}
