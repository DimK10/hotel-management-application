package com.sphy.hotelmanagementapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.domain.Hotel;
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

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {

    @Mock
    HotelService hotelService;

    @Mock
    RoomService roomService;

    @Mock
    RoomController roomController;

    @InjectMocks
    HotelController hotelController;

    List<Hotel> hotels;
    List<HotelDTO> hotelDTOS1;

    List<RoomDTO> roomDTOS;
    List<Room> rooms;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        hotels=new ArrayList<>();


        Hotel hotel = new Hotel(1L);
        hotel.setName("hotel");

        Hotel hotel1 = new Hotel(2L);
        hotel1.setName("hotel1");


        hotels.add(hotel);
        hotels.add(hotel1);

        hotelDTOS1 = new ArrayList<>();
        RoomDTO room = new RoomDTO();
        room.setId(1L);
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("hotelDTO");
        hotelDTO.setStars(3);
        hotelDTO.setOwner(1L);
        hotelDTO.setRooms(new HashSet<>());
        hotelDTO.setAreaName("Athens");
        hotelDTO.setDisabled(false);
        hotelDTO.getRooms().add(room);

        hotelDTOS1.add(hotelDTO);

        RoomDTO room1 = new RoomDTO();
        room1.setId(1L);
        HotelDTO hotelDTO1 = new HotelDTO();
        hotelDTO1.setId(1L);
        hotelDTO1.setName("hotelDTO1");
        hotelDTO1.setStars(3);
        hotelDTO1.setOwner(1L);
        hotelDTO1.setRooms(new HashSet<>());
        hotelDTO1.setAreaName("Athens");
        hotelDTO1.setDisabled(false);
        hotelDTO1.getRooms().add(room1);

        hotelDTOS1.add(hotelDTO1);

//		rooms.forEach(System.out::println);
//		roomDTOS.forEach(System.out::println);

        mockMvc = MockMvcBuilders
                .standaloneSetup(hotelController)
                .build();
    }

    @Test
    void addHotel() throws Exception {
        // Given
        RoomDTO room = new RoomDTO();
        room.setId(1L);
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("hotelDTO");
        hotelDTO.setStars(3);
        hotelDTO.setOwner(1L);
        hotelDTO.setRooms(new HashSet<>());
        hotelDTO.setAreaName("Athens");
        hotelDTO.setDisabled(false);
        hotelDTO.getRooms().add(room);


        // When
        when(hotelService.saveHotelDTO(any())).thenReturn(hotelDTO);


        // Return
        mockMvc.perform(
                        post("/api/hotel/create")
                                .content(asJsonString(hotelDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.owner")
                .value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("hotelDTO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars")
                        .value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms")
                        .isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms[0].id")
                        .value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.areaName")
                        .value("Athens"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disabled")
                        .value("false"));


        verify(hotelService, times(1)).saveHotelDTO(any());

    }

    @Test
    void addHotels() throws Exception {
        // Given



        // When
        when(hotelService.saveHotels(any())).thenReturn(hotelDTOS1);

        // Return
        mockMvc.perform(
                        post("/api/hotels/create")
                                .content(asJsonString(hotelDTOS1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]")
                        .isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]",hasSize(2)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name")
                        .value("hotelDTO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name")
                        .value("hotelDTO1"));

        verify(hotelService, times(1)).saveHotels(any());
    }

    @Test
    void findAllHotels() throws Exception {
        // Given


        // When
        when(hotelService.getHotels()).thenReturn(hotelDTOS1);

        // Return
        mockMvc.perform(get("/api/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath(
                        "$[0].name",
                        Matchers.equalTo("hotelDTO")
                ));

        // verify that roomService was executed inside findAllRooms() only once
        verify(hotelService, times(1)).getHotels();
    }

    @Test
    void findHotelById() throws Exception {
        // Given
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);

        // When
        when(hotelService.getHotelById(anyLong())).thenReturn(hotelDTO);

        // Return
        mockMvc.perform(get("/api/hotelId/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(hotelService, times(1)).getHotelById(anyLong());
    }

    @Test
    void findHotelByName() throws Exception {
        // Given
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("hotelName");

        // When
        when(hotelService.getHotelByName(anyString())).thenReturn(hotelDTO);

        // Return
        mockMvc.perform(get("/api/hotelName/{name}","hotelName"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("hotelName"));

        verify(hotelService, times(1)).getHotelByName(anyString());
    }

    @Test
    void updateHotel() throws Exception {
        // Given
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("hotelDTO");
        hotelDTO.setDisabled(false);
        hotelDTO.setOwner(1L);
        hotelDTO.setStars(5);
        hotelDTO.setAreaName("athens");

        RoomDTO room = new RoomDTO();
        room.setId(1L);
        hotelDTO.getRooms().add(room);

        // When
        when(hotelService.updateHotel(any())).thenReturn(hotelDTO);

        // Return
        mockMvc.perform(
                        put("/api/hotel/update")
                                .content(asJsonString(hotelDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("hotelDTO"));


        verify(hotelService, times(1)).updateHotel(any());
    }

    @Test
    void enableHotel() throws Exception {
        // Given
        String expected = "Hotel with id 1 was successfully activated";

        // When
        when(hotelService.enableHotel(any())).thenReturn(true);

        // Return
        MvcResult result = mockMvc.perform(
                        post("/api/hotel/enable/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(hotelService, times(1)).enableHotel(any());

    }

    @Test
    void disableHotel() throws Exception {
        // Given
        String expected = "Hotel with id 1 was successfully deactivated";

        // When
        when(hotelService.disableHotel(any())).thenReturn(true);

        // Return
        MvcResult result = mockMvc.perform(
                        post("/api/hotel/disable/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(hotelService, times(1)).disableHotel(any());

    }

	/**
	 * This method converts an object to a string representation in JSON format.
	 * Basically, it serializes the object in json format.
	 * @param obj The object to be serialized.
	 * @return THe String representation of given object in JSON format.
	 */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
