package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.repositories.ClientRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/***
 * crated by gp
 */
@ExtendWith(MockitoExtension.class)
public class OrderDTOToOrderTest {

    @Mock
    OrderDTOToOrder orderDTOToOrder;

    @Mock
    RoomRepository roomRepository;

    @Mock
    ClientRepository clientRepository;

    Order order = new Order();

    OrderDTO orderDTO = new OrderDTO();

    Client client = new Client(1L);

    Room room = new Room(1L);

    @BeforeEach
    void setUp() throws Exception{

        order.setCanceled(false);
        order.setId(1L);
        order.setCheckInDate(LocalDate.of(1993,4,12));
        order.setCheckOutDate(LocalDate.of(1993,4,15));
        order.setClient(client);
        order.setRoom(room);

        orderDTO.setCanceled(false);
        orderDTO.setId(1L);
        orderDTO.setCheckInDate(LocalDate.of(1993,4,12));
        orderDTO.setCheckOutDate(LocalDate.of(1993,4,15));
        orderDTO.setClient(client.getId());
        orderDTO.setRoom(1L);

        orderDTOToOrder = new OrderDTOToOrder(roomRepository, clientRepository);
    }

    @Test
    void converterTest() {

        //given

        //when
        when(roomRepository.findById(anyLong())).thenReturn(Optional.ofNullable(room));

        //then
        assertEquals(order , orderDTOToOrder.converter(orderDTO));

    }

}

