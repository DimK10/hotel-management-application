package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * created by gp
 */
@Transactional
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


    public RoomDTO converter(Room room) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());

        roomDTO.setName(room.getName());

        roomDTO.setPrice(room.getPrice());

        roomDTO.setLuxurity(room.getLuxurity());

        roomDTO.setDisabled(room.isDisabled());

        roomDTO.setCapacity(room.getCapacity());

        Set<RoomAmenity> roomAmenities = new HashSet<>();

        if (room.getIntermediateRoomAmenities() != null)
            roomAmenities = room
                    .getIntermediateRoomAmenities()
                    .stream()
                    .map(IntermediateRoomAmenity::getRoomAmenity)
                    .collect(Collectors.toSet());

        roomDTO.setAmenities(roomAmenities);

        if (room.getHotel() != null) {
            Optional<Hotel> hotel = hotelRepository.findById(room.getHotel().getId());

            hotel.ifPresent(value -> roomDTO.setHotel(value.getId()));
        }

        if (room.getOrders() != null) {

            for (Order order : room.getOrders()) {
                roomDTO.getOrders().add(orderToOrderDTO.converter(order));
            }
        }

        return roomDTO;
    }
}
