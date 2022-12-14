package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.dto.ClientDTO;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
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
public class ClientDTOToUserBTest {

    @Mock
    ClientDTOToClient clientDTOToClient;
    @Mock
    OrderDTOToOrder orderDTOToOrder;

    UserB userB = new UserB();

    ClientDTO clientDTO = new ClientDTO();

    Order order = new Order(1L);

    OrderDTO orderDTO = new OrderDTO(1L);

    @BeforeEach
    void setUp() throws Exception{

        userB.setId(1L);
        userB.setUsername("pelatis");
        userB.setFirstname("giorgos");
        userB.setLastname("papadopoulos");
        userB.setHashedPassword("asdfghjk");
        userB.setEmail("papadopoulos@gmail.com");
        userB.setEmailVerify(true);
        userB.getOrders().add(order);

        clientDTO.setId(1L);
        clientDTO.setUsername("pelatis");
        clientDTO.setFirstname("giorgos");
        clientDTO.setLastname("papadopoulos");
        clientDTO.setHashedPassword("asdfghjk");
        clientDTO.setEmail("papadopoulos@gmail.com");
        clientDTO.setEmailVerify(true);
        clientDTO.setTransactionId("1234567");
        clientDTO.getOrders().add(orderDTO);

        clientDTOToClient = new ClientDTOToClient(orderDTOToOrder);
    }

    @Test
    void converterTest() {

        //given

        //when

        //then
        assertEquals(userB, clientDTOToClient.converter(clientDTO));

    }
}