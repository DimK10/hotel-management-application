package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.AdminDTOToAdmin;
import com.sphy.hotelmanagementapplication.domain.Admin;
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
public class AdminDTOToAdminTest {

    @Mock
    AdminDTOToAdmin adminDTOToAdmin;

    @Mock
    HotelRepository hotelRepository;

    Admin admin = new Admin();

    AdminDTO adminDTO = new AdminDTO();

    Hotel hotel = new Hotel(1L);

    HotelDTO hotelDTO = new HotelDTO(1L);

   @BeforeEach
   void setUp() throws Exception{

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

       adminDTOToAdmin = new AdminDTOToAdmin(hotelRepository);
   }

    @Test
    void converterTest() {

        //given

        //when

        //then
        assertEquals(admin , adminDTOToAdmin.converter(adminDTO));

    }
}
