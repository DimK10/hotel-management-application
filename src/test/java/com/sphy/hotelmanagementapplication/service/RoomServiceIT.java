package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * created by gp
 */
@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "dev")
public class RoomServiceIT {

        @Autowired
        RoomService roomService;

        @Autowired
        RoomRepository roomRepository;

        @Autowired
        HotelRepository hotelRepository;

        @Autowired
        UserRepository userRepository;

        @Test
        void getRooms() throws Exception {

            //given
            int expected = 5;

            User admin = new User(1L);
            userRepository.save(admin);

            Hotel hotel = new Hotel(10L);
            hotel.setOwner(admin);
            hotelRepository.save(hotel);



            Room room1 = new Room(1L);
            room1.setHotel(hotel);

            Room room2 = new Room(2L);
            room2.setHotel(hotel);

            Room room3 = new Room(3L);
            room3.setHotel(hotel);

            Room room4 = new Room(4L);
            room4.setHotel(hotel);

            Room room5 = new Room(5L);
            room5.setHotel(hotel);

            Room room6 = new Room(6L);

            roomRepository.save(room1);
            roomRepository.save(room2);
            roomRepository.save(room3);
            roomRepository.save(room4);
            roomRepository.save(room5);
            roomRepository.save(room6);

            //when
            List<RoomDTO> roomDTOS = roomService.getRooms(0,10,"id", admin.getId());

            //then
            assertEquals(expected, roomDTOS.size());


        }

    }
