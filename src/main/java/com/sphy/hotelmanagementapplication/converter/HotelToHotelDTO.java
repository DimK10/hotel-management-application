package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import org.springframework.stereotype.Component;

@Component
public class HotelToHotelDTO {

    private final RoomToRoomDTO roomToRoomDTO;


    public HotelToHotelDTO(RoomToRoomDTO roomToRoomDTO) {
        this.roomToRoomDTO = roomToRoomDTO;
    }

    public HotelDTO converter(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAreaName(hotel.getName());
        hotelDTO.setStars(hotel.getStars());
        hotelDTO.setOwner(hotel.getOwner().getId());

        for (Room room : hotel.getRooms()){
            hotelDTO.getRooms().add(roomToRoomDTO.converter(room));
        }

        hotelDTO.setDisabled(hotel.isDisabled());

        return hotelDTO;

    }
}
