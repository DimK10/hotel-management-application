package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.RoomAmenityDTOToRoomAmenity;
import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class RoomDTOToRoomTest {

    @Mock
    RoomDTOToRoom roomDTOToRoom;

    @Mock
    HotelRepository hotelRepository;

    @Mock
    OrderDTOToOrder orderDTOToOrder;

    @Mock
    RoomAmenityDTOToRoomAmenity roomAmenityDTOToRoomAmenity;



    Room room = new Room();

    RoomDTO roomDTO = new RoomDTO();

    Order order = new Order(1L);

    OrderDTO orderDTO = new OrderDTO(1L);

    Hotel hotel = new Hotel(1L);

    @BeforeEach
    void setUp() throws Exception{

        room.setId(1L);
        room.setHotel(hotel);
        room.setDisabled(false);
        room.setLuxurity(3);
        room.setName("kala");
        room.getOrders().add(order);
        room.setPrice(500L);

        roomDTO.setId(1L);
        roomDTO.setHotel(hotel.getId());
        roomDTO.setDisabled(false);
        roomDTO.setLuxurity(3);
        roomDTO.setName("kala");
        roomDTO.getOrders().add(orderDTO);
        roomDTO.setPrice(500L);

        roomDTOToRoom = new RoomDTOToRoom(hotelRepository, orderDTOToOrder, roomAmenityDTOToRoomAmenity);
    }

    @Test
    void converterTest() {

        //given

        //when

        //then
        assertEquals(room , roomDTOToRoom.converter(roomDTO));

    }
}
