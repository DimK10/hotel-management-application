package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.AdminDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class HotelDTOToHotelTest {

    @Mock
    HotelDTOToHotel hotelDTOToHotel;

    @Mock
    RoomDTOToRoom roomDTOToRoom;

    @Mock
    AdminRepository adminRepository;

    Hotel hotel = new Hotel();

    HotelDTO hotelDTO = new HotelDTO();

    Admin admin = new Admin(1L);

    AdminDTO adminDTO = new AdminDTO(1L);

    @BeforeEach
    void setUp() throws Exception{

        hotel.setOwner(admin);
        hotel.setAreaName("Athens");
        hotel.setDisabled(false);
        hotel.setName("grand Athens");
        hotel.setStars(5);

        hotelDTO.setOwner(admin.getId());
        hotelDTO.setAreaName("Athens");
        hotelDTO.setDisabled(false);
        hotelDTO.setName("grand Athens");
        hotelDTO.setStars(5);

        hotelDTOToHotel = new HotelDTOToHotel(roomDTOToRoom, adminRepository);
    }

    @Test
    void converterTest() {

        //given

        //when
        when(adminRepository.findById(anyLong())).thenReturn(Optional.of(admin));

        //then
        assertEquals(hotel , hotelDTOToHotel.converter(hotelDTO));

    }

}
