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

    /***
     * converts a roomDTO object to room
     * @param roomDTO the roomDTO object to be converted
     * @return the converted room object
     */
    public Room converter(RoomDTO roomDTO){
        Room room = new Room();

        room.setId(roomDTO.getId());

        room.setName(roomDTO.getName());

        room.setPrice(roomDTO.getPrice());

        room.setLuxurity(roomDTO.getLuxurity());

        for (OrderDTO orderDTO : roomDTO.getOrders()){
            room.getOrders().add(orderDTOToOrder.Converter(orderDTO));
        }

		if (roomDTO.getHotel() != null) {
			Optional<Hotel> hotel =
					hotelRepository.findById(roomDTO.getHotel());

			hotel.ifPresent(room::setHotel);
		}

        return room;
    }
}
