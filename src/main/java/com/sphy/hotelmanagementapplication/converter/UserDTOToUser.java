package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import org.springframework.stereotype.Component;

/***
 * created by gp
 */
@Component
public class UserDTOToUser {

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

        return user;

    }
}
