package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.UserDTOToUser;
import com.sphy.hotelmanagementapplication.converter.UserToUserDTO;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.UserDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import com.sphy.hotelmanagementapplication.security.JwtUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * created by gp
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserToUserDTO userToUserDTO;

    private final UserDTOToUser userDTOToUser;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, UserToUserDTO userToUserDTO, UserDTOToUser userDTOToUser, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userToUserDTO = userToUserDTO;
        this.userDTOToUser = userDTOToUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


	/***
	 * get an admin by his id
	 * @param id the id of the admin to be found
	 * @return the admin with the current id
	 */
	public User getUserById(Long id){
		return userRepository.findById(id).orElse(null);
	}

    /**
     * Get user object, from jwt token, using username subject
     * @param token The jwt token
     * @return The user object associated with the jwt token
     */
    public User getUserFromToken(String token) {

        if	(token.contains("Bearer")) {
            token = token.substring(7);
        }

        String username = jwtUtil.extractUsername(token);

        return userRepository.findByUsername(username);
    }

    /***
     * take username, password and the role and create a userDetails
     * @param username the username of the user
     * @return userDetails from user
     * @throws UsernameNotFoundException if the user does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);

       if (user == null){
           throw new UsernameNotFoundException("The user does not exists");
       }

       Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

       authorities.add( new SimpleGrantedAuthority(user.getRole().toString()));

       return new org.springframework.security.core.userdetails.User(
               user.getUsername(),user.getHashedPassword(), authorities);
    }

    /***
     * save a user
     * @param userDTO the user to be saves
     * @return the saved user
     * @throws ApiRequestException if the necessary properties are not included in the request
     */
    public UserDTO saveUser(UserDTO userDTO) throws ApiRequestException{

        if (userDTO.getUsername().isBlank() || userDTO.getPassword().isBlank()
                || userDTO.getEmail().isBlank() || userDTO.getRole().isBlank()){
            throw new ApiRequestException("Informations are incomplete");
        }else {
            userDTO.setHashedPassword(passwordEncoder.encode(userDTO.getPassword()));
          return   userToUserDTO.converter(userRepository.save(userDTOToUser.converter(userDTO)));
        }
    }

	public UserDTO getUserByUsername(String username){
		return userToUserDTO.converter(userRepository.findByUsername(username));
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

    public User findByUsername(String username){

        return userRepository.findByUsername(username);
    }

}
