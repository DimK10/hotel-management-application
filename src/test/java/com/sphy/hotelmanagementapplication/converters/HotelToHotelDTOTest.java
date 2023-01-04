package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.HotelAmenityToHotelAmenityDTO;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
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
public class HotelToHotelDTOTest {

	@Mock
	HotelToHotelDTO hotelToHotelDTO;

	@Mock
	RoomToRoomDTO roomToRoomDTO;

	@Mock
	HotelAmenityToHotelAmenityDTO hotelAmenityToHotelAmenityDTO;

	Hotel hotel = new Hotel();

	HotelDTO hotelDTO = new HotelDTO();

	User admin = new User(1L);

	Room room = new Room(1L);

	RoomDTO roomDTO = new RoomDTO(1L);

	@BeforeEach
	void setUp() throws Exception {

		hotel.setOwner(admin);
		hotel.setAreaName("Athens");
		hotel.setDisabled(false);
		hotel.setName("grand lala");
		hotel.setStars(5);
		hotel.getRooms().add(room);
		hotel.setAddress("address");

		hotelDTO.setOwner(admin.getId());
		hotelDTO.setAreaName("Athens");
		hotelDTO.setDisabled(false);
		hotelDTO.setName("grand lala");
		hotelDTO.setStars(5);
		hotelDTO.getRooms().add(roomDTO);
		hotelDTO.setAddress("address");

		hotelToHotelDTO = new HotelToHotelDTO(roomToRoomDTO, hotelAmenityToHotelAmenityDTO);
	}

	@Test
	void converterTest() {

		//given

		//when
		when(roomToRoomDTO.converter(any())).thenReturn(roomDTO);

		//then
		assertEquals(hotelDTO.getId(), hotelToHotelDTO.converter(hotel).getId());
		assertEquals(hotelDTO.getName(), hotelToHotelDTO.converter(hotel).getName());
		assertEquals(hotelDTO.getAreaName(), hotelToHotelDTO.converter(hotel).getAreaName());
		assertEquals(hotelDTO.getStars(), hotelToHotelDTO.converter(hotel).getStars());
		assertEquals(hotelDTO.getOwner(), hotelToHotelDTO.converter(hotel).getOwner());
		assertEquals(hotelDTO.getRooms(), hotelToHotelDTO.converter(hotel).getRooms());
		assertEquals(hotelDTO.getAddress(), hotelToHotelDTO.converter(hotel).getAddress());

	}

}

