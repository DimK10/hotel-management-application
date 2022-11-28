package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import org.springframework.stereotype.Component;

/***
 * created by gp
 */
@Component
public class HotelToHotelDTO {

    private final RoomToRoomDTO roomToRoomDTO;


    public HotelToHotelDTO(RoomToRoomDTO roomToRoomDTO) {
        this.roomToRoomDTO = roomToRoomDTO;
    }

    /***
     * converts a hotel object to hotelDTO
     * @param hotel the hotel object we want to convert
     * @return the converted hotelDTO object
     */
    public HotelDTO converter(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAreaName(hotel.getAreaName());
        hotelDTO.setStars(hotel.getStars());
        hotelDTO.setOwner(hotel.getOwner().getId());

        for (Room room : hotel.getRooms()){
            hotelDTO.getRooms().add(roomToRoomDTO.converter(room));
        }

        hotelDTO.setDisabled(hotel.isDisabled());

        return hotelDTO;

    }
}
