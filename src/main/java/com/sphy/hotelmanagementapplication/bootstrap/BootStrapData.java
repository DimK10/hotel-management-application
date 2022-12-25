package com.sphy.hotelmanagementapplication.bootstrap;

import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.domain.User.Role;
import com.sphy.hotelmanagementapplication.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;


@Component
public class BootStrapData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final OrderRepository orderRepository;
    private final RoomRepository roomRepository;


    public BootStrapData(UserRepository userRepository, HotelRepository hotelRepository, OrderRepository orderRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User client = new User(null, true,"pelatis", "mitsos","allatsas", "pelatis@gmail.com", "asfgbafbf", Role.CLIENT, new HashSet<>(), new HashSet<>());
        client.setHashedPassword("hfdgjakdhgakj");
        client.setHashedPassword("avbasbvabcba");
        userRepository.save(client);


        User admin = new User(2L, true, "geo_46", "thanos", "poul", "geopapadopoulos@gmail.com", "soula_sagapo", Role.ADMIN, new HashSet<>(), new HashSet<>());
        admin.setHashedPassword("5c54105254c53d8e67ce12cddc0dc00a85ebd4156c68b2c8ee955d6d9066396ed4780bea29e02ef5");

        userRepository.save(admin);

        Room ena = new Room( null, "ena",5,54, false);
        roomRepository.save(ena);


        Room dio = new Room(null, "dio",4,30, false);
        roomRepository.save(dio);


		for (int i = 0; i < 100; i++) {
			Hotel hotel = new Hotel(null, ("ksenia" + i), 5,"athens", false);

			hotel.setOwner(admin);
			admin.getHotels().add(hotel);

			hotelRepository.save(hotel);
			userRepository.save(admin);
		}

//        Hotel ksenia = new Hotel(null, "ksenia", 5,"athens", false);
//        Hotel ksenia2 = new Hotel(null, "ksenia2", 5,"athens", false);
//        Hotel ksenia3 = new Hotel(null, "ksenia3", 5,"athens", false);
//        hotelRepository.save(ksenia);
//        hotelRepository.save(ksenia2);
//        hotelRepository.save(ksenia3);
//        ksenia.setOwner(admin);
//        hotelRepository.save(ksenia);
//        ena.setHotel(ksenia);
//        dio.setHotel(ksenia);
//        roomRepository.save(ena);
//        roomRepository.save(dio);
//        admin.getHotels().add(ksenia);
//        userRepository.save(admin);
//
//
//        ksenia.getRooms().add(ena);
//        hotelRepository.save(ksenia);
//        ksenia.getRooms().add(dio);
//        hotelRepository.save(ksenia);



        Order order = new Order(null, LocalDate.of(2007, 12, 03), LocalDate.of(2007, 12, 07), false, client,ena);
        orderRepository.save(order);


        ena.getOrders().add(order);
        roomRepository.save(ena);

        Order order1 = new Order(null, LocalDate.of( 2007, 12,03), LocalDate.of(2007, 12, 07), false, client,dio);
        orderRepository.save(order1);
        dio.getOrders().add(order1);
        roomRepository.save(dio);
    //        orderRepository.save(order);

//        clientRepository.save(client);
//        adminRepository.save(admin);
//        roomRepository.save(ena);
//        roomRepository.save(dio);
//        hotelRepository.save(ksenia);
//        orderRepository.save(order);
    }
}
