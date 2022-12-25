package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.converter.UserToUserDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.domain.User.Role;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class UserToUserDTOTest {

    @Mock
	UserToUserDTO userToUserDTO;

    @Mock
    HotelToHotelDTO hotelToHotelDTO;

	@Mock
	OrderToOrderDTO orderToOrderDTO;

    User admin = new User();

    UserDTO adminDTO = new UserDTO();

    Hotel hotel = new Hotel(1L);

    HotelDTO hotelDTO = new HotelDTO(1L);

    @BeforeEach
    void setUp(){

        hotel.getRooms().add(new Room(1L));
        hotelDTO.getRooms().add(new RoomDTO(1L));

        admin.setId(1L);
		admin.setRole(Role.ADMIN);
        admin.setUsername("ksenodoxos");
        admin.setFirstname("giorgos");
        admin.setLastname("papadopoulos");
        admin.setPassword("asdfghjk");
        admin.setHashedPassword("asdfghjk");
        admin.setEmail("papadopoulos@gmail.com");
        admin.setEmailVerify(true);
        admin.getHotels().add(hotel);

        adminDTO.setId(1L);
		adminDTO.setRole(String.valueOf(Role.ADMIN));
        adminDTO.setUsername("ksenodoxos");
        adminDTO.setFirstname("giorgos");
        adminDTO.setLastname("papadopoulos");
        adminDTO.setEmail("papadopoulos@gmail.com");
        adminDTO.setEmailVerify(true);
        adminDTO.getHotelDTOS().add(hotelDTO);

        userToUserDTO = new UserToUserDTO(hotelToHotelDTO, orderToOrderDTO);
    }

    @Test
    void converterTest() {

        //given

        //when

        when(hotelToHotelDTO.converter(any())).thenReturn(hotelDTO);

        //then
        assertEquals(adminDTO.getId() , userToUserDTO.converter(admin).getId());
        assertEquals(adminDTO.getRole() , userToUserDTO.converter(admin).getRole());
        assertEquals(adminDTO.getLastname() , userToUserDTO.converter(admin).getLastname());
        assertEquals(adminDTO.getFirstname() , userToUserDTO.converter(admin).getFirstname());
        assertEquals(adminDTO.getUsername(), userToUserDTO.converter(admin).getUsername());
        assertNull(userToUserDTO.converter(admin).getPassword());
		assertNull(userToUserDTO.converter(admin).getHashedPassword());
        assertEquals(adminDTO.getEmail(), userToUserDTO.converter(admin).getEmail());
        assertEquals(adminDTO.getHotelDTOS(), userToUserDTO.converter(admin).getHotelDTOS());

    }
}
