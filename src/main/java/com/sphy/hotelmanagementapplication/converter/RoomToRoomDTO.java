package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/***
 * created by gp
 */
@Component
public class RoomToRoomDTO {

    private final OrderToOrderDTO orderToOrderDTO;

    private final HotelRepository hotelRepository;

    public RoomToRoomDTO(OrderToOrderDTO orderToOrderDTO, HotelRepository hotelRepository) {
        this.orderToOrderDTO = orderToOrderDTO;
        this.hotelRepository = hotelRepository;
    }

    /***
     * converts a room object to roomDTO
     * @param room the room object we want to convert
     * @return the converted roomDTO object
     */
    @Transactional
    public RoomDTO converter(Room room){
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());

        roomDTO.setName(room.getName());

        roomDTO.setPrice(room.getPrice());

        roomDTO.setLuxurity(room.getLuxurity());

		roomDTO.setDisabled(room.isDisabled());

        if (!(room.getHotel() == null)) {
            Optional<Hotel> hotel = hotelRepository.findById(room.getHotel().getId());

            if (hotel.isPresent()) {
                roomDTO.setHotel(hotel.get().getId());
            }
        }
        return roomDTO;
    }
}
