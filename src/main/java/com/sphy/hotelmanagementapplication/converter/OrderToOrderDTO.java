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
public class OrderToOrderDTO {


    private final RoomRepository roomRepository;

    private final UserRepository userRepository;



    public OrderToOrderDTO(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    /***
     * converts an orderDTO object to order
     * @param order the order object to be converted
     * @return the converted orderDTO object
     */
    public OrderDTO converter(Order order){

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setCheckInDate(order.getCheckInDate());
        orderDTO.setCheckOutDate(order.getCheckOutDate());
        orderDTO.setCanceled(order.isCanceled());

        if (order.getRoom() != null){

            Optional<Room> room = roomRepository.findById(order.getRoom().getId());

            if (room.isPresent() && room.get().getId() != 0){
                orderDTO.setRoom(room.get().getId());
            }
        }


        if (order.getClient() != null){

            Optional<User> client = userRepository.findById(order.getClient().getId());

            if (client.isPresent() && client.get().getId() != 0) {
                orderDTO.setClient(client.get().getId());
            }
        }


        return orderDTO;
    }
}
