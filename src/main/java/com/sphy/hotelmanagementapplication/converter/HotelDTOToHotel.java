package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import org.springframework.stereotype.Component;

@Component
public class HotelDTOToHotel {

    private final RoomDTOToRoom roomDTOToRoom ;

    private final AdminRepository adminRepository;


    public HotelDTOToHotel(RoomDTOToRoom roomDTOToRoom, AdminRepository adminRepository) {
        this.roomDTOToRoom = roomDTOToRoom;
        this.adminRepository = adminRepository;
    }

    public Hotel converter(HotelDTO hotelDTO){

        Hotel hotel = new Hotel();

        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setAreaName(hotelDTO.getName());
        hotel.setStars(hotelDTO.getStars());

        hotel.setOwner(adminRepository.findById(hotelDTO.getOwner()).get());

        for (RoomDTO roomDTO : hotelDTO.getRooms()){
            hotel.getRooms().add(roomDTOToRoom.converter(roomDTO));
        }

        hotelDTO.setDisabled(hotel.isDisabled());

        return hotel;

    }
}
