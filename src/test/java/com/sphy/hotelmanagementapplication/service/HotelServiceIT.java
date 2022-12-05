package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * created by gp
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class HotelServiceIT {

    @Autowired
    HotelService hotelService;

    @Autowired
    HotelRepository hotelRepository;


    @Transactional
    @Test
    void getHotels() throws Exception {

        //given
        int expected = 3;

        Hotel hotel1 = new Hotel(1L);
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

        List<HotelDTO> hotelDTOS = hotelService.getHotels(0,expected,"id");

        //then
        assertEquals(expected, hotelDTOS.size());
        assertEquals(hotelDTOS.get(0).getId(), 1);
        assertEquals(hotelDTOS.get(2).getId(), 3);


    }

}
