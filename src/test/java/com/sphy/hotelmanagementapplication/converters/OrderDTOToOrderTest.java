package com.sphy.hotelmanagementapplication.converters;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	UserRepository userRepository;

	Order order = new Order();

	OrderDTO orderDTO = new OrderDTO();

	User client = new User(1L);

	Room room = new Room(1L);

	@BeforeEach
	void setUp() throws Exception {

		order.setCanceled(false);
		order.setId(1L);
		order.setCheckInDate(LocalDate.of(1993, 4, 12));
		order.setCheckOutDate(LocalDate.of(1993, 4, 15));
		order.setClient(client);
		order.setRoom(room);

		orderDTO.setCanceled(false);
		orderDTO.setId(1L);
		orderDTO.setCheckInDate(LocalDate.of(1993, 4, 12));
		orderDTO.setCheckOutDate(LocalDate.of(1993, 4, 15));
		orderDTO.setClient(client.getId());
		orderDTO.setRoom(1L);

		orderDTOToOrder = new OrderDTOToOrder(roomRepository, userRepository);
	}

	@Test
	void converterTest() {

		//given

		//when

		//then
		assertEquals(order, orderDTOToOrder.converter(orderDTO));

	}

}

