package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.OrderRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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

    @Test
    @Disabled("This test returns different number of pages when it is run alongside with the others. " +
            "As standalone, it return the expected pages (21), but with the other ones, it returns 15")
    void advancedSearchLocation() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();

        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, null, null, "athe", 0, 1);

        System.out.println(hotelDTOS);
        //then
        assertEquals(21, hotelDTOS.get().count());
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

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 4), LocalDate.of(2007, 12, 6), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(77, hotelDTOS.get().count());
        assertEquals(21, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchOrderLeftIn() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 2), LocalDate.of(2007, 12, 6), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(77, hotelDTOS.get().count());
        assertEquals(21, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchOrderInRight() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 4), LocalDate.of(2007, 12, 9), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(77, hotelDTOS.get().count());
        assertEquals(21, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchOrderExactTheSame() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), null, null, 2, null, null, 0, 100);

        //then
        assertEquals(77, hotelDTOS.get().count());
        assertEquals(21, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchStars() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, LocalDate.of(2007, 12, 2), LocalDate.of(2007, 12, 9), null, null, null, 1, null, 0, 100);

        //then
        assertEquals(39, hotelDTOS.get().count());
        assertEquals(64, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchAdultRange() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, 2, null, null, 0, 100);

        //then
        assertEquals(97, hotelDTOS.get().count());
        assertEquals(1, hotelDTOS.stream().findFirst().get().getId());

    }

    @Test
    void advancedSearchPrice() throws Exception {

        //given

        List<HotelAmenity> hotelAmenities = new ArrayList<>();
        List<RoomAmenity> roomAmenities = new ArrayList<>();
        //when

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, null, null, 0L, 11L, 2, null, null, 0, 100);

        //then
        assertEquals(39, hotelDTOS.get().count());
        assertEquals(64, hotelDTOS.stream().findFirst().get().getId());

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

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, null, null, null, 0, 100);

        //then
        assertEquals(59, hotelDTOS.get().count());
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

        Page<HotelDTO> hotelDTOS = hotelService.advanceSearchMethod(hotelAmenities, roomAmenities, null, null, null, null, null, null, null, 0, 100);

        //then
        assertEquals(21, hotelDTOS.get().count());
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
