package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.AdminDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class HotelToHotelDTOTest {

    @Mock
    HotelToHotelDTO hotelToHotelDTO;

    @Mock
    RoomToRoomDTO roomToRoomDTO;

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

        hotelToHotelDTO = new HotelToHotelDTO(roomToRoomDTO);
    }

    @Test
    void converterTest() {

        //given

        //when

        //then
        assertEquals(hotelDTO.getId() , hotelToHotelDTO.converter(hotel).getId());

    }

}

