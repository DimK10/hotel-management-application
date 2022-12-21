package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.repository.ClientRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/***
 * created by gp
 */
@Component
public class OrderToOrderDTO {


    private final RoomRepository roomRepository;

    private final ClientRepository clientRepository;



    public OrderToOrderDTO(RoomRepository roomRepository, ClientRepository clientRepository) {
        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
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

        Optional<Room> room = roomRepository.findById(order.getRoom().getId());

        if (room.isPresent()){
            orderDTO.setRoom(room.get().getId());
        }

        Optional<Client> client = clientRepository.findById(order.getClient().getId());

        if (client.isPresent()){
            orderDTO.setClient(client.get().getId());
        }

        return orderDTO;
    }
}
