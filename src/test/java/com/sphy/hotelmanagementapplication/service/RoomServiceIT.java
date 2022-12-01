package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
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
@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RoomServiceIT {

        @Autowired
        RoomService roomService;

        @Autowired
        RoomRepository roomRepository;

        @Test
        void getRooms() throws Exception {

            //given
            int expected = 6;

            Room room1 = new Room(1L);
            Room room2 = new Room(2L);
            Room room3 = new Room(3L);
            Room room4 = new Room(4L);
            Room room5 = new Room(5L);
            Room room6 = new Room(6L);

            roomRepository.save(room1);
            roomRepository.save(room2);
            roomRepository.save(room3);
            roomRepository.save(room4);
            roomRepository.save(room5);
            roomRepository.save(room6);

            //when
            List<RoomDTO> roomDTOS = roomService.getRooms(0,expected,"id");

            //then
            assertEquals(expected, roomDTOS.size());
            assertEquals(roomDTOS.get(0).getId(), 1);
            assertEquals(roomDTOS.get(2).getId(), 3);
            assertEquals(roomDTOS.get(5).getId(), 6);


        }

    }
