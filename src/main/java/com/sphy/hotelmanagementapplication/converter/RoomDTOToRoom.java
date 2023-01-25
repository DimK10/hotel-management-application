package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/***
 * created by gp ,AKd
 */
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
    @Transactional
    public Room converter(RoomDTO roomDTO){
        Room room = new Room();

        room.setId(roomDTO.getId());

        room.setName(roomDTO.getName());

        room.setPrice(roomDTO.getPrice());

        room.setLuxurity(roomDTO.getLuxurity());

        room.setCapacity(roomDTO.getCapacity());

        for (OrderDTO orderDTO : roomDTO.getOrders()){
            room.getOrders().add(orderDTOToOrder.converter(orderDTO));
        }

		if (roomDTO.getHotel() != null) {
			Optional<Hotel> hotel =
					hotelRepository.findById(roomDTO.getHotel());

			hotel.ifPresent(room::setHotel);
		}

        return room;
    }
}
