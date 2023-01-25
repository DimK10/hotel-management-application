package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
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
    UserRepository userRepository;

    Hotel hotel = new Hotel();

    HotelDTO hotelDTO = new HotelDTO();

    User admin = new User(1L);

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

        hotelDTOToHotel = new HotelDTOToHotel(roomDTOToRoom, userRepository);
    }

    @Test
    void converterTest() {

        //given

        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(admin));

        //then
        assertEquals(hotel , hotelDTOToHotel.converter(hotelDTO));

    }

}
