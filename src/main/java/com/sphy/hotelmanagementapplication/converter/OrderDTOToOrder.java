package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.repositories.UserRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

/***
 * created by gp
 */
@Component
public class OrderDTOToOrder {


    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    public OrderDTOToOrder(RoomRepository roomRepository, UserRepository userRepository) {

        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    /***
     * converts an orderDTO object to order
     * @param orderDTO the orderDTO object we want to convert
     * @return the converted order object
     */
    public Order converter(OrderDTO orderDTO){

        Order order = new Order();

        order.setId(orderDTO.getId());
        order.setCheckInDate(orderDTO.getCheckInDate());
        order.setCheckOutDate(orderDTO.getCheckOutDate());
        order.setCanceled(orderDTO.isCanceled());

        if (orderDTO.getRoom() != null){

            Optional<Room> room = roomRepository.findById(orderDTO.getRoom());

            room.ifPresent(order::setRoom);
        }

        if (orderDTO.getClient() != null){

            Optional<User> client =  userRepository.findById(orderDTO.getClient());

            client.ifPresent(order::setClient);
        }



        return order;
    }
}
