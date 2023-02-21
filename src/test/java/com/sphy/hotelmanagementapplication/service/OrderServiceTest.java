package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.domain.User.Role;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.repository.OrderRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/***
 * created by gp on 08/11/22
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    EntityManager entityManager;


    @Mock
    RoomRepository roomRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
	UserRepository userRepository;

    @Mock
    JavaMailSender emailSender;

    OrderService orderService;

    List<Order> orders = new ArrayList<>();

    List<OrderDTO> ordersDTO = new ArrayList<>();

    Order order = new Order();

    Order order1 = new Order();

    OrderDTO orderDTO = new OrderDTO();

    OrderDTO orderDTO1 = new OrderDTO();

    Long id1 = 1L;
    Long id2 = 2L;

    User admin = new User(id1);

    User client = new User(id2);

    @BeforeEach
    void setUp() throws Exception {

        Room room = new Room(id1);
		admin.setRole(Role.ADMIN);

        order.setId(1L);
        order.setClient(admin);
        order.setRoom(room);
        order.setCheckInDate(LocalDate.ofEpochDay(2022 - 2 - 3));
        order.setCheckOutDate(LocalDate.ofEpochDay(2022 - 2 - 8));
        order.setCanceled(false);

        orders.add(order);

        Room room1 = new Room(id2);

		client.setRole(Role.CLIENT);

        order1.setId(id2);
        order1.setRoom(room1);
        order1.setCanceled(false);
        order1.setCheckOutDate(LocalDate.ofEpochDay(2022 - 3 - 23));
        order1.setCheckInDate(LocalDate.ofEpochDay(2022 - 3 - 18));
        order1.setClient(client);

        orders.add(order1);

        orderDTO.setId(1L);
        orderDTO.setClient(admin.getId());
        orderDTO.setRoom(room.getId());
        orderDTO.setCheckInDate(LocalDate.ofEpochDay(2022 - 2 - 3));
        orderDTO.setCheckOutDate(LocalDate.ofEpochDay(2022 - 2 - 8));
        orderDTO.setCanceled(false);

        ordersDTO.add(orderDTO);

        orderDTO1.setId(2L);
        orderDTO1.setClient(client.getId());
        orderDTO1.setRoom(room1.getId());
        orderDTO1.setCheckInDate(LocalDate.ofEpochDay(2022 - 3 - 18));
        orderDTO1.setCheckOutDate(LocalDate.ofEpochDay(2022 - 3 - 23));
        orderDTO1.setCanceled(false);

        ordersDTO.add(orderDTO1);


        orderService = new OrderService(entityManager, orderRepository, roomRepository, userRepository,
                new OrderDTOToOrder(roomRepository, userRepository),
                new OrderToOrderDTO(roomRepository, userRepository), emailSender);

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
    void getOrdersClient() throws Exception {
        // given



        // when
        when(orderRepository.findAllClient(anyLong())).thenReturn(orders);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(admin));
        when(userRepository.findById(2L)).thenReturn(Optional.ofNullable(client));


        //then
        List<OrderDTO> orderDTOList = orderService.getOrdersClient(id2);

        assertEquals(2, orderDTOList.size());
        assertEquals(ordersDTO, orderDTOList);
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
