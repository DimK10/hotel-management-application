package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.ClientRepository;
import com.sphy.hotelmanagementapplication.repositories.OrderRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/***
 * created by gp on 08/11/22
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @Mock
    RoomRepository roomRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ClientRepository clientRepository;

    OrderService orderService;

    List<Order> orders = new ArrayList<>();

    List<OrderDTO> ordersDTO = new ArrayList<>();

    Order order = new Order();

    Order order1 = new Order();

    OrderDTO orderDTO = new OrderDTO();

    OrderDTO orderDTO1 = new OrderDTO();

    Long id1 = 1L;
    Long id2 = 2L;

    @BeforeEach
    void setUp() throws Exception {

        Room room = new Room(id1);
        Client client = new Client(id1);
        order.setId(1L);
        order.setClient(client);
        order.setRoom(room);
        order.setCheckInDate(LocalDate.ofEpochDay(2022 - 2 - 3));
        order.setCheckOutDate(LocalDate.ofEpochDay(2022 - 2 - 8));
        order.setCanceled(false);

        orders.add(order);

        Room room1 = new Room(id2);
        Client client1 = new Client(id2);
        order1.setId(id2);
        order1.setRoom(room1);
        order1.setCanceled(false);
        order1.setCheckOutDate(LocalDate.ofEpochDay(2022 - 3 - 23));
        order1.setCheckInDate(LocalDate.ofEpochDay(2022 - 3 - 18));
        order1.setClient(client1);

        orders.add(order1);

        orderDTO.setId(1L);
        orderDTO.setClient(client.getId());
        orderDTO.setRoom(room.getId());
        orderDTO.setCheckInDate(LocalDate.ofEpochDay(2022 - 2 - 3));
        orderDTO.setCheckOutDate(LocalDate.ofEpochDay(2022 - 2 - 8));
        orderDTO.setCanceled(false);

        ordersDTO.add(orderDTO);

        orderDTO1.setId(2L);
        orderDTO1.setClient(client1.getId());
        orderDTO1.setRoom(room1.getId());
        orderDTO1.setCheckInDate(LocalDate.ofEpochDay(2022 - 3 - 18));
        orderDTO1.setCheckOutDate(LocalDate.ofEpochDay(2022 - 3 - 23));
        orderDTO1.setCanceled(false);

        ordersDTO.add(orderDTO1);


        orderService = new OrderService(orderRepository, roomRepository, clientRepository,
                new OrderDTOToOrder(roomRepository, clientRepository),
                new OrderToOrderDTO(roomRepository, clientRepository));

    }


    @Test
    void saveOrderDTO() {
        // todo
    }

    @Test
    void saveOrders() {
        // todo
    }

    @Test
    void getOrders() throws Exception {
        // given


        // when
        when(orderRepository.findAll()).thenReturn(orders);


        //then
        List<OrderDTO> orderDTOList = orderService.getOrders();

        assertEquals(2, orderDTOList.size());
        assertTrue(EqualsBuilder.reflectionEquals(ordersDTO, orderDTOList));
    }

    @Test
    void getOrdersById() throws Exception {
        // given


        // when
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));


        //then
        OrderDTO orderDTOActual = orderService.getOrderById(anyLong());

        assertEquals(order.getId(), orderDTOActual.getId());
    }


    @Test
    void enableOrder() {
        // given
        boolean expected = true;
        Order order2 = new Order();
        order2.setId(1L);
        order2.setCanceled(true);

        Optional<Order> orderOptional = Optional.of(order2);

        // when
        when(orderRepository.existsById(anyLong())).thenReturn(true);
        when(orderRepository.findById(anyLong())).thenReturn(orderOptional);

        //then
        boolean actual = orderService.enableOrder(anyLong());

        assertEquals(expected, actual);
    }

    @Test
    void disableOrder() {
        // given
        boolean expected = true;
        Order order2 = new Order();
        order2.setId(1L);
        order2.setCanceled(false);

        Optional<Order> orderOptional = Optional.of(order2);

        // when
        when(orderRepository.existsById(anyLong())).thenReturn(true);
        when(orderRepository.findById(anyLong())).thenReturn(orderOptional);


        //then
        boolean actual = orderService.disableOrder(anyLong());

        assertEquals(expected, actual);
    }

    @Test
    void updateOrder() {
        // todo
    }
}
