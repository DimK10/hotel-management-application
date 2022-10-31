package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoomDTOToRoom {

    private final HotelRepository hotelRepository;


    private final OrderDTOToOrder orderDTOToOrder;


    public RoomDTOToRoom(HotelRepository hotelRepository,OrderDTOToOrder orderDTOToOrder) {
        this.hotelRepository = hotelRepository;
        this.orderDTOToOrder = orderDTOToOrder;
    }

    public Room converter(RoomDTO roomDTO){
        Room room = new Room();

        room.setId(roomDTO.getId());

        room.setName(roomDTO.getName());

        room.setPrice(roomDTO.getPrice());

        room.setLuxurity(roomDTO.getLuxurity());

        for (OrderDTO orderDTO : roomDTO.getOrdersDTO()){
            room.getOrders().add(orderDTOToOrder.Converter(orderDTO));
        }

        Optional<Hotel> hotel = hotelRepository.findById(roomDTO.getId());

        if (hotel.isPresent()){
            room.setHotel(hotel.get());
        }


        return room;
    }
}
