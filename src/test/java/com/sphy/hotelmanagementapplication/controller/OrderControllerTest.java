package com.sphy.hotelmanagementapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.service.OrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/***
 * created by gp on 08/11/22
 */
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController controller;

    List<Order> orders = new ArrayList<>();

    List<OrderDTO> ordersDTO = new ArrayList<>();

    Order order = new Order();

    Order order1 = new Order();

    OrderDTO orderDTO = new OrderDTO();

    OrderDTO orderDTO1 = new OrderDTO();

    MockMvc mockMvc;

    Long id2 = 2L;

    @BeforeEach
    void setUp() throws Exception {

        Room room = new Room(1L);
        User client = new User(1L);
        order.setId(1L);
        order.setClient(client);
        order.setRoom(room);
        order.setCheckInDate(LocalDate.ofEpochDay(2022 - 2 - 3));
        order.setCheckOutDate(LocalDate.ofEpochDay(2022 - 2 - 8));
        order.setCanceled(false);

        orders.add(order);

        Room room1 = new Room(2L);
        User client1 = new User(2L);
        order1.setId(id2);
        order1.setRoom(room1);
        order1.setCanceled(false);
        order1.setCheckOutDate(LocalDate.ofEpochDay(2022 - 3 - 23));
        order1.setCheckInDate(LocalDate.ofEpochDay(2022 - 3 - 18));
        order1.setClient(client1);

        orders.add(order1);

        orderDTO.setId(1L);
        orderDTO.setClient(1L);
        orderDTO.setRoom(1L);
        orderDTO.setCanceled(false);

        ordersDTO.add(orderDTO);

        orderDTO1.setId(2L);
        orderDTO1.setClient(client1.getId());
        orderDTO1.setRoom(room1.getId());
        orderDTO1.setCanceled(false);

        ordersDTO.add(orderDTO1);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

    }



    @Test
    void addOrder() throws Exception {
        // Given


        // When
        when(orderService.saveOrderDTO(any())).thenReturn(orderDTO);

        // Return
        mockMvc.perform(
                        post("/api/order/create")
                                .content(asJsonString(orderDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.client")
                        .value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.room")
                        .value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.canceled")
                        .value("false"));


        verify(orderService, times(1)).saveOrderDTO(any());

    }


    @Test
    void findAllOrders() throws Exception {
        // Given

        // When
        when(orderService.getOrders()).thenReturn(ordersDTO);

        // Return
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath(
                        "$[0].id",
                        Matchers.equalTo(1)
                ));

        // verify that roomService was executed inside findAllRooms() only once
        verify(orderService, times(1)).getOrders();
    }

    @Test
    void findOrderById() throws Exception {
        // Given
        Long id = 1L;

        // When
        when(orderService.getOrderById(anyLong())).thenReturn(orderDTO);

        // Return
        mockMvc.perform(get("/api/orderId/{id}",id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(orderService, times(1)).getOrderById(anyLong());
    }

    @Test
    void updateOrder() throws Exception {
        // Given


        // When
        when(orderService.updateOrder(any())).thenReturn(orderDTO);

        // Return
        mockMvc.perform(
                        put("/api/order/update")
                                .content(asJsonString(orderDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value("1"));


        verify(orderService, times(1)).updateOrder(any());
    }

    @Test
    void enableOrder() throws Exception {
        // Given
        String expected = "Order with id 1 was successfully activated";

        // When
        when(orderService.enableOrder(any())).thenReturn(true);

        // Return
        MvcResult result = mockMvc.perform(
                        post("/api/order/enable/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(orderService, times(1)).enableOrder(any());

    }

    @Test
    void disableOrder() throws Exception {
        // Given
        String expected = "Order with id 1 was successfully deactivated";

        // When
        when(orderService.disableOrder(any())).thenReturn(true);

        // Return
        MvcResult result = mockMvc.perform(
                        post("/api/order/disable/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(orderService, times(1)).disableOrder(any());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}