package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import org.springframework.stereotype.Component;

/***
 * created by gp
 */
@Component
public class UserToUserDTO {


    /***
     * converts a user object to userDTO
     * @param user the user object we want to convert
     * @return the converted user
     */
    public UserDTO converter(User user){

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());

        userDTO.setHashedPassword(user.getHashedPassword());

        userDTO.setFirstname(user.getFirstname());

        userDTO.setLastname(user.getLastname());

        userDTO.setRole(String.valueOf(user.getRole()));

        userDTO.setEmail(user.getEmail());

        userDTO.setEmailVerify(user.isEmailVerify());

        userDTO.setId(user.getId());

        userDTO.setPassword(user.getPassword());

        return userDTO;

    }
}
