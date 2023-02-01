package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.OrderRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@ActiveProfiles(value = "dev")
public class HotelServiceIT {

    @Autowired
    HotelService hotelService;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    OrderRepository orderRepository;


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

        List<HotelDTO> hotelDTOS = hotelService.getHotels(0,expected,"id", 1L);

        //then
        assertEquals(expected, hotelDTOS.size());
        assertEquals(hotelDTOS.get(0).getId(), 1);
    }

    @Test
    void advancedSearchOrder() throws Exception {

        //given
        int expected = 1;

        User owner = new User(1L);
        User client = new User(2L);

        Hotel hotel1 = new Hotel(1L);
        hotel1.setOwner(owner);
        hotel1.setName("alla");
        hotel1.setStars(3);
        hotel1.setAreaName("thessaloniki");
        hotelRepository.save(hotel1);
        Room room1 = new Room(1L, "ana", 3, 10L, false);
        room1.setCapacity(3);
        room1.setHotel(hotel1);
        roomRepository.save(room1);
        hotel1.getRooms().add(room1);
        Order order = new Order(10L, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, room1, room1.getName(), room1.getHotel().getName(), room1.getPrice());
        orderRepository.save(order);
        room1.getOrders().add(order);

        Hotel hotel2 = new Hotel(2L);
        hotel2.setOwner(owner);
        hotel2.setName("kallalala");
        hotel2.setStars(4);
        hotel2.setAreaName("athina");
        hotelRepository.save(hotel2);
        Room room2 = new Room(2L, "dio", 3, 100L, false);
        room2.setCapacity(2);
        room2.setHotel(hotel2);
        roomRepository.save(room2);
        hotel2.getRooms().add(room2);
        Order order1 = new Order(11L, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, room2, room2.getName(), room2.getHotel().getName(), room2.getPrice());
        orderRepository.save(order1);
        room2.getOrders().add(order1);

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Set<HotelDTO> hotelDTOS = hotelService.advanceSearchMethode(hotelAmenities, roomAmenities, LocalDate.of(2017, 12, 4), LocalDate.of(2017, 12, 9), null, null, 2, null, null);

        //then
        assertEquals(0, hotelDTOS.size());
    }

    @Test
    @Transactional
    void advancedSearchLocation() throws Exception {

        //given
        int expected = 1;

        User owner = new User(1L);
        User client = new User(2L);

        Hotel hotel1 = new Hotel(1L);
        hotel1.setOwner(owner);
        hotel1.setName("alla");
        hotel1.setStars(3);
        hotel1.setAreaName("thessaloniki");
        hotelRepository.save(hotel1);
        Room room1 = new Room(1L, "ana", 3, 10L, false);
        room1.setCapacity(3);
        room1.setHotel(hotel1);
        roomRepository.save(room1);
        hotel1.getRooms().add(room1);
        hotelRepository.save(hotel1);
        Order order = new Order(1L, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, room1, room1.getName(), room1.getHotel().getName(), room1.getPrice());
        orderRepository.save(order);
        room1.getOrders().add(order);
        roomRepository.save(room1);

        Hotel hotel2 = new Hotel(2L);
        hotel2.setOwner(owner);
        hotel2.setName("kallalala");
        hotel2.setStars(4);
        hotel2.setAreaName("athina");
        hotelRepository.save(hotel2);
        Room room2 = new Room(2L, "dio", 3, 100L, false);
        room2.setCapacity(2);
        room2.setHotel(hotel2);
        roomRepository.save(room2);
        hotel2.getRooms().add(room2);
        hotelRepository.save(hotel2);
        Order order1 = new Order(2L, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, room2, room2.getName(), room2.getHotel().getName(), room2.getPrice());
        orderRepository.save(order1);
        room2.getOrders().add(order1);
        roomRepository.save(room2);

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when
//            HotelDTO hotelDTOS = hotelService.getHotelById(1L);
//        System.out.println(hotelDTOS);
        Set<HotelDTO> hotelDTOS = hotelService.advanceSearchMethode(hotelAmenities, roomAmenities, null, null, null, null, 2, null, "ath");

        //then
        assertEquals(1, hotelDTOS.size());
    }

}
