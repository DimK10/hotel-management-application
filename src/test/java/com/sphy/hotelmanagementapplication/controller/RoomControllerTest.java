package com.sphy.hotelmanagementapplication.controller;

import java.util.ArrayList;
import java.util.List;

import com.sphy.hotelmanagementapplication.service.RoomService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

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