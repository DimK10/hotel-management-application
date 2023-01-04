package com.sphy.hotelmanagementapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.configuration.TestAppAdminConfiguration;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.BasicSearchDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.service.HotelService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/***
 * created by gp
 */
@ExtendWith(MockitoExtension.class)
@Import(TestAppAdminConfiguration.class)
public class HotelControllerTest {

    @Mock
    HotelService hotelService;

    @Mock
    UserService userService;

    @InjectMocks
    HotelController hotelController;

    List<Hotel> hotels = new ArrayList<>();
    List<HotelDTO> hotelDTOS1 = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        Hotel hotel = new Hotel(1L);
        hotel.setName("hotel");

        Hotel hotel1 = new Hotel(2L);
        hotel1.setName("hotel1");


        hotels.add(hotel);
        hotels.add(hotel1);

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

        mockMvc = MockMvcBuilders
                .standaloneSetup(hotelController)
                .build();
    }

    @Test
    void countHotels() throws Exception {

        //given
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);

        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        hotelDTO.setOwner(1L);

        //when
        when(hotelService.countHotels(anyLong())).thenReturn(1);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);

        //then
        mockMvc.perform(
                        get("/api/hotels/quantity/{userId}",1L)
                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("@")
                        .value(1));
    }

    @Test
    void addHotel() throws Exception {
        // Given
        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        RoomDTO room = new RoomDTO();
        room.setId(1L);
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("hotelDTO");
        hotelDTO.setStars(3);
        hotelDTO.setOwner(admin.getId());
        hotelDTO.setRooms(new HashSet<>());
        hotelDTO.setAreaName("Athens");
        hotelDTO.setDisabled(false);
        hotelDTO.getRooms().add(room);


        // When
        when(hotelService.saveHotelDTO(any())).thenReturn(hotelDTO);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);


        // Return
        mockMvc.perform(
                        post("/api/hotel/create")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
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
        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);


        // When
        when(hotelService.saveHotels(any())).thenReturn(hotelDTOS1);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);

        // Return
        mockMvc.perform(
                        post("/api/hotels/create")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
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
    void findAllHotelsByPage() throws Exception {
        // Given
        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        // When
        when(hotelService.getHotels(0,10,"id", 1L)).thenReturn(hotelDTOS1);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);

        // Return
        mockMvc.perform(get("/api/hotels/0/10/id")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath(
                        "$[0].name",
                        Matchers.equalTo("hotelDTO")
                ));

    }

    @Test
    @WithMockUser
    void findHotelById() throws Exception {
        // Given
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);

        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        hotelDTO.setOwner(1L);

        // When
        when(hotelService.getHotelById(anyLong(), anyLong())).thenReturn(hotelDTO);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);


        // Return
        mockMvc.perform(get("/api/hotelId/1").header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(userService, times(1)).getUserFromToken(anyString());
        verify(hotelService, times(1)).getHotelById(anyLong(), anyLong());
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
        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("hotelDTO");
        hotelDTO.setDisabled(false);
        hotelDTO.setOwner(admin.getId());
        hotelDTO.setStars(5);
        hotelDTO.setAreaName("athens");

        RoomDTO room = new RoomDTO();
        room.setId(1L);
        hotelDTO.getRooms().add(room);

        // When
        when(hotelService.updateHotel(any())).thenReturn(hotelDTO);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);


        // Return
        mockMvc.perform(
                        put("/api/hotel/update")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(asJsonString(hotelDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("hotelDTO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.areaName")
                        .value("athens"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disabled")
                        .value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars")
                        .value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.owner")
                        .value(1L));


        verify(hotelService, times(1)).updateHotel(any());
    }

    @Test
    void enableHotel() throws Exception {
        // Given
        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setOwner(admin.getId());

        String expected = "Hotel with id 1 was successfully activated";

        // When
        when(hotelService.enableHotel(any())).thenReturn(true);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);
        when(hotelService.getHotelById(any())).thenReturn(hotelDTO);

        // Return
        MvcResult result = mockMvc.perform(
                        post("/api/hotel/enable/{id}", 1)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(hotelService, times(1)).enableHotel(any());

    }

    @Test
    void disableHotel() throws Exception {
        // Given

        User admin = new User(1L);
        admin.setRole(User.Role.ADMIN);

        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setOwner(admin.getId());

        String expected = "Hotel with id 1 was successfully deactivated";

        // When
        when(hotelService.disableHotel(any())).thenReturn(true);
        when(userService.getUserFromToken(anyString())).thenReturn(admin);
        when(hotelService.getHotelById(any())).thenReturn(hotelDTO);

        // Return
        MvcResult result = mockMvc.perform(
                        post("/api/hotel/disable/{id}", 1)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(hotelService, times(1)).disableHotel(any());

    }

    @Test
    void findHotelBasicSearch() throws Exception{

        //given
        User client = new User(10L);

        client.setRole(User.Role.CLIENT);

        Set<Hotel> hotels1 = new HashSet<>();

        Hotel hotel = new Hotel(1L);

        hotel.setName("ksenia");
        hotel.setAreaName("athens");

        Room room = new Room(2L);

        Order order = new Order(3L, LocalDate.of(2017, 12, 12),
                LocalDate.of(2017, 12, 20),
                false, new User(5L), room);

        room.getOrders().add(order);

        hotel.getRooms().add(room);

        hotels1.add(hotel);

        Set<HotelDTO> hotel1DTOS = new HashSet<>();

        HotelDTO hotelDTO = new HotelDTO(1L);

        hotelDTO.setName("ksenia");
        hotelDTO.setAreaName("athens");

        hotel1DTOS.add(hotelDTO);

        BasicSearchDTO basicSearchDTO1 = new BasicSearchDTO();

//        basicSearchDTO1.setCheckInDate(LocalDate.of(2019, 12, 13));
//        basicSearchDTO1.setCheckOutDate(LocalDate.of(2019, 12, 20));
//        basicSearchDTO1.setNameOrLocation("athens");

        BasicSearchDTO basicSearchDTO2 = new BasicSearchDTO();

        basicSearchDTO2.setCheckInDate(LocalDate.of(2019, 12, 13));
        basicSearchDTO2.setCheckOutDate(LocalDate.of(2019, 12, 20));
        basicSearchDTO2.setNameOrLocation("ksenia");

        //when
        when(userService.getUserFromToken(anyString())).thenReturn(client);
        when(hotelService.getHotelBasicSearch(any())).thenReturn(hotel1DTOS);

        //then

         mockMvc.perform(
                        get("/api/hotel/basic/search")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                                .content(asJsonString(basicSearchDTO1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                ;


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
