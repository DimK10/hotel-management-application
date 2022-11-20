package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.UserDTOToUser;
import com.sphy.hotelmanagementapplication.converter.UserToUserDTO;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * created by gp
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserToUserDTO userToUserDTO;

    private final UserDTOToUser userDTOToUser;

    public UserService(UserRepository userRepository, UserToUserDTO userToUserDTO, UserDTOToUser userDTOToUser) {
        this.userRepository = userRepository;
        this.userToUserDTO = userToUserDTO;
        this.userDTOToUser = userDTOToUser;
    }

    /***
     * save a user
     * @param userDTO the user to be saves
     * @return the saved user
     * @throws ApiRequestException if the necessary properties are not included in the request
     */
    public UserDTO saveUser(UserDTO userDTO) throws ApiRequestException{

        if (userDTO.getUsername().isBlank() || userDTO.getHashedPassword().isBlank()
                || userDTO.getEmail().isBlank() || userDTO.getRole().isBlank()){
            throw new ApiRequestException("Informations are incomplete");
        }else {
          return   userToUserDTO.converter(userRepository.save(userDTOToUser.converter(userDTO)));
        }
    }

    /***
     * get all users
     * @return all users
     */
    public List<UserDTO> getUsers(){

        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        List<UserDTO> usersDTO = new ArrayList<>();

        for (User user : users){
            usersDTO.add(userToUserDTO.converter(user));
        }

        return usersDTO;
    }
}
