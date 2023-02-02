package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import org.springframework.stereotype.Component;

/***
 * created by gp
 */
@Component
public class UserToUserDTO {


	private final HotelToHotelDTO hotelToHotelDTO;

	private final OrderToOrderDTO orderToOrderDTO;

	public UserToUserDTO(HotelToHotelDTO hotelToHotelDTO, OrderToOrderDTO orderToOrderDTO) {
		this.hotelToHotelDTO = hotelToHotelDTO;
		this.orderToOrderDTO = orderToOrderDTO;
	}

	/***
	 * converts a user object to userDTO
	 * @param user the user object we want to convert
	 * @return the converted user
	 */
	public UserDTO converter(User user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setUsername(user.getUsername());

//		userDTO.setHashedPassword(user.getHashedPassword());
		userDTO.setHashedPassword(null);

		userDTO.setFirstname(user.getFirstname());

		userDTO.setLastname(user.getLastname());

		userDTO.setRole(String.valueOf(user.getRole()));

		userDTO.setEmail(user.getEmail());

		userDTO.setEmailVerify(user.isEmailVerify());

		userDTO.setId(user.getId());

		userDTO.setPassword(null);

		userDTO.setEnabled(user.getEnabled());

		if (user.getHotels() != null && !user.getHotels().isEmpty()) {
			user
					.getHotels()
					.forEach(hotel -> userDTO.getHotelDTOS().add(hotelToHotelDTO.converter(hotel)));
		}

		if (user.getOrders() != null && !user.getOrders().isEmpty()) {
			user
					.getOrders()
					.forEach(order -> userDTO.getOrderDTOS().add(orderToOrderDTO.converter(order)));
		}

		return userDTO;

	}
}
