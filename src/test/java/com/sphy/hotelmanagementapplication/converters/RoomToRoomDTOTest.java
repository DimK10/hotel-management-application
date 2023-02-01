package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class RoomToRoomDTOTest {

    @Mock
    RoomToRoomDTO roomToRoomDTO;

    @Mock
    HotelRepository hotelRepository;

    @Mock
    OrderToOrderDTO orderToOrderDTO;

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
        room.setCapacity(5);

        roomDTO.setId(1L);
        roomDTO.setHotel(hotel.getId());
        roomDTO.setDisabled(false);
        roomDTO.setLuxurity(3);
        roomDTO.setName("kala");
        roomDTO.getOrders().add(orderDTO);
        roomDTO.setPrice(500L);
        roomDTO.setCapacity(5);

        roomToRoomDTO = new RoomToRoomDTO(orderToOrderDTO, hotelRepository);
    }

    @Test
    void converterTest() {

        //given

        //when
        when(hotelRepository.findById(anyLong())).thenReturn(Optional.of(hotel));
        when(orderToOrderDTO.converter(any())).thenReturn(orderDTO);



        //then
        assertEquals(roomDTO.getId() , roomToRoomDTO.converter(room).getId());
        assertEquals(roomDTO.getName() , roomToRoomDTO.converter(room).getName());
        assertEquals(roomDTO.getPrice() , roomToRoomDTO.converter(room).getPrice());
        assertEquals(roomDTO.getHotel() , roomToRoomDTO.converter(room).getHotel());
        assertEquals(roomDTO.getOrders() , roomToRoomDTO.converter(room).getOrders());
        assertEquals(roomDTO.getCapacity() , roomToRoomDTO.converter(room).getCapacity());





    }
}

