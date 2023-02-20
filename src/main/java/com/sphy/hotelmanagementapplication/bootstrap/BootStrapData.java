package com.sphy.hotelmanagementapplication.bootstrap;

import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.domain.User.Role;
import com.sphy.hotelmanagementapplication.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Profile("dev")
@Component
public class BootStrapData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final OrderRepository orderRepository;
    private final RoomRepository roomRepository;
    private final AmenityHotelRepository amenityHotelRepository;
    private final AmenityRoomRepository amenityRoomRepository;
    private final IntermediateHotelAmenityRepository intermediateHotelAmenityRepository;
    private final IntermediateRoomAmenityRepository intermediateRoomAmenityRepository;


    public BootStrapData(UserRepository userRepository, HotelRepository hotelRepository, OrderRepository orderRepository, RoomRepository roomRepository, AmenityHotelRepository amenityHotelRepository, AmenityRoomRepository amenityRoomRepository, IntermediateHotelAmenityRepository intermediateHotelAmenityRepository, IntermediateRoomAmenityRepository intermediateRoomAmenityRepository) {
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
        this.amenityHotelRepository = amenityHotelRepository;
        this.amenityRoomRepository = amenityRoomRepository;
        this.intermediateHotelAmenityRepository = intermediateHotelAmenityRepository;
        this.intermediateRoomAmenityRepository = intermediateRoomAmenityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        HotelAmenity hotelAmenity1 = new HotelAmenity("Parking", true);
        amenityHotelRepository.save(hotelAmenity1);

        HotelAmenity hotelAmenity2 = new HotelAmenity("Restaurant", true);
        amenityHotelRepository.save(hotelAmenity2);

        HotelAmenity hotelAmenity3 = new HotelAmenity("Room Service", true);
        amenityHotelRepository.save(hotelAmenity3);

        HotelAmenity hotelAmenity4 = new HotelAmenity("Gym", true);
        amenityHotelRepository.save(hotelAmenity4);

        HotelAmenity hotelAmenity5 = new HotelAmenity("Spa", true);
        amenityHotelRepository.save(hotelAmenity5);

        HotelAmenity hotelAmenity6 = new HotelAmenity("Pool", true);
        amenityHotelRepository.save(hotelAmenity6);

        HotelAmenity hotelAmenity7 = new HotelAmenity("Charging Station", true);
        amenityHotelRepository.save(hotelAmenity7);

        HotelAmenity hotelAmenity8 = new HotelAmenity("Pets Allowed", true);
        amenityHotelRepository.save(hotelAmenity8);

        HotelAmenity hotelAmenity9 = new HotelAmenity("Airport Transport", true);
        amenityHotelRepository.save(hotelAmenity9);

        HotelAmenity hotelAmenity10 = new HotelAmenity("Wheelchair Ramps", true);
        amenityHotelRepository.save(hotelAmenity10);

        HotelAmenity hotelAmenity11 = new HotelAmenity("Rooms Accessible Elevator", true);
        amenityHotelRepository.save(hotelAmenity11);


        RoomAmenity roomAmenity1 = new RoomAmenity("Free WiFi", true);
        amenityRoomRepository.save(roomAmenity1);

        RoomAmenity roomAmenity2 = new RoomAmenity("View To See Mountain", true);
        amenityRoomRepository.save(roomAmenity2);

        RoomAmenity roomAmenity3 = new RoomAmenity("AirCondition", true);
        amenityRoomRepository.save(roomAmenity3);

        RoomAmenity roomAmenity4 = new RoomAmenity("Fireplace", true);
        amenityRoomRepository.save(roomAmenity4);

        RoomAmenity roomAmenity5 = new RoomAmenity("Kitchen", true);
        amenityRoomRepository.save(roomAmenity5);

        RoomAmenity roomAmenity6 = new RoomAmenity("Refrigerator", true);
        amenityRoomRepository.save(roomAmenity6);

        RoomAmenity roomAmenity7 = new RoomAmenity("MiniBar", true);
        amenityRoomRepository.save(roomAmenity7);

        RoomAmenity roomAmenity8 = new RoomAmenity("Washing machine", true);
        amenityRoomRepository.save(roomAmenity8);

        RoomAmenity roomAmenity9 = new RoomAmenity("Coffee - Tea machine", true);
        amenityRoomRepository.save(roomAmenity9);

        RoomAmenity roomAmenity10 = new RoomAmenity("TV", true);
        amenityRoomRepository.save(roomAmenity10);

        RoomAmenity roomAmenity11 = new RoomAmenity("Toilet Grab Rails", true);
        amenityRoomRepository.save(roomAmenity11);

        RoomAmenity roomAmenity12 = new RoomAmenity("Bathtub Grab Rails", true);
        amenityRoomRepository.save(roomAmenity12);

        RoomAmenity roomAmenity13 = new RoomAmenity("Shower Chair", true);
        amenityRoomRepository.save(roomAmenity13);

        RoomAmenity roomAmenity14 = new RoomAmenity("Raised Chair", true);
        amenityRoomRepository.save(roomAmenity14);

        RoomAmenity roomAmenity15 = new RoomAmenity("Emergency Phone", true);
        amenityRoomRepository.save(roomAmenity15);

        RoomAmenity roomAmenity16 = new RoomAmenity("Safe Deposit Box", true);
        amenityRoomRepository.save(roomAmenity16);

        RoomAmenity roomAmenity17 = new RoomAmenity("Bathrobe", true);
        amenityRoomRepository.save(roomAmenity17);

        RoomAmenity roomAmenity18 = new RoomAmenity("Hair Dryer", true);
        amenityRoomRepository.save(roomAmenity18);

        RoomAmenity roomAmenity19 = new RoomAmenity("Baby Highchair", true);
        amenityRoomRepository.save(roomAmenity19);


        User client = new User(null, true, "pelatis", "mitsos", "allatsas", "pelatis@gmail.com", "asfgbafbf", true, Role.CLIENT, new HashSet<>(), new HashSet<>());
        client.setHashedPassword("hfdgjakdhgakj");
        client.setHashedPassword("avbasbvabcba");
        userRepository.save(client);

        User client2 = new User(1L, true, "dim_80", "dim", "Iwannou", "dimioannou@gmail.com", "soula_magapas",true ,Role.CLIENT, new HashSet<>(), new HashSet<>());
        client2.setHashedPassword("1229758f94f95fe3593ffe549ab6c5dd797660bfc823ab8dc4fea9dd656c0609b196b0e77491ebf0");
        userRepository.save(client2);


        User admin = new User(2L, true, "geo_46", "thanos", "poul", "geopapadopoulos@gmail.com", "soula_sagapo", true, Role.ADMIN, new HashSet<>(), new HashSet<>());
        admin.setHashedPassword("5c54105254c53d8e67ce12cddc0dc00a85ebd4156c68b2c8ee955d6d9066396ed4780bea29e02ef5");

        userRepository.save(admin);

        Room ena = new Room(null, "ena", 5, 54, false);
        ena.setCapacity(3);
        roomRepository.save(ena);


        Room dio = new Room(null, "dio", 4, 30, false);
        dio.setCapacity(3);
        roomRepository.save(dio);

        Hotel ksenia = new Hotel(null, "ksenia", 5, "athens", "description", false);
        hotelRepository.save(ksenia);
        ksenia.setOwner(admin);
        hotelRepository.save(ksenia);
        ena.setHotel(ksenia);
        dio.setHotel(ksenia);
        roomRepository.save(ena);
        roomRepository.save(dio);
        admin.getHotels().add(ksenia);
        userRepository.save(admin);


        ksenia.getRooms().add(ena);
        hotelRepository.save(ksenia);
        ksenia.getRooms().add(dio);
        hotelRepository.save(ksenia);
        ksenia.getRooms().add(ena);
        ksenia.getRooms().add(dio);

        hotelRepository.save(ksenia);

        IntermediateHotelAmenity hamen1 = new IntermediateHotelAmenity(ksenia, hotelAmenity1);
        intermediateHotelAmenityRepository.save(hamen1);


        ksenia.getIntermediateHotelAmenities().add(hamen1);
        hotelAmenity1.getIntermediateHotelAmenities().add(hamen1);


        IntermediateHotelAmenity hamen2 = new IntermediateHotelAmenity(ksenia, hotelAmenity2);

        intermediateHotelAmenityRepository.save(hamen2);
        hotelAmenity2.getIntermediateHotelAmenities().add(hamen2);

        ksenia.getIntermediateHotelAmenities().add(hamen2);
        amenityHotelRepository.save(hotelAmenity1);
        amenityHotelRepository.save(hotelAmenity2);

        Order order = new Order(null, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, ena, ena.getName(), ena.getHotel().getName(), ena.getPrice());
        orderRepository.save(order);

        ena.getOrders().add(order);

        IntermediateRoomAmenity roomAme1 = new IntermediateRoomAmenity(ena,roomAmenity1);
        intermediateRoomAmenityRepository.save(roomAme1);

        IntermediateRoomAmenity roomAme2 = new IntermediateRoomAmenity(ena, roomAmenity2);
        intermediateRoomAmenityRepository.save(roomAme2);

        roomRepository.save(ena);

        ena.getIntermediateRoomAmenities().add(roomAme1);
        ena.getIntermediateRoomAmenities().add(roomAme2);

        Order order1 = new Order(null, LocalDate.of(2007, 12, 3), LocalDate.of(2007, 12, 7), false, client, dio, dio.getName(), dio.getHotel().getName(), dio.getPrice());
        orderRepository.save(order1);
        dio.getOrders().add(order1);

//        Set<RoomAmenity> amenityRset1 = new HashSet<>();
//
//        RoomAmenity amenityR4 = new RoomAmenity();
//        amenityR4.setAmenitiesR(RoomAmenity.AmenitiesRoom.MINIBAR);
//
//        RoomAmenity amenityR5 = new RoomAmenity();
//        amenityR5.setAmenitiesR(RoomAmenity.AmenitiesRoom.BABYHIGHCHAIR);
//
//
//        amenityRset1.add(amenityR4);
//        amenityRset1.add(amenityR5);
//        amenityRoomRepository.saveAll(amenityRset1);
//
//        dio.getRoomAmenity().addAll(amenityRset1);
        roomRepository.save(dio);

        //  hotel with rooms for testing in front end
        Hotel hotelWithRooms = new Hotel(40L, "hotel_with_rooms", 40, "Larissa", "aaa", false);
        hotelWithRooms.setAddress("Larissa");
        hotelWithRooms.setAreaName("Patision 33");
        hotelWithRooms.setDisabled(false);
        hotelWithRooms.setOwner(admin);

        hotelWithRooms = hotelRepository.save(hotelWithRooms);

        for (int i = 0; i < 104; i++) {
            Room room = new Room();

            room.setHotel(hotelWithRooms);
            room.setName("room_" + i);
            room.setDisabled(false);
            room.setLuxurity(i);
            room.setPrice(i);
            room.setCapacity(i);

            roomRepository.save(room);
        }
    }
}