package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.converter.UserDTOToUser;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.domain.User.Role;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.repositories.UserRepository;
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
public class UserDTOToUserTest {

    @Mock
	UserDTOToUser userDTOToUser;

    @Mock
    HotelRepository hotelRepository;

	@Mock
	RoomRepository roomRepository;

	@Mock
	UserRepository userRepository;


	User admin = new User();

    UserDTO userDTO = new UserDTO();

    Hotel hotel = new Hotel(1L);

    HotelDTO hotelDTO = new HotelDTO(1L);

	@BeforeEach
   void setUp() throws Exception{

       admin.setId(1L);
	   admin.setRole(Role.ADMIN);
       admin.setUsername("ksenodoxos");
       admin.setFirstname("giorgos");
       admin.setLastname("papadopoulos");
       admin.setHashedPassword("asdfghjk");
       admin.setEmail("papadopoulos@gmail.com");
       admin.setEmailVerify(true);
       admin.getHotels().add(hotel);

		userDTO.setId(1L);
		userDTO.setRole(String.valueOf(Role.ADMIN));
		userDTO.setUsername("ksenodoxos");
		userDTO.setFirstname("giorgos");
		userDTO.setLastname("papadopoulos");
		userDTO.setHashedPassword("asdfghjk");
		userDTO.setEmail("papadopoulos@gmail.com");
		userDTO.setEmailVerify(true);
		userDTO.getHotelDTOS().add(hotelDTO);

       userDTOToUser = new UserDTOToUser(new HotelToHotelDTO(new RoomToRoomDTO(new OrderToOrderDTO(roomRepository, userRepository), hotelRepository)), new OrderToOrderDTO(roomRepository, userRepository));
   }

    @Test
    void converterTest() {

        //given

        //when

        //then
        assertEquals(admin, userDTOToUser.converter(userDTO));

    }
}
