package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
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
public class AdminToUserADTOTest {

    @Mock
    AdminToAdminDTO adminToAdminDTO;

    @Mock
    HotelToHotelDTO hotelToHotelDTO;
    UserA userA = new UserA();

    AdminDTO adminDTO = new AdminDTO();

    Hotel hotel = new Hotel(1L);

    HotelDTO hotelDTO = new HotelDTO(1L);

    @BeforeEach
    void setUp(){

        hotel.getRooms().add(new Room(1L));
        hotelDTO.getRooms().add(new RoomDTO(1L));

        userA.setId(1L);
        userA.setUsername("ksenodoxos");
        userA.setFirstname("giorgos");
        userA.setLastname("papadopoulos");
        userA.setHashedPassword("asdfghjk");
        userA.setEmail("papadopoulos@gmail.com");
        userA.setEmailVerify(true);
        userA.getHotels().add(hotel);

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
        assertEquals(adminDTO.getId() , adminToAdminDTO.converter(userA).getId());
        assertEquals(adminDTO.getLastname() , adminToAdminDTO.converter(userA).getLastname());
        assertEquals(adminDTO.getFirstname() , adminToAdminDTO.converter(userA).getFirstname());
        assertEquals(adminDTO.getUsername(), adminToAdminDTO.converter(userA).getUsername());
        assertEquals(adminDTO.getHashedPassword(), adminToAdminDTO.converter(userA).getHashedPassword());
        assertEquals(adminDTO.getEmail(), adminToAdminDTO.converter(userA).getEmail());
        assertEquals(adminDTO.getHotels(), adminToAdminDTO.converter(userA).getHotels());

    }
}
