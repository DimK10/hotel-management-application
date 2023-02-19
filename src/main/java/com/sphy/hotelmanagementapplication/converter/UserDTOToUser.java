package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import org.springframework.stereotype.Component;

/***
 * created by gp
 */
@Component
public class UserDTOToUser {

	private final HotelToHotelDTO hotelToHotelDTO;

	private final OrderToOrderDTO orderToOrderDTO;

	public UserDTOToUser(HotelToHotelDTO hotelToHotelDTO, OrderToOrderDTO orderToOrderDTO) {
		this.hotelToHotelDTO = hotelToHotelDTO;
		this.orderToOrderDTO = orderToOrderDTO;
	}

	/***
     * converts a userDTO object to user
     * @param userDTO the userDTO object we want to convert
     * @return the converted user
     */
    public User converter(UserDTO userDTO){

        User user = new User();

        user.setUsername(userDTO.getUsername());

        user.setHashedPassword(userDTO.getHashedPassword());

        user.setFirstname(userDTO.getFirstname());

        user.setLastname(userDTO.getLastname());

        user.setRole(User.Role.valueOf(userDTO.getRole()));

        user.setEmail(userDTO.getEmail());

        user.setEmailVerify(userDTO.isEmailVerify());

        user.setId(userDTO.getId());

        user.setPassword(userDTO.getPassword());

        user.setEnabled(userDTO.getEnabled());

		if (user.getHotels() != null && !user.getHotels().isEmpty()) {
			user.getHotels().forEach(hotel -> userDTO.getHotelDTOS().add(hotelToHotelDTO.converter(hotel)));
		}

		if (user.getOrders() != null && !user.getOrders().isEmpty()) {
			user.getOrders().forEach(order -> userDTO.getOrderDTOS().add(orderToOrderDTO.converter(order)));
		}

        return user;

    }
}
