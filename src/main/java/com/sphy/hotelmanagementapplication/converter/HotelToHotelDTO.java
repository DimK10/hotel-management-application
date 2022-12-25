package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * created by gp, AKd
 */
@Transactional
@Component
public class HotelToHotelDTO {

    private final RoomToRoomDTO roomToRoomDTO;
    private final HotelAmenityToHotelAmenityDTO hotelAmenityToHotelAmenityDTO;


    public HotelToHotelDTO(RoomToRoomDTO roomToRoomDTO,HotelAmenityToHotelAmenityDTO hotelAmenityToHotelAmenityDTO) {
        this.roomToRoomDTO = roomToRoomDTO;
        this.hotelAmenityToHotelAmenityDTO = hotelAmenityToHotelAmenityDTO;
    }

    /***
     * converts a hotel object to hotelDTO
     * @param hotel the hotel object we want to convert
     * @return the converted hotelDTO object
     */
    @Transactional
    public HotelDTO converter(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAreaName(hotel.getAreaName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setStars(hotel.getStars());

        if (hotel.getOwner() != null) {
            hotelDTO.setOwner(hotel.getOwner().getId());
        } else {
            hotelDTO.setOwner(null);
        }

        for (Room room : hotel.getRooms()){
            hotelDTO.getRooms().add(roomToRoomDTO.converter(room));
        }

        if(!hotel.getHotelAmenity().isEmpty()) {
            for (HotelAmenity hotelAmenity : hotel.getHotelAmenity()) {
                hotelDTO.getHotelAmenityDTO().add(hotelAmenityToHotelAmenityDTO.converter(hotelAmenity));
            }
        }
        
        hotelDTO.setDisabled(hotel.isDisabled());

        return hotelDTO;

    }

}
