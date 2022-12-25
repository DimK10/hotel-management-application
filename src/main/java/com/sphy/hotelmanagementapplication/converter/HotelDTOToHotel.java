package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelAmenityDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import org.springframework.stereotype.Component;

/***
 * created by gp, AKd
 */
@Component
public class HotelDTOToHotel {

    private final RoomDTOToRoom roomDTOToRoom ;

    private final UserRepository userRepository;

    private final HotelAmenityDTOToHotelAmenity hotelAmenityDTOToHotelAmenity;


    public HotelDTOToHotel(RoomDTOToRoom roomDTOToRoom, UserRepository userRepository, HotelAmenityDTOToHotelAmenity hotelAmenityDTOToHotelAmenity) {
        this.roomDTOToRoom = roomDTOToRoom;
        this.userRepository = userRepository;
        this.hotelAmenityDTOToHotelAmenity = hotelAmenityDTOToHotelAmenity;
    }

    /***
     * converts a hotelDTO object to hotel
     * @param hotelDTO the hotelDTO object we want to convert
     * @return the converted hotel object
     */
    public Hotel converter(HotelDTO hotelDTO){

        Hotel hotel = new Hotel();

        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setAreaName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStars(hotelDTO.getStars());

        hotel.setOwner(userRepository.findById(hotelDTO.getOwner()).get());

        for (RoomDTO roomDTO : hotelDTO.getRooms()){
            hotel.getRooms().add(roomDTOToRoom.converter(roomDTO));
        }

        hotelDTO.setDisabled(hotel.isDisabled());


        if (!hotelDTO.getHotelAmenityDTO().isEmpty()) {

            for (HotelAmenityDTO hotelAmenityDTO : hotelDTO.getHotelAmenityDTO()) {
                hotel.getHotelAmenity().add(hotelAmenityDTOToHotelAmenity.converter(hotelAmenityDTO));
            }
        }

        return hotel;

    }
}
