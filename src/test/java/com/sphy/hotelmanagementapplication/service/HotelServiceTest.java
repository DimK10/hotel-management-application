package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.*;
import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
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

/***
 * created by gp
 */

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {


        @Mock
        RoomRepository roomRepository;

        @Mock
        HotelRepository hotelRepository;

        @Mock
        ClientRepository clientRepository;

        @Mock
        AdminRepository adminRepository;

        @Mock
        RoomService roomService;


        HotelService hotelService;

        List<Hotel> hotels;

        List<HotelDTO> hotelDTOS;

        List<Room> rooms;

        List<Room> rooms1;

        List<RoomDTO> roomDTOS;

        List<RoomDTO> roomDTOS1;

        final Long id = 1L;
        final String name = "hotel";

        @BeforeEach
        void setUp() throws Exception {
            hotels=new ArrayList<>();

            rooms = new ArrayList<>();
            rooms1 = new ArrayList<>();

            Admin admin = new Admin(1L);

            Hotel hotel = new Hotel(1L);
            hotel.setOwner(admin);
            hotel.setStars(4);
            hotel.setAreaName("Athens");
            hotel.setName("hotel");


            Room room1 = new Room();
            room1.setId(1L);
            room1.setName("room1");
            room1.setHotel(hotel);
            hotel.getRooms().add(room1);

            Room room2 = new Room();
            room2.setId(2L);
            room2.setName("room2");
            room2.setHotel(hotel);

            hotel.getRooms().add(room2);


            Hotel hotel1 = new Hotel(2L);
            hotel1.setOwner(admin);
            hotel1.setStars(5);
            hotel1.setAreaName("Athens");
            hotel1.setName("hotel1");

            Room room3 = new Room();
            room1.setId(3L);
            room1.setName("room3");
            room1.setHotel(hotel1);
            hotel1.getRooms().add(room2);

            Room room4 = new Room();
            room2.setId(4L);
            room2.setName("room4");
            room2.setHotel(hotel1);
            hotel1.getRooms().add(room4);

            rooms.add(room1);
            rooms.add(room2);

            rooms1.add(room3);
            rooms1.add(room4);

            hotels.add(hotel);
            hotels.add(hotel1);

            hotelDTOS= new ArrayList<>();
            roomDTOS = new ArrayList<>();
            roomDTOS1 = new ArrayList<>();

            HotelDTO hotelDTO = new HotelDTO(1L);
            hotelDTO.setOwner(admin.getId());
            hotelDTO.setStars(4);
            hotelDTO.setAreaName("Athens");
            hotelDTO.setName("hotel");

            RoomDTO roomDTO1 = new RoomDTO();
            roomDTO1.setId(1L);
            roomDTO1.setName("roomDTO1");
            roomDTO1.setHotel(1L);
            roomDTOS.add(roomDTO1);
            hotelDTO.getRooms().add(roomDTO1);

            RoomDTO roomDTO2 = new RoomDTO();
            roomDTO2.setId(2L);
            roomDTO2.setName("roomDTO2");
            roomDTO2.setHotel(1L);
            roomDTOS.add(roomDTO2);
            hotelDTO.getRooms().add(roomDTO2);



            hotelDTOS.add(hotelDTO);


            HotelDTO hotelDTO1 = new HotelDTO(2L);
            hotelDTO1.setOwner(admin.getId());
            hotelDTO1.setStars(5);
            hotelDTO1.setAreaName("Athens");
            hotelDTO1.setName("hotel1");

            RoomDTO roomDTO3 = new RoomDTO();
            roomDTO3.setId(3L);
            roomDTO3.setName("roomDTO3");
            roomDTO3.setHotel(3L);
            roomDTOS1.add(roomDTO1);
            hotelDTO1.getRooms().add(roomDTO3);

            RoomDTO roomDTO4 = new RoomDTO();
            roomDTO4.setId(4L);
            roomDTO4.setName("roomDTO2");
            roomDTO4.setHotel(1L);
            roomDTOS1.add(roomDTO4);
            hotelDTO.getRooms().add(roomDTO2);

            hotelService = new HotelService(hotelRepository,adminRepository,
                    new HotelDTOToHotel(new RoomDTOToRoom(hotelRepository,
                            new OrderDTOToOrder(roomRepository, clientRepository)),adminRepository),
                    new HotelToHotelDTO(new RoomToRoomDTO(
                            new OrderToOrderDTO(roomRepository, clientRepository),hotelRepository)),roomService,
                    new AdminService(adminRepository));
        }

        @Test
        void saveHotelDTO() {
            // todo
        }

        @Test
        void saveHotels() {
            // todo
        }

        @Test
        void getHotels() throws Exception {
            // given
            List<Hotel> hotels1 = new ArrayList<>();
            Admin admin = new Admin(1L);
            Hotel hotel = new Hotel(1L);
            hotel.setName("hotel");
            hotel.setOwner(admin);
            hotel.setAreaName("Athens");
            hotel.setStars(5);

            Room room = new Room();
            room.setId(1L);
            room.setName("room");
            room.setHotel(hotel);

            hotel.getRooms().add(room);
            hotels1.add(hotel);

            Hotel hotel1 = new Hotel(2L);
            hotel1.setOwner(admin);
            hotel1.setStars(5);
            hotel1.setAreaName("Athens");
            hotel1.setName("hotel1");

            Room room3 = new Room();
            room3.setId(3L);
            room3.setName("room3");
            room3.setHotel(hotel1);
            hotel1.getRooms().add(room3);

            Room room4 = new Room();
            room4.setId(4L);
            room4.setName("room4");
            room4.setHotel(hotel1);
            hotel1.getRooms().add(room4);
            hotels1.add(hotel1);

            List<HotelDTO> hotelDTOS= new ArrayList<>();
            roomDTOS = new ArrayList<>();
            roomDTOS1 = new ArrayList<>();

            HotelDTO hotelDTO = new HotelDTO(1L);
            hotelDTO.setOwner(admin.getId());
            hotelDTO.setStars(4);
            hotelDTO.setAreaName("Athens");
            hotelDTO.setName("hotel");

            RoomDTO roomDTO1 = new RoomDTO();
            roomDTO1.setId(1L);
            roomDTO1.setName("roomDTO1");
            roomDTO1.setHotel(1L);
            roomDTOS.add(roomDTO1);
            hotelDTO.getRooms().add(roomDTO1);

            RoomDTO roomDTO2 = new RoomDTO();
            roomDTO2.setId(2L);
            roomDTO2.setName("roomDTO2");
            roomDTO2.setHotel(1L);
            roomDTOS.add(roomDTO2);
            hotelDTO.getRooms().add(roomDTO2);



            hotelDTOS.add(hotelDTO);


            HotelDTO hotelDTO1 = new HotelDTO(2L);
            hotelDTO1.setOwner(admin.getId());
            hotelDTO1.setStars(5);
            hotelDTO1.setAreaName("Athens");
            hotelDTO1.setName("hotel1");

            RoomDTO roomDTO3 = new RoomDTO();
            roomDTO3.setId(3L);
            roomDTO3.setName("roomDTO3");
            roomDTO3.setHotel(3L);
            roomDTOS1.add(roomDTO1);
            hotelDTO1.getRooms().add(roomDTO3);

            RoomDTO roomDTO4 = new RoomDTO();
            roomDTO4.setId(4L);
            roomDTO4.setName("roomDTO2");
            roomDTO4.setHotel(1L);
            roomDTOS1.add(roomDTO4);
            hotelDTO1.getRooms().add(roomDTO2);

            hotelDTOS.add(hotelDTO1);



            // when
            when(hotelRepository.findAll()).thenReturn(hotels1);


            //then
            List<HotelDTO> hotelDTOList = hotelService.getHotels();

            assertEquals(2, hotelDTOList.size());
            assertTrue(EqualsBuilder.reflectionEquals(hotelDTOS, hotelDTOList));
        }

        @Test
        void getHotelById() throws Exception {
            // given
            Admin admin = new Admin(1L);
            Optional<Admin> adminOptional = Optional.of(admin);

            Hotel hotel = new Hotel(1L);
            hotel.setName("hotel");
            hotel.setOwner(admin);
            hotel.setAreaName("Athens");
            hotel.setStars(5);

            Room room = new Room();
            room.setId(1L);
            room.setName("room");
            room.setHotel(hotel);

            hotel.getRooms().add(room);

            Optional<Hotel> hotelOptional = Optional.of(hotel);

            // when
            when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


            //then
            HotelDTO hotelDTOActual = hotelService.getHotelById(anyLong());

            assertEquals(hotel.getName(), hotelDTOActual.getName());
        }

        @Test
        void getHotelByName() throws Exception {
            // given
            Admin admin = new Admin(1L);

            Hotel hotel = new Hotel(1L);
            hotel.setOwner(admin);
            hotel.setStars(4);
            hotel.setAreaName("Athens");
            hotel.setName("hotel");


            Room room1 = new Room();
            room1.setId(1L);
            room1.setName("room1");
            room1.setHotel(hotel);
            hotel.getRooms().add(room1);

            Room room2 = new Room();
            room2.setId(2L);
            room2.setName("room2");
            room2.setHotel(hotel);

            hotel.getRooms().add(room2);


            Optional<Hotel> hotelOptional = Optional.of(hotel);

            // when
            when(hotelRepository.findByName(anyString())).thenReturn(hotelOptional);


            //then
            HotelDTO hotelDTOActual = hotelService.getHotelByName(anyString());

            assertEquals(hotel.getName(), hotelDTOActual.getName());
        }

        @Test
        void enableHotel() {
            // given
            boolean expected = true;
            Hotel hotel = new Hotel();
            hotel.setId(1L);
            hotel.setName("hotel");
            hotel.setDisabled(true);

            Optional<Hotel> hotelOptional = Optional.of(hotel);


            // when
            when(hotelRepository.existsById(anyLong())).thenReturn(true);
            when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


            //then
            boolean actual = hotelService.enableHotel(anyLong());

            assertEquals(expected, actual);
        }

        @Test
        void disableHotel() {
            // given
            boolean expected = true;
            Hotel hotel = new Hotel();
            hotel.setId(1L);
            hotel.setName("hotel");
            hotel.setDisabled(false);

            Optional<Hotel> hotelOptional = Optional.of(hotel);

            // when
            when(hotelRepository.existsById(anyLong())).thenReturn(true);
            when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


            //then
            boolean actual = hotelService.disableHotel(anyLong());

            assertEquals(expected, actual);
        }

        @Test
        void updateHotel() {
            // todo
        }

    }
