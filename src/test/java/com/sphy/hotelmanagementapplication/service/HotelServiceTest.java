package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.*;
import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.domain.User.Role;
import com.sphy.hotelmanagementapplication.dto.BasicSearchDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repository.*;
import com.sphy.hotelmanagementapplication.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/***
 * created by gp
 */

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {


    @Mock
    RoomRepository roomRepository;

    @Mock
    HotelRepository hotelRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    RoomService roomService;

    @Mock
    HotelToHotelDTO hotelToHotelDTO;

    @Mock
    IntermediateHotelAmenityRepository intermediateHotelAmenityRepository;

    @Mock
    AmenityHotelRepository amenityHotelRepository;

    @Mock
    EntityManager entityManager;

    @Mock
    JwtUtil jwtUtil;

    HotelService hotelService;

    List<Hotel> hotels = new ArrayList<>();

    List<HotelDTO> hotelDTOS = new ArrayList<>();

    List<Room> rooms = new ArrayList<>();

    List<Room> rooms1 = new ArrayList<>();

    List<RoomDTO> roomDTOS = new ArrayList<>();

    List<RoomDTO> roomDTOS1 = new ArrayList<>();

    final Long id = 1L;

    final String name = "hotel";

    @BeforeEach
    void setUp() throws Exception {
        hotels = new ArrayList<>();

        rooms = new ArrayList<>();
        rooms1 = new ArrayList<>();

        User admin = new User(1L);
        admin.setRole(Role.ADMIN);

        Hotel hotel = new Hotel(1L);
        hotel.setOwner(admin);
        hotel.setStars(4);
        hotel.setAreaName("Athens");
        hotel.setName("hotel");


        Room room1 = new Room();
        room1.setId(1L);
        room1.setName("room1");
        room1.setHotel(hotel);
        hotel.getRooms().add(room1);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setName("room2");
        room2.setHotel(hotel);

        hotel.getRooms().add(room2);


        Hotel hotel1 = new Hotel(2L);
        hotel1.setOwner(admin);
        hotel1.setStars(5);
        hotel1.setAreaName("Athens");
        hotel1.setName("hotel1");

        Room room3 = new Room();
        room1.setId(3L);
        room1.setName("room3");
        room1.setHotel(hotel1);
        hotel1.getRooms().add(room2);

        Room room4 = new Room();
        room2.setId(4L);
        room2.setName("room4");
        room2.setHotel(hotel1);
        hotel1.getRooms().add(room4);

        rooms.add(room1);
        rooms.add(room2);

        rooms1.add(room3);
        rooms1.add(room4);

        hotels.add(hotel);
        hotels.add(hotel1);

        HotelDTO hotelDTO = new HotelDTO(1L);
        hotelDTO.setOwner(admin.getId());
        hotelDTO.setStars(4);
        hotelDTO.setAreaName("Athens");
        hotelDTO.setName("hotel");

        RoomDTO roomDTO1 = new RoomDTO();
        roomDTO1.setId(1L);
        roomDTO1.setName("roomDTO1");
        roomDTO1.setHotel(1L);
        roomDTOS.add(roomDTO1);
        hotelDTO.getRooms().add(roomDTO1);

        RoomDTO roomDTO2 = new RoomDTO();
        roomDTO2.setId(2L);
        roomDTO2.setName("roomDTO2");
        roomDTO2.setHotel(1L);
        roomDTOS.add(roomDTO2);
        hotelDTO.getRooms().add(roomDTO2);


        hotelDTOS.add(hotelDTO);


        HotelDTO hotelDTO1 = new HotelDTO(2L);
        hotelDTO1.setOwner(admin.getId());
        hotelDTO1.setStars(5);
        hotelDTO1.setAreaName("Athens");
        hotelDTO1.setName("hotel1");

        RoomDTO roomDTO3 = new RoomDTO();
        roomDTO3.setId(3L);
        roomDTO3.setName("roomDTO3");
        roomDTO3.setHotel(3L);
        roomDTOS1.add(roomDTO1);
        hotelDTO1.getRooms().add(roomDTO3);

        RoomDTO roomDTO4 = new RoomDTO();
        roomDTO4.setId(4L);
        roomDTO4.setName("roomDTO2");
        roomDTO4.setHotel(1L);
        roomDTOS1.add(roomDTO4);
        hotelDTO.getRooms().add(roomDTO2);

        hotelService = new HotelService(
                entityManager, hotelRepository,
                new HotelDTOToHotel(new RoomDTOToRoom(hotelRepository,
                        new OrderDTOToOrder(roomRepository, userRepository)),
                        userRepository),
                hotelToHotelDTO, roomService, userRepository,
                new UserService(userRepository, new UserToUserDTO(new HotelToHotelDTO(new RoomToRoomDTO(new OrderToOrderDTO(roomRepository, userRepository), hotelRepository)), new OrderToOrderDTO(roomRepository, userRepository)),
                        new UserDTOToUser(new HotelToHotelDTO(new RoomToRoomDTO(new OrderToOrderDTO(roomRepository, userRepository), hotelRepository)), new OrderToOrderDTO(roomRepository, userRepository)),
                        new PasswordEncoder() {
                            @Override
                            public String encode(CharSequence rawPassword) {
                                return null;
                            }

                            @Override
                            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                                return false;
                            }
                        },
                        jwtUtil

                ), intermediateHotelAmenityRepository, amenityHotelRepository);
    }

    @Test
    void saveHotelDTO() {
        // todo
    }

    @Test
    void saveHotels() {
        // todo
    }

    @Test
    void getHotelById() throws Exception {
        // given
        User admin = new User(1L);
        admin.setRole(Role.ADMIN);
        Optional<User> adminOptional = Optional.of(admin);

        Hotel hotel = new Hotel(1L);
        hotel.setName("hotel");
        hotel.setOwner(admin);
        hotel.setAreaName("Athens");
        hotel.setStars(5);

        Room room = new Room();
        room.setId(1L);
        room.setName("room");
        room.setHotel(hotel);

        hotel.getRooms().add(room);

        HotelDTO hotelDTO = new HotelDTO();

        Optional<Hotel> hotelOptional = Optional.of(hotel);

        // when
        when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);
        when(hotelToHotelDTO.converter(any())).thenReturn(hotelDTO);


        //then
        HotelDTO hotelDTOActual = hotelService.getHotelById(anyLong());

        assertEquals(hotelDTO.getName(), hotelDTOActual.getName());
    }

    @Test
    void getHotelByName() throws Exception {
        // given
        User admin = new User(1L);
        admin.setRole(Role.ADMIN);

        Hotel hotel = new Hotel(1L);
        hotel.setOwner(admin);
        hotel.setStars(4);
        hotel.setAreaName("Athens");
        hotel.setName("hotel");

        HotelDTO hotelDTO = new HotelDTO();


        Room room1 = new Room();
        room1.setId(1L);
        room1.setName("room1");
        room1.setHotel(hotel);
        hotel.getRooms().add(room1);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setName("room2");
        room2.setHotel(hotel);

        hotel.getRooms().add(room2);


        Optional<Hotel> hotelOptional = Optional.of(hotel);

        // when
        when(hotelRepository.findByName(anyString())).thenReturn(hotelOptional);
        when(hotelToHotelDTO.converter(any())).thenReturn(hotelDTO);


        //then
        HotelDTO hotelDTOActual = hotelService.getHotelByName(anyString());

        assertEquals(hotelDTO.getName(), hotelDTOActual.getName());
    }

    @Test
    void enableHotel() {
        // given
        boolean expected = true;
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("hotel");
        hotel.setDisabled(true);

        Optional<Hotel> hotelOptional = Optional.of(hotel);


        // when
        when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


        //then
        boolean actual = hotelService.enableHotel(anyLong());

        assertEquals(expected, actual);
    }

    @Test
    void disableHotel() {
        // given
        boolean expected = true;
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("hotel");
        hotel.setDisabled(false);

        Optional<Hotel> hotelOptional = Optional.of(hotel);

        // when
        when(hotelRepository.findById(anyLong())).thenReturn(hotelOptional);


        //then
        boolean actual = hotelService.disableHotel(anyLong());

        assertEquals(expected, actual);
    }

    @Test
    void updateHotel() {
        // todo
    }

    @Test
    void countHotels() {

        //given

        //when
        when(hotelRepository.countAll(anyLong())).thenReturn(1);


        //then
        assertEquals(1, hotelService.countHotels(1L));

    }

    @Test
    void getHotelBasicSearch() {

        //given
        Set<Hotel> hotels1 = new HashSet<>();

        Hotel hotel = new Hotel(1L);

        hotel.setName("ksenia");
        hotel.setAreaName("athens");

        Room room = new Room(2L);

        Order order = new Order(3L, LocalDate.of(2017, 12, 12),
                LocalDate.of(2017, 12, 20),
                false, new User(5L), room, "ena", "ksenia", 50L);

        room.getOrders().add(order);

        hotel.getRooms().add(room);

        HotelAmenity hotelAmenity1 = new HotelAmenity("Parking",true);

        Set<HotelAmenity> hotelAmenities = new HashSet<>();

        hotelAmenities.add(hotelAmenity1);

        IntermediateHotelAmenity hamen1 = new IntermediateHotelAmenity(hotel,hotelAmenity1);

        hotel.getIntermediateHotelAmenities().add(hamen1);

        hotels1.add(hotel);


        Set<HotelDTO> hotel1DTOS = new HashSet<>();

        HotelDTO hotelDTO = new HotelDTO(1L);

        hotelDTO.setName("ksenia");
        hotelDTO.setAreaName("athens");

        hotel1DTOS.add(hotelDTO);

        RoomDTO roomDTO = new RoomDTO(2L);

        OrderDTO orderDTO = new OrderDTO(3L);

        BasicSearchDTO basicSearchDTO1 = new BasicSearchDTO();

        basicSearchDTO1.setCheckInDate(LocalDate.of(2019, 12, 13));
        basicSearchDTO1.setCheckOutDate(LocalDate.of(2019, 12, 20));
        basicSearchDTO1.setNameOrLocation("athens");

        BasicSearchDTO basicSearchDTO2 = new BasicSearchDTO();

        basicSearchDTO2.setCheckInDate(LocalDate.of(2019, 12, 13));
        basicSearchDTO2.setCheckOutDate(LocalDate.of(2019, 12, 20));
        basicSearchDTO2.setNameOrLocation("ksenia");

        //when

        when(hotelRepository.findByBasicSearch(basicSearchDTO1.getCheckInDate(), basicSearchDTO1.getCheckOutDate(),
                basicSearchDTO1.getNameOrLocation())).thenReturn(hotels1);
        when(hotelToHotelDTO.converter(hotel)).thenReturn(hotelDTO);
        when(hotelRepository.findById(anyLong())).thenReturn(Optional.of(hotel));
        when(hotelRepository.findAmenityByHotelId(id)).thenReturn(hotelAmenities);

        //then

        assertEquals(hotelService.getHotelBasicSearch(basicSearchDTO1), hotel1DTOS);
    }


    /**
     * Created by Akd
     */
    @Test
    void saveHotelAmenity() {
        // given
        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setId(4L);
        hotelAmenity.sethAmenity("POOL");
        hotelAmenity.setEnabled(true);

        // when
        when(amenityHotelRepository.save(any())).thenReturn(hotelAmenity);

        //then
        assertEquals(hotelAmenity, hotelService.saveHotelAmenity(hotelAmenity));
    }

    /**
     * Created by Akd
     */
    @Test
    void enableHotelAmenity() {
        // given
        boolean expected = true;
        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setId(4L);
        hotelAmenity.sethAmenity("POOL");
        hotelAmenity.setEnabled(false);

        Optional<HotelAmenity> hotelAmenityOptional = Optional.of(hotelAmenity);

        // when
        when(amenityHotelRepository.findById(anyLong())).thenReturn(hotelAmenityOptional);

        //then
        boolean result = hotelService.enableHotelAmenity(anyLong());

        assertEquals(expected, result);
    }

    /**
     * Created by Akd
     */
    @Test
     void testDisableHotelAmenity(){
        boolean expected = true;
        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setId(4L);
        hotelAmenity.sethAmenity("POOL");
        hotelAmenity.setEnabled(true);

        Optional<HotelAmenity> hotelAmenityOptional = Optional.of(hotelAmenity);

        when(amenityHotelRepository.findById(anyLong())).thenReturn(hotelAmenityOptional);

        boolean result = hotelService.disableHotelAmenity(anyLong());

        assertEquals(expected,result);
    }


}
