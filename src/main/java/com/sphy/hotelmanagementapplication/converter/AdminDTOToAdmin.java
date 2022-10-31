package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.AdminDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminDTOToAdmin {


    private final HotelRepository hotelRepository;

    public AdminDTOToAdmin(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Admin converter(AdminDTO adminDTO) {

        Admin admin = new Admin();

        admin.setId(adminDTO.getId());
        admin.setFirstname(adminDTO.getFirstname());
        admin.setLastname(adminDTO.getLastname());
        admin.setUsername(adminDTO.getUsername());
        admin.setEmail(adminDTO.getEmail());
        admin.setEmailVerify(adminDTO.isEmailVerify());
        admin.setHashedPassword(adminDTO.getHashedPassword());
        admin.setTransactionId(adminDTO.getTransactionId());

        for (HotelDTO hotelDTO : adminDTO.getHotels()) {
            Optional<Hotel> hotelOPT = hotelRepository.findById(hotelDTO.getId());
            if (hotelOPT.isPresent()){
                admin.getHotels().add(hotelOPT.get());

            }
        }

        return admin;
    }
}
