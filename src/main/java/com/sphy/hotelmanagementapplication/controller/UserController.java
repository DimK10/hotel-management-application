package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.security.AuthenticationRequest;
import com.sphy.hotelmanagementapplication.security.AuthenticationResponse;
import com.sphy.hotelmanagementapplication.security.JwtUtil;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * created by gp
 */
// TODO ADD TESTS FOR ALL METHODS
@RestController
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /***
     * sing in for a user
     * @param userDTO the user to be saved
     * @return the saved user
     */
    @PostMapping("/api/signup")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){

        return userService.saveUser(userDTO);

    }

    /***
     * get username and password, authenticates the user and giv him a java web token
     * @param authenticationRequest username and password
     * @return a jwt
     * @throws ApiRequestException if the username and password does not mach or does not exist
     */
    @PostMapping("/api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)  {


            User user = userService.findByUsername(authenticationRequest.getUsername());

            if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getHashedPassword())) {

                final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

                final String jwt = jwtUtil.generateToken(userDetails);

                return ResponseEntity.ok(new AuthenticationResponse(jwt));

            }else {
                throw new ApiExceptionFront("incorrect username or password");
            }
    }

	/**
	 * Gets all user info - password and hashed password are removed
	 * @param token The jwt token taken from request header
	 * @return The user the token belongs to
	 */
	@GetMapping("/api/auth")
	public UserDTO getUser(@RequestHeader(name="Authorization") String token) {
		String username = jwtUtil.extractUsername(token);
		return userService.getUserByUsername(username);
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
