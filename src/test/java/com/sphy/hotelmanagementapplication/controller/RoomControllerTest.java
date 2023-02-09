package com.sphy.hotelmanagementapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.configuration.TestAppAdminConfiguration;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/***
 * created by dk
 */
@ExtendWith(MockitoExtension.class)
@Import(TestAppAdminConfiguration.class)
class RoomControllerTest {

	@Mock
	RoomService roomService;

	@Mock
	HotelService hotelService;

	@Mock
	UserService userService;

	@InjectMocks
	RoomController roomController;


	List<RoomDTO> roomDTOS;
	List<Room> rooms;

	Hotel hotel;
	User admin;
	RoomDTO roomDTO1;

	HotelDTO hotelDTO;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		admin = new User(1L);

		hotelDTO = new HotelDTO(11L);
		hotelDTO.setOwner(admin.getId());

		hotel = new Hotel(10L);
		hotel.setOwner(admin);

		rooms = new ArrayList<>();

		Room room1 = new Room();
		room1.setId(1L);
		room1.setName("room1");
		room1.setHotel(hotel);

		Room room2 = new Room();
		room2.setId(2L);
		room2.setHotel(hotel);
		room2.setName("room2");

		rooms.add(room1);
		rooms.add(room2);

		roomDTOS = new ArrayList<>();
		roomDTO1 = new RoomDTO();
		roomDTO1.setId(1L);
		roomDTO1.setName("roomDTO1");
		roomDTO1.setHotel(hotelDTO.getId());
		roomDTO1.setLuxurity(3);
		roomDTO1.setPrice(300);
		roomDTO1.setDisabled(false);
		roomDTOS.add(roomDTO1);

		RoomDTO roomDTO2 = new RoomDTO();
		roomDTO2.setId(2L);
		roomDTO2.setName("roomDTO2");
		roomDTO2.setHotel(hotelDTO.getId());
		roomDTOS.add(roomDTO2);

		mockMvc = MockMvcBuilders
				.standaloneSetup(roomController)
				.build();
	}

	@Test
	void addRoom() throws Exception {
		// Given


		// When
		when(hotelService.getHotelById(anyLong())).thenReturn(hotelDTO);
		when(userService.getUserFromToken(anyString())).thenReturn(admin);
		when(roomService.saveRoomDTO(any())).thenReturn(roomDTO1);

		// Return
		mockMvc.perform(
						post("/api/room/create")
								.header(HttpHeaders.AUTHORIZATION, "Bearer token")
								.content(asJsonString(roomDTO1))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id")
						.value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name")
						.value("roomDTO1"))
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
		when(hotelService.getHotelById(anyLong())).thenReturn(hotelDTO);
		when(userService.getUserFromToken(anyString())).thenReturn(admin);

		// Return
		mockMvc.perform(
						post("/api/rooms/create")
								.header(HttpHeaders.AUTHORIZATION, "Bearer token")								.content(asJsonString(roomDTOS))
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
	void countRooms() throws Exception {

		//given

		//when
		when(roomService.countRooms(anyLong())).thenReturn(1);
		when(userService.getUserFromToken(anyString())).thenReturn(admin);

		//then
		mockMvc.perform(
						get("/api/rooms/quantity")
								.header(HttpHeaders.AUTHORIZATION, "Bearer token"))

				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("@")
						.value(1));
	}

	@Test
	void findAllRooms() throws Exception {
		// Given

		// When
		when(roomService.getRooms(0,10,"id",admin.getId())).thenReturn(roomDTOS);
		when(userService.getUserFromToken(anyString())).thenReturn(admin);
		// Return
		mockMvc.perform(get("/api/rooms/0/10/id")
						.header(HttpHeaders.AUTHORIZATION, "Bearer token"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath(
						"$[0].name",
						Matchers.equalTo("roomDTO1")
				));

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
		when(userService.getUserFromToken(anyString())).thenReturn(admin);
		when(roomService.updateRoom(any())).thenReturn(roomDTO);
		when(hotelService.getHotelById(anyLong())).thenReturn(hotelDTO);

		// Return
		mockMvc.perform(
					put("/api/room/update")
							.header(HttpHeaders.AUTHORIZATION, "Bearer token")
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
		RoomDTO roomDTO = new RoomDTO(1L);
		roomDTO.setHotel(hotelDTO.getId());

		String expected = "Room with id 1 was successfully activated";

		// When
		when(roomService.enableRoom(any())).thenReturn(true);
		when(userService.getUserFromToken(anyString())).thenReturn(admin);
		when(roomService.getRoomById(anyLong())).thenReturn(roomDTO);
		when(hotelService.getHotelById(anyLong())).thenReturn(hotelDTO);

		// Return
		MvcResult result = mockMvc.perform(
						post("/api/room/enable/{id}", 1)
								.header(HttpHeaders.AUTHORIZATION, "Bearer token"))
				.andExpect(status().isOk())
				.andReturn();

		String actual = result.getResponse().getContentAsString();

		assertEquals(expected, actual);

		verify(roomService, times(1)).enableRoom(any());

	}

	@Test
	void disableRoom() throws Exception {
		// Given
		RoomDTO roomDTO = new RoomDTO(1L);
		roomDTO.setHotel(hotelDTO.getId());
		String expected = "Room with id 1 was successfully deactivated";

		// When
		when(roomService.disableRoom(any())).thenReturn(true);
		when(userService.getUserFromToken(anyString())).thenReturn(admin);
		when(roomService.getRoomById(anyLong())).thenReturn(roomDTO);
		when(hotelService.getHotelById(anyLong())).thenReturn(hotelDTO);


		// Return
		MvcResult result = mockMvc.perform(
						post("/api/room/disable/{id}", 1)
								.header(HttpHeaders.AUTHORIZATION, "Bearer token"))
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

    @Test
    void findAllRoomsByHotelId() throws Exception {

		// Given
		RoomDTO roomDTO = new RoomDTO();
		HotelDTO hotelDTO1 = new HotelDTO(1L);
		roomDTO.setId(1L);
		roomDTO.setName("roomName");
		roomDTO.setHotel(1L);
		List<RoomDTO> roomDTOS1 = new ArrayList<>();
		roomDTOS1.add(roomDTO);

		// When
		when(roomService.getRoomsByHotelId(0, 10, "id", 1L)).thenReturn(roomDTOS1);

		// Return
		mockMvc.perform(get("/api/rooms/1/0/10/id")
				.header(HttpHeaders.AUTHORIZATION, "Bearer token"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name").value("roomName"));

		verify(roomService, times(1)).getRoomsByHotelId(anyInt(), anyInt(), anyString(), anyLong());
    }

    @Test
    void countRoomsOfHotel() {
    }
}