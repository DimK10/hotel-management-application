package com.sphy.hotelmanagementapplication.controller;

import java.util.ArrayList;
import java.util.List;

import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

	@Mock
	RoomService roomService;

	@InjectMocks
	RoomController roomController;

	List<RoomDTO> roomDTOS;
	List<Room> rooms;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {

		rooms = new ArrayList<>();

		Room room1 = new Room();
		room1.setId(1L);
		room1.setName("room1");

		Room room2 = new Room();
		room2.setId(2L);
		room2.setName("room2");

		rooms.add(room1);
		rooms.add(room2);

		roomDTOS = new ArrayList<>();
		RoomDTO roomDTO1 = new RoomDTO();
		roomDTO1.setId(1L);
		roomDTO1.setName("roomDTO1");
		roomDTOS.add(roomDTO1);

		RoomDTO roomDTO2 = new RoomDTO();
		roomDTO2.setId(2L);
		roomDTO2.setName("roomDTo2");
		roomDTOS.add(roomDTO2);

//		rooms.forEach(System.out::println);
//		roomDTOS.forEach(System.out::println);

		mockMvc = MockMvcBuilders
				.standaloneSetup(roomController)
				.build();
	}

	@Test
	void addRoom() {
	}

	@Test
	void addRooms() {
	}

	@Test
	void findAllRooms() throws Exception {
		// Given

		// When
		when(roomService.getRooms()).thenReturn(roomDTOS);

		// Return
		mockMvc.perform(get("/api/rooms"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath(
						"$[0].name",
						Matchers.equalTo("roomDTO1")
				));

		// verify that roomService was executed inside findAllRooms() only once
		verify(roomService, times(1)).getRooms();
	}

	@Test
	void findRoomById() throws Exception {
		// Given
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setId(1L);

		// When
		when(roomService.getRoomById(anyLong())).thenReturn(roomDTO);

		// Return
		mockMvc.perform(get("/api/roomId/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));

	}

	@Test
	void findRoomByName() {
	}

	@Test
	void updateRoom() {
	}

	@Test
	void enableRoom() {
	}

	@Test
	void disableRoom() {
	}

//	@Mock
//	RoomService roomService;
//
//	@Mock
//	ModelMapperFactory modelMapperFactory;
//
//	@InjectMocks
//	RoomController roomController;
//
//	List<Room> rooms;
//
//	ModelMapper modelMapper;
//
//	MockMvc mockMvc;
//
//	@BeforeEach
//	void setUp() {
//		rooms = new ArrayList<>();
//
//		Room room1 = new Room();
//		room1.setId(1L);
//		room1.setName("room1");
//
//		Room room2 = new Room();
//		room2.setId(2L);
//		room2.setName("room2");
//
//		rooms.add(room1);
//		rooms.add(room2);
//
//		rooms.forEach(System.out::println);
//
//		modelMapper = new ModelMapper();
//
//		modelMapper.createTypeMap(Room.class, RoomDTO.class);
//
//		ModelMapperFactory modelMapperFactory =
//				new ModelMapperFactory(modelMapper, new BaseEntitySetToSetLongConverter());
//
//		modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);
//
//		mockMvc = MockMvcBuilders
//				.standaloneSetup(roomController)
//				.build();
//	}
//
//	@Test
//	void addRoom() {
//
//	}
//
//	@Test
//	void addRooms() {
//	}
//
//	@Test
//	void findAllRooms() throws Exception {
//
//		when(roomService.getRooms()).thenReturn(rooms);
//		when(modelMapperFactory.create(ModelMapperType.ROOM)).thenReturn(modelMapper);
//
//		mockMvc.perform(get("/api/rooms"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", Matchers.hasSize(2)))
//				.andExpect(jsonPath("$[0].name", Matchers.equalTo("room1")));
////				.andDo(print());
//	}
//
//	@Test
//	void findRoomById() {
//	}
//
//	@Test
//	void findRoomByName() {
//	}
//
//	@Test
//	void updateRoom() {
//	}
//
//	@Test
//	void enableRoom() {
//	}
//
//	@Test
//	void disableRoom() {
//	}
}