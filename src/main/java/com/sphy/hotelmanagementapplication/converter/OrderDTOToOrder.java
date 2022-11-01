package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.repositories.ClientRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class OrderDTOToOrder {


    private final RoomRepository roomRepository;

    private final ClientRepository clientRepository;

    public OrderDTOToOrder(RoomRepository roomRepository, ClientRepository clientRepository) {

        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
    }

    Order Converter(OrderDTO orderDTO){

        Order order = new Order();

        order.setId(orderDTO.getId());
        order.setCheckInDate(orderDTO.getCheckInDate());
        order.setCheckOutDate(orderDTO.getCheckOutDate());
        Optional<Room> room = roomRepository.findById(orderDTO.getId());
        if (room.isPresent()){
            order.setRoom(room.get());
        }

        Optional<Client> client =  clientRepository.findById(orderDTO.getClient());

        if (client.isPresent()){
            order.setClient(client.get());
        }

        return order;
    }
}
