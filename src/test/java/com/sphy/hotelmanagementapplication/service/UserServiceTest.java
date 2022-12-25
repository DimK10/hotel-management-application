package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.*;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.repositories.UserRepository;
import com.sphy.hotelmanagementapplication.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    JwtUtil jwtUtil;
    @Mock
    private UserRepository userRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        openMocks(this);

        userService = new UserService(
                userRepository,
                new UserToUserDTO(
                        new HotelToHotelDTO(new RoomToRoomDTO(new OrderToOrderDTO(roomRepository, userRepository), hotelRepository)),
                        new OrderToOrderDTO(roomRepository, userRepository)
                ),
                new UserDTOToUser(new HotelToHotelDTO(new RoomToRoomDTO(new OrderToOrderDTO(roomRepository, userRepository), hotelRepository)),
                        new OrderToOrderDTO(roomRepository, userRepository)),
                passwordEncoder,
                jwtUtil
        );
    }

    @Test
    void getUserFromToken() {
        // Given
        String token = "token";
        User expected = new User(1L);
        expected.setRole(User.Role.ADMIN);
        expected.setUsername("geo_46");

        // When
        when(jwtUtil.extractUsername(token)).thenReturn("geo_46");
        when(userRepository.findByUsername(anyString())).thenReturn(expected);

        // Then
        User actual = userService.getUserFromToken(token);

        assertEquals(expected, actual);

        verify(jwtUtil, times(1)).extractUsername(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
    }
}