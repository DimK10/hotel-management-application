package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.ClientRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

	@Mock
	RoomRepository roomRepository;

	@Mock
	HotelRepository hotelRepository;

	@Mock
	ClientRepository clientRepository;

	RoomService roomService;

	List<Room> rooms;

	List<RoomDTO> roomDTOS;

	final Long id = 1L;
	final String name = "room";

	@BeforeEach
	void setUp() throws Exception {

		rooms = new ArrayList<>();

		Hotel hotel = new Hotel(1L);

		Room room1 = new Room();
		room1.setId(1L);
		room1.setName("room1");
		room1.setHotel(hotel);

		Room room2 = new Room();
		room2.setId(2L);
		room2.setName("room2");
		room2.setHotel(hotel);

		rooms.add(room1);
		rooms.add(room2);

		roomDTOS = new ArrayList<>();
		RoomDTO roomDTO1 = new RoomDTO();
		roomDTO1.setId(1L);
		roomDTO1.setName("roomDTO1");
		roomDTO1.setHotel(1L);
		roomDTOS.add(roomDTO1);

		RoomDTO roomDTO2 = new RoomDTO();
		roomDTO2.setId(2L);
		roomDTO2.setName("roomDTO2");
		roomDTO2.setHotel(1L);
		roomDTOS.add(roomDTO2);


		roomService = new RoomService(
				roomRepository, hotelRepository,
				new RoomDTOToRoom(hotelRepository, new OrderDTOToOrder(roomRepository, clientRepository)),
				new RoomToRoomDTO(new OrderToOrderDTO(roomRepository, clientRepository), hotelRepository));
	}

	@Test
	void saveRoomDTO() {
		// todo
	}

	@Test
	void saveRooms() {
		// todo
	}

	@Test
	void getRooms() throws Exception {
		// given

		// when
		when(roomRepository.findAll()).thenReturn(rooms);


		//then
		List<RoomDTO> roomDTOList = roomService.getRooms();

		assertEquals(2, roomDTOList.size());
		assertTrue(EqualsBuilder.reflectionEquals(roomDTOS,roomDTOList));
	}

	@Test
	void getRoomById() throws Exception {
		// given
		Hotel hotel = new Hotel(1L);
		Optional<Hotel> hotelOptional = Optional.of(hotel);

		Room room = new Room();
		room.setId(1L);
		room.setName("room");
		room.setHotel(hotel);

		Optional<Room> roomOptional = Optional.of(room);

		// when
		when(roomRepository.findById(anyLong())).thenReturn(roomOptional);
		when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


		//then
		RoomDTO roomDTOActual = roomService.getRoomById(anyLong());

		assertEquals(room.getName(),roomDTOActual.getName());
	}

	@Test
	void getRoomByName() throws Exception {
		// given
		Hotel hotel = new Hotel(1L);
		Optional<Hotel> hotelOptional = Optional.of(hotel);

		Room room = new Room();
		room.setId(1L);
		room.setName("room");
		room.setHotel(hotel);

		Optional<Room> roomOptional = Optional.of(room);

		// when
		when(roomRepository.findByName(anyString())).thenReturn(roomOptional);
		when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


		//then
		RoomDTO roomDTOActual = roomService.getRoomByName(anyString());

		assertEquals(room.getName(),roomDTOActual.getName());
	}

	@Test
	void enableRoom() {
		// given
		boolean expected = true;
		Room room = new Room();
		room.setId(1L);
		room.setName("room");
		room.setDisabled(true);

		Optional<Room> roomOptional = Optional.of(room);

		// when
		when(roomRepository.existsById(anyLong())).thenReturn(true);
		when(roomRepository.findById(anyLong())).thenReturn(roomOptional);

		//then
		boolean actual = roomService.enableRoom(anyLong());

		assertEquals(expected,actual);
	}

	@Test
	void disableRoom() {
		// given
		boolean expected = true;
		Room room = new Room();
		room.setId(1L);
		room.setName("room");
		room.setDisabled(true);

		Optional<Room> roomOptional = Optional.of(room);

		// when
		when(roomRepository.existsById(anyLong())).thenReturn(true);
		when(roomRepository.findById(anyLong())).thenReturn(roomOptional);


		//then
		boolean actual = roomService.disableRoom(anyLong());

		assertEquals(expected,actual);
	}

	@Test
	void updateRoom() {
		// todo
	}
}