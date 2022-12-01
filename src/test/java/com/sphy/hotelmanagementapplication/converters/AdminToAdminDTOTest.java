package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.AdminToAdminDTO;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.AdminDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class AdminToAdminDTOTest {

    @Mock
    AdminToAdminDTO adminToAdminDTO;

    @Mock
    HotelToHotelDTO hotelToHotelDTO;
    Admin admin = new Admin();

    AdminDTO adminDTO = new AdminDTO();

    Hotel hotel = new Hotel(1L);

    HotelDTO hotelDTO = new HotelDTO(1L);

    @BeforeEach
    void setUp(){

        hotel.getRooms().add(new Room(1L));
        hotelDTO.getRooms().add(new RoomDTO(1L));

        admin.setId(1L);
        admin.setUsername("ksenodoxos");
        admin.setFirstname("giorgos");
        admin.setLastname("papadopoulos");
        admin.setHashedPassword("asdfghjk");
        admin.setEmail("papadopoulos@gmail.com");
        admin.setEmailVerify(true);
        admin.getHotels().add(hotel);

        adminDTO.setId(1L);
        adminDTO.setUsername("ksenodoxos");
        adminDTO.setFirstname("giorgos");
        adminDTO.setLastname("papadopoulos");
        adminDTO.setHashedPassword("asdfghjk");
        adminDTO.setEmail("papadopoulos@gmail.com");
        adminDTO.setEmailVerify(true);
        adminDTO.setTransactionId("1234567");
        adminDTO.getHotels().add(hotelDTO);

        adminToAdminDTO = new AdminToAdminDTO(hotelToHotelDTO);
    }

    @Test
    void converterTest() {

        //given

        //when

        when(hotelToHotelDTO.converter(any())).thenReturn(hotelDTO);

        //then
        assertEquals(adminDTO.getId() , adminToAdminDTO.converter(admin).getId());
        assertEquals(adminDTO.getLastname() , adminToAdminDTO.converter(admin).getLastname());
        assertEquals(adminDTO.getFirstname() , adminToAdminDTO.converter(admin).getFirstname());
        assertEquals(adminDTO.getUsername(), adminToAdminDTO.converter(admin).getUsername());
        assertEquals(adminDTO.getHashedPassword(), adminToAdminDTO.converter(admin).getHashedPassword());
        assertEquals(adminDTO.getEmail(), adminToAdminDTO.converter(admin).getEmail());
        assertEquals(adminDTO.getHotels(), adminToAdminDTO.converter(admin).getHotels());

    }
}
