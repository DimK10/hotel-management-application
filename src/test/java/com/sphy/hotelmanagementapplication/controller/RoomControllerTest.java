package com.sphy.hotelmanagementapplication.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * created by dk
 */
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
		roomDTO2.setName("roomDTO2");
		roomDTOS.add(roomDTO2);

		mockMvc = MockMvcBuilders
				.standaloneSetup(roomController)
				.build();
	}

	@Test
	void addRoom() throws Exception {
		// Given
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setId(1L);
		roomDTO.setName("roomDTO");
		roomDTO.setLuxurity(3);
		roomDTO.setHotel(1L);
		roomDTO.setOrders(new HashSet<>());
		roomDTO.setPrice(300);
		roomDTO.setDisabled(false);

		// When
		when(roomService.saveRoomDTO(any())).thenReturn(roomDTO);

		// Return
		mockMvc.perform(
						post("/api/room/create")
								.content(asJsonString(roomDTO))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id")
						.value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name")
						.value("roomDTO"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.luxurity")
						.value("3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orders")
						.exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.orders")
						.isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.orders")
						.isEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price")
						.value("300"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.disabled")
						.value("false"));


		verify(roomService, times(1)).saveRoomDTO(any());

	}

	@Test
	void addRooms() throws Exception {
		// Given

		// When
		when(roomService.saveRooms(any())).thenReturn(roomDTOS);

		// Return
		mockMvc.perform(
						post("/api/rooms/create")
								.content(asJsonString(roomDTOS))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*]")
						.isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*]",hasSize(2)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name")
						.value("roomDTO1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].name")
						.value("roomDTO2"));

		verify(roomService, times(1)).saveRooms(any());
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

		verify(roomService, times(1)).getRoomById(anyLong());
	}

	@Test
	void findRoomByName() throws Exception {
		// Given
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setId(1L);
		roomDTO.setName("roomName");

		// When
		when(roomService.getRoomByName(anyString())).thenReturn(roomDTO);

		// Return
		mockMvc.perform(get("/api/roomName/roomName"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("roomName"));

		verify(roomService, times(1)).getRoomByName(anyString());
	}

	@Test
	void updateRoom() throws Exception {
		// Given
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setId(1L);
		roomDTO.setName("roomDTO");
		roomDTO.setLuxurity(3);
		roomDTO.setHotel(1L);
		roomDTO.setOrders(new HashSet<>());
		roomDTO.setPrice(300);
		roomDTO.setDisabled(false);

		// When
		when(roomService.updateRoom(any())).thenReturn(roomDTO);

		// Return
		mockMvc.perform(
					put("/api/room/update")
							.content(asJsonString(roomDTO))
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name")
						.value("roomDTO"));


		verify(roomService, times(1)).updateRoom(any());
	}

	@Test
	void enableRoom() throws Exception {
		// Given
		String expected = "Room with id 1 was successfully activated";

		// When
		when(roomService.enableRoom(any())).thenReturn(true);

		// Return
		MvcResult result = mockMvc.perform(
						post("/api/room/enable/{id}", 1))
				.andExpect(status().isOk())
				.andReturn();

		String actual = result.getResponse().getContentAsString();

		assertEquals(expected, actual);

		verify(roomService, times(1)).enableRoom(any());

	}

	@Test
	void disableRoom() throws Exception {
		// Given
		String expected = "Room with id 1 was successfully deactivated";

		// When
		when(roomService.disableRoom(any())).thenReturn(true);

		// Return
		MvcResult result = mockMvc.perform(
						post("/api/room/disable/{id}", 1))
				.andExpect(status().isOk())
				.andReturn();

		String actual = result.getResponse().getContentAsString();

		assertEquals(expected, actual);

		verify(roomService, times(1)).disableRoom(any());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}