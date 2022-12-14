package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.AdminDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
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
public class AdminDTOToUserATest {

    @Mock
    AdminDTOToAdmin adminDTOToAdmin;

    @Mock
    HotelRepository hotelRepository;

    UserA userA = new UserA();

    AdminDTO adminDTO = new AdminDTO();

    Hotel hotel = new Hotel(1L);

    HotelDTO hotelDTO = new HotelDTO(1L);

   @BeforeEach
   void setUp() throws Exception{

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

       adminDTOToAdmin = new AdminDTOToAdmin(hotelRepository);
   }

    @Test
    void converterTest() {

        //given

        //when

        //then
        assertEquals(userA, adminDTOToAdmin.converter(adminDTO));

    }
}
