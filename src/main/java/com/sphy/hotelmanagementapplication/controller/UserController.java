package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.UserDTO;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * created by gp
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /***
     * sing in for a user
     * @param userDTO the user to be saved
     * @return the saved user
     */
    @PostMapping("/api/signIn")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){

        return userService.saveUser(userDTO);

    }

    /***
     * find all users
     * @return all users
     */
    @GetMapping("/api/users")
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }



}
