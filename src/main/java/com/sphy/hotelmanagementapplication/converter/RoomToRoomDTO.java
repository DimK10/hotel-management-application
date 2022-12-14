package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/***
 * created by gp
 */
@Transactional
@Component
public class RoomToRoomDTO {

    private final OrderToOrderDTO orderToOrderDTO;

    private final HotelRepository hotelRepository;

    private final RoomAmenityToRoomAmenityDTO roomAmenityToRoomAmenityDTO;

    public RoomToRoomDTO(OrderToOrderDTO orderToOrderDTO, HotelRepository hotelRepository,RoomAmenityToRoomAmenityDTO roomAmenityToRoomAmenityDTO) {
        this.orderToOrderDTO = orderToOrderDTO;
        this.hotelRepository = hotelRepository;
        this.roomAmenityToRoomAmenityDTO = roomAmenityToRoomAmenityDTO;
    }

    /***
     * converts a room object to roomDTO
     * @param room the room object we want to convert
     * @return the converted roomDTO object
     */


    public RoomDTO converter(Room room){
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());

        roomDTO.setName(room.getName());

        roomDTO.setPrice(room.getPrice());

        roomDTO.setLuxurity(room.getLuxurity());

		roomDTO.setDisabled(room.isDisabled());

        roomDTO.setCapacity(room.getCapacity());

        if (room.getHotel() != null) {
            Optional<Hotel> hotel = hotelRepository.findById(room.getHotel().getId());

            hotel.ifPresent(value -> roomDTO.setHotel(value.getId()));
        }

        if (room.getOrders() != null){

            for (Order order : room.getOrders()){
                roomDTO.getOrders().add(orderToOrderDTO.converter(order));
            }
        }

        if (!room.getRoomAmenity().isEmpty()) {
            for (RoomAmenity roomAmenity : room.getRoomAmenity()) {
                roomDTO.getRoomAmenityDTO().add(roomAmenityToRoomAmenityDTO.converter(roomAmenity));
            }
        }

        return roomDTO;
    }
}
