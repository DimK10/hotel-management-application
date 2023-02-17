package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * created by gp
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Disabled("These tests get interfered with the data that already exists in db")
public class HotelServiceIT {

    @Autowired
    HotelService hotelService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    IntermediateRoomAmenityRepository intermediateRoomAmenityRepository;

    @Autowired
    IntermediateHotelAmenityRepository intermediateHotelAmenityRepository;

    @Autowired
    AmenityHotelRepository amenityHotelRepository;

    @Autowired
    AmenityRoomRepository amenityRoomRepository;

    @BeforeEach
    void setUp () {


        User admin = new User(2L, true, "geo_46", "thanos", "poul", "geopapadopoulos@gmail.com", "soula_sagapo", true, User.Role.ADMIN, new HashSet<>(), new HashSet<>());
        admin.setHashedPassword("5c54105254c53d8e67ce12cddc0dc00a85ebd4156c68b2c8ee955d6d9066396ed4780bea29e02ef5");

        User client = new User(null, true, "pelatis", "mitsos", "allatsas", "pelatis@gmail.com", "asfgbafbf", true, User.Role.CLIENT, new HashSet<>(), new HashSet<>());
        client.setHashedPassword("hfdgjakdhgakj");
        client.setHashedPassword("avbasbvabcba");
        userRepository.save(client);

        Hotel ksenia = new Hotel(null, "ksenia", 5, "athens", "description", false);
        hotelRepository.save(ksenia);
        ksenia.setOwner(admin);

        Room ena = new Room(null, "ena", 5, 54, false);
        ena.setCapacity(3);
        roomRepository.save(ena);


        HotelAmenity hotelAmenity1 = new HotelAmenity("Parking", true);
        amenityHotelRepository.save(hotelAmenity1);

        HotelAmenity hotelAmenity2 = new HotelAmenity("Restaurant", true);
        amenityHotelRepository.save(hotelAmenity2);

        HotelAmenity hotelAmenity3 = new HotelAmenity("Room Service", true);
        amenityHotelRepository.save(hotelAmenity3);

        HotelAmenity hotelAmenity4 = new HotelAmenity("Gym", true);
        amenityHotelRepository.save(hotelAmenity4);

        HotelAmenity hotelAmenity5 = new HotelAmenity("Spa", true);
        amenityHotelRepository.save(hotelAmenity5);

        HotelAmenity hotelAmenity6 = new HotelAmenity("Pool", true);
        amenityHotelRepository.save(hotelAmenity6);

        HotelAmenity hotelAmenity7 = new HotelAmenity("Charging Station", true);
        amenityHotelRepository.save(hotelAmenity7);

        HotelAmenity hotelAmenity8 = new HotelAmenity("Pets Allowed", true);
        amenityHotelRepository.save(hotelAmenity8);

        HotelAmenity hotelAmenity9 = new HotelAmenity("Airport Transport", true);
        amenityHotelRepository.save(hotelAmenity9);

        HotelAmenity hotelAmenity10 = new HotelAmenity("Wheelchair Ramps", true);
        amenityHotelRepository.save(hotelAmenity10);




        RoomAmenity roomAmenity1 = new RoomAmenity("Free WiFi", true);
        amenityRoomRepository.save(roomAmenity1);

        RoomAmenity roomAmenity2 = new RoomAmenity("View To See Mountain", true);
        amenityRoomRepository.save(roomAmenity2);

        RoomAmenity roomAmenity3 = new RoomAmenity("AirCondition", true);
        amenityRoomRepository.save(roomAmenity3);

        RoomAmenity roomAmenity4 = new RoomAmenity("Fireplace", true);
        amenityRoomRepository.save(roomAmenity4);

        RoomAmenity roomAmenity5 = new RoomAmenity("Kitchen", true);
        amenityRoomRepository.save(roomAmenity5);

        RoomAmenity roomAmenity6 = new RoomAmenity("Refrigerator", true);
        amenityRoomRepository.save(roomAmenity6);

        RoomAmenity roomAmenity7 = new RoomAmenity("MiniBar", true);
        amenityRoomRepository.save(roomAmenity7);

        RoomAmenity roomAmenity8 = new RoomAmenity("Washing machine", true);
        amenityRoomRepository.save(roomAmenity8);

        RoomAmenity roomAmenity9 = new RoomAmenity("Coffee - Tea machine", true);
        amenityRoomRepository.save(roomAmenity9);

        RoomAmenity roomAmenity10 = new RoomAmenity("TV", true);
        amenityRoomRepository.save(roomAmenity10);



        IntermediateRoomAmenity roomAme1 = new IntermediateRoomAmenity(ena, roomAmenity1);
        intermediateRoomAmenityRepository.save(roomAme1);

        IntermediateRoomAmenity roomAme2 = new IntermediateRoomAmenity(ena, roomAmenity2);
        intermediateRoomAmenityRepository.save(roomAme2);



        userRepository.save(admin);

        for (int i = 0; i < 5; i++) {
            Hotel hotel = new Hotel(null, ("ksenia" + i), 5, "athens", "description", false);
            hotel.setOwner(admin);
            admin.getHotels().add(hotel);
            hotelRepository.save(hotel);

            for (int j = 0; j < 4; j++) {

                List<Room> rooms = new ArrayList<>();

                Room room = new Room(null, String.valueOf(j), 3, 30 + i, false);

                room.setCapacity(j);

                roomRepository.save(room);

                IntermediateRoomAmenity intermediateRoomAmenity1 = new IntermediateRoomAmenity(room, roomAmenity1);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity1);
                IntermediateRoomAmenity intermediateRoomAmenity2 = new IntermediateRoomAmenity(room, roomAmenity2);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity2);

                roomRepository.save(room);

                hotel.getRooms().add(room);
                hotelRepository.save(hotel);

                room.setHotel(hotel);

                roomRepository.save(room);
                rooms.add(room);

                for (int k = 0; k < 3; k++) {

                    Order order = new Order(null, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, room, room.getName(), room.getHotel().getName(), room.getPrice());

                    orderRepository.save(order);
                    room.getOrders().add(order);
                    roomRepository.save(room);

                    client.getOrders().add(order);
                    userRepository.save(client);
                }

            }
            hotelRepository.save(hotel);

            IntermediateHotelAmenity hamen1 = new IntermediateHotelAmenity(hotel, hotelAmenity1);
            intermediateHotelAmenityRepository.save(hamen1);

            IntermediateHotelAmenity hamen2 = new IntermediateHotelAmenity(hotel, hotelAmenity2);
            intermediateHotelAmenityRepository.save(hamen2);

            IntermediateHotelAmenity hamen3 = new IntermediateHotelAmenity(hotel, hotelAmenity3);
            intermediateHotelAmenityRepository.save(hamen3);


            userRepository.save(admin);

        }

        for (int i = 6; i < 10; i++) {
            Hotel hotel = new Hotel(null, ("anna" + i), 4, "thesaloniki", "kati", false);
            hotel.setOwner(admin);
            admin.getHotels().add(hotel);
            hotelRepository.save(hotel);

            for (int j = 0; j < 4; j++) {

                List<Room> rooms = new ArrayList<>();

                Room room = new Room(null, String.valueOf(j), 3, 50 + j, false);

                room.setCapacity(j);

                roomRepository.save(room);

                IntermediateRoomAmenity intermediateRoomAmenity1 = new IntermediateRoomAmenity(room, roomAmenity3);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity1);
                IntermediateRoomAmenity intermediateRoomAmenity2 = new IntermediateRoomAmenity(room, roomAmenity4);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity2);
                IntermediateRoomAmenity intermediateRoomAmenity3 = new IntermediateRoomAmenity(room, roomAmenity5);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity3);

                roomRepository.save(room);

                hotel.getRooms().add(room);
                hotelRepository.save(hotel);

                room.setHotel(hotel);

                roomRepository.save(room);
                rooms.add(room);

                for (int k = 0; k < 3; k++) {

                    Order order = new Order(null, LocalDate.of(2008, 12, 3), LocalDate.of(2008, 12, 7), false, client, room, room.getName(), room.getHotel().getName(), room.getPrice());

                    orderRepository.save(order);
                    room.getOrders().add(order);
                    roomRepository.save(room);

                    client.getOrders().add(order);
                    userRepository.save(client);
                }

            }
            hotelRepository.save(hotel);

            IntermediateHotelAmenity hamen1 = new IntermediateHotelAmenity(hotel, hotelAmenity1);
            intermediateHotelAmenityRepository.save(hamen1);

            IntermediateHotelAmenity hamen2 = new IntermediateHotelAmenity(hotel, hotelAmenity2);
            intermediateHotelAmenityRepository.save(hamen2);

            IntermediateHotelAmenity hamen3 = new IntermediateHotelAmenity(hotel, hotelAmenity3);
            intermediateHotelAmenityRepository.save(hamen3);


            userRepository.save(admin);

        }

        for (int i = 11; i < 5; i++) {
            Hotel hotel = new Hotel(null, ("geo" + i), 3, "chios", "kati allo", false);
            hotel.setOwner(admin);
            admin.getHotels().add(hotel);
            hotelRepository.save(hotel);

            for (int j = 0; j < 6; j++) {

                List<Room> rooms = new ArrayList<>();

                Room room = new Room(null, String.valueOf(j), 5, 100 + j, false);

                room.setCapacity(j);

                roomRepository.save(room);

                IntermediateRoomAmenity intermediateRoomAmenity1 = new IntermediateRoomAmenity(room, roomAmenity6);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity1);
                IntermediateRoomAmenity intermediateRoomAmenity2 = new IntermediateRoomAmenity(room, roomAmenity7);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity2);
                IntermediateRoomAmenity intermediateRoomAmenity3 = new IntermediateRoomAmenity(room, roomAmenity8);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity3);
                IntermediateRoomAmenity intermediateRoomAmenity4 = new IntermediateRoomAmenity(room, roomAmenity9);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity4);

                roomRepository.save(room);

                hotel.getRooms().add(room);
                hotelRepository.save(hotel);

                room.setHotel(hotel);

                roomRepository.save(room);
                rooms.add(room);

                for (int k = 0; k < 3; k++) {

                    Order order = new Order(null, LocalDate.of(2009, 12, 3), LocalDate.of(2009, 12, 7), false, client, room, room.getName(), room.getHotel().getName(), room.getPrice());

                    orderRepository.save(order);
                    room.getOrders().add(order);
                    roomRepository.save(room);

                    client.getOrders().add(order);
                    userRepository.save(client);
                }

            }
            hotelRepository.save(hotel);

            IntermediateHotelAmenity hamen1 = new IntermediateHotelAmenity(hotel, hotelAmenity1);
            intermediateHotelAmenityRepository.save(hamen1);

            IntermediateHotelAmenity hamen2 = new IntermediateHotelAmenity(hotel, hotelAmenity2);
            intermediateHotelAmenityRepository.save(hamen2);

            IntermediateHotelAmenity hamen3 = new IntermediateHotelAmenity(hotel, hotelAmenity3);
            intermediateHotelAmenityRepository.save(hamen3);

            IntermediateHotelAmenity hamen4 = new IntermediateHotelAmenity(hotel, hotelAmenity4);
            intermediateHotelAmenityRepository.save(hamen4);

            IntermediateHotelAmenity hamen5 = new IntermediateHotelAmenity(hotel, hotelAmenity5);
            intermediateHotelAmenityRepository.save(hamen5);


            userRepository.save(admin);

        }

        for (int i = 16; i < 20; i++) {
            Hotel hotel = new Hotel(null, ("mpalafa" + i), 1, "patra", "kati diaforetiko", false);
            hotel.setOwner(admin);
            admin.getHotels().add(hotel);
            hotelRepository.save(hotel);

            for (int j = 0; j < 6; j++) {

                List<Room> rooms = new ArrayList<>();

                Room room = new Room(null, String.valueOf(j), 1, 10 + j, false);

                room.setCapacity(2);

                roomRepository.save(room);

                IntermediateRoomAmenity intermediateRoomAmenity1 = new IntermediateRoomAmenity(room, roomAmenity10);
                intermediateRoomAmenityRepository.save(intermediateRoomAmenity1);

                roomRepository.save(room);

                hotel.getRooms().add(room);
                hotelRepository.save(hotel);

                room.setHotel(hotel);

                roomRepository.save(room);
                rooms.add(room);

                for (int k = 0; k < 3; k++) {

                    Order order = new Order(null, LocalDate.of(2010, 12, 3), LocalDate.of(2010, 12, 7), false, client, room, room.getName(), room.getHotel().getName(), room.getPrice());

                    orderRepository.save(order);
                    room.getOrders().add(order);
                    roomRepository.save(room);

                    client.getOrders().add(order);
                    userRepository.save(client);
                }

            }
            hotelRepository.save(hotel);

            IntermediateHotelAmenity hamen1 = new IntermediateHotelAmenity(hotel, hotelAmenity9);
            intermediateHotelAmenityRepository.save(hamen1);

            userRepository.save(admin);

        }
    }

    @Test

    void advancedSearchLocation() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();

        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, null, null, "athe", 0, 1);

        System.out.println(hotelDTOS);
        //then
        assertEquals(6, hotelDTOS.size());
        assertEquals(1, hotelDTOS.stream().findFirst().get().getId());
    }


    @Transactional
    @Test
    void getHotels() throws Exception {

        //given
        int expected = 1;

        User owner = new User(1L);

        Hotel hotel1 = new Hotel(1L);
        hotel1.setOwner(owner);
        Set<Hotel> hotelSet = new HashSet<>();
        hotelSet.add(hotel1);
        owner.setHotels(hotelSet);
        Hotel hotel2 = new Hotel(2L);
        Hotel hotel3 = new Hotel(3L);
        Hotel hotel4 = new Hotel(4L);
        Hotel hotel5 = new Hotel(5L);

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);
        hotelRepository.save(hotel4);
        hotelRepository.save(hotel5);

        //when

        List<HotelDTO> hotelDTOS = hotelService.getHotels(0, expected, "id", 1L);

        //then
        assertEquals(expected, hotelDTOS.size());
        assertEquals(hotelDTOS.get(0).getId(), 1);
    }


    @Test
    void advancedSearchOrderInIn() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 4), LocalDate.of(2007, 12, 6), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(8, hotelDTOS.size());
        assertEquals(8, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchOrderLeftIn() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 2), LocalDate.of(2007, 12, 6), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(8, hotelDTOS.size());
        assertEquals(8, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchOrderInRight() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 4), LocalDate.of(2007, 12, 9), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(8, hotelDTOS.size());
        assertEquals(8, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchOrderExactTheSame() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(8, hotelDTOS.size());
        assertEquals(8, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchStars() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 2), LocalDate.of(2007, 12, 9), null, null, null, 1, null, 0, 100);

        //then
        assertEquals(4, hotelDTOS.size());
        assertEquals(12, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchAdultRange() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, 2, null, null, 0, 100);

        //then
        assertEquals(13, hotelDTOS.size());
        assertEquals(3, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchPrice() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, null, null, 0L, 11L, 2, null, null, 0, 100);

        //then
        assertEquals(4, hotelDTOS.size());
        assertEquals(12, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchHAmenities() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();

        HotelAmenity hotelAmenity1 = new HotelAmenity("Parking", true);
        hotelAmenity1.setId(1L);
        hotelAmenities.add(hotelAmenity1);
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, null, null, null, 0, 100);

        //then
        assertEquals(10, hotelDTOS.size());
        assertEquals(1, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchRAmenities() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();

        List<RoomAmenity> roomAmenities = new ArrayList<>();
        RoomAmenity roomAmenity = new RoomAmenity("Free WiFi", true);
        roomAmenity.setId(1L);
        roomAmenities.add(roomAmenity);
        //when

        List<HotelDTO> hotelDTOS = hotelService.advancedSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, null, null, null, 0, 100);

        //then
        assertEquals(6, hotelDTOS.size());
        assertEquals(1, hotelDTOS.stream().findFirst().get().getId());

    }


    @Test
    void getHotelsNoPagination() throws Exception {

        //given
        int expected = 1;

        User owner = new User(1L);

        Hotel hotel1 = new Hotel(1L);
        hotel1.setOwner(owner);
        Set<Hotel> hotelSet = new HashSet<>();
        hotelSet.add(hotel1);
        owner.setHotels(hotelSet);
        Hotel hotel2 = new Hotel(2L);
        Hotel hotel3 = new Hotel(3L);
        Hotel hotel4 = new Hotel(4L);
        Hotel hotel5 = new Hotel(5L);

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);
        hotelRepository.save(hotel4);
        hotelRepository.save(hotel5);

        //when

        Set<HotelDTO> hotelDTOS = hotelService.getHotels(1L);

        //then
        assertEquals(expected, hotelDTOS.size());

        assertEquals(hotelDTOS.iterator().next().getId(), 1);


    }
}
