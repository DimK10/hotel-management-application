package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * created by gp
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
}
