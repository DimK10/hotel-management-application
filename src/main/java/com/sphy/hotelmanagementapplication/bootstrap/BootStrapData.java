package com.sphy.hotelmanagementapplication.bootstrap;

import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Component
public class BootStrapData implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final HotelRepository hotelRepository;
    private final OrderRepository orderRepository;
    private final RoomRepository roomRepository;
    private final AdminRepository adminRepository;
    private final AmenityHotelRepository amenityHotelRepository;
    private final AmenityRoomRepository amenityRoomRepository;
    //private final HotelAmenity hotelAmenity;


    public BootStrapData(ClientRepository clientRepository, HotelRepository hotelRepository, OrderRepository orderRepository, RoomRepository roomRepository, AdminRepository adminRepository, AmenityHotelRepository amenityHotelRepository, AmenityRoomRepository amenityRoomRepository) {
        this.clientRepository = clientRepository;
        this.hotelRepository = hotelRepository;
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
        this.adminRepository = adminRepository;
        this.amenityHotelRepository = amenityHotelRepository;
        //this.hotelAmenity = new HotelAmenity();
        this.amenityRoomRepository = amenityRoomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client(null, true,"pelatis", "mitsos","allatsas", "pelatis@gmail.com");
        client.setHashedPassword("hfdgjakdhgakj");
        client.setHashedPassword("avbasbvabcba");
        clientRepository.save(client);

        Admin admin = new Admin(null, true, "ksenodoxos", "thanos", "poul", "ksenodoxos@gmail.com");
        admin.setHashedPassword("skjdfhgakhdfj");
        adminRepository.save(admin);

        Room ena = new Room( null, "ena",5,54, false);
        roomRepository.save(ena);


        Room dio = new Room(null, "dio",4,30, false);
        roomRepository.save(dio);


        Hotel ksenia = new Hotel(null, "ksenia", 5,"athens", false);
        hotelRepository.save(ksenia);
        ksenia.setOwner(admin);
        hotelRepository.save(ksenia);
        ena.setHotel(ksenia);
        dio.setHotel(ksenia);
        roomRepository.save(ena);
        roomRepository.save(dio);
        admin.getHotels().add(ksenia);
        adminRepository.save(admin);

        Set<HotelAmenity> amenitySet = new HashSet<>();

        HotelAmenity amenity1 = new HotelAmenity();
        amenity1.setAmenitiesH(HotelAmenity.AmenitiesHotel.AIRPORTTRANSPORT);

        HotelAmenity amenity2 = new HotelAmenity();
        amenity2.setAmenitiesH(HotelAmenity.AmenitiesHotel.GYM);

        HotelAmenity amenity3 = new HotelAmenity();
        amenity3.setAmenitiesH(HotelAmenity.AmenitiesHotel.SPA);

        amenityHotelRepository.saveAll(amenitySet);

        ksenia.getHotelAmenity().addAll(amenitySet);
        
        ksenia.getRooms().add(ena);
        //ksenia.getHotelAmenity().add(amenity); //?
        hotelRepository.save(ksenia);
        ksenia.getRooms().add(dio);
        hotelRepository.save(ksenia);

        


        Order order = new Order(null, LocalDate.of(2007, 12, 03), LocalDate.of(2007, 12, 07), false, client,ena);
        orderRepository.save(order);


        ena.getOrders().add(order);
        RoomAmenity roomAmenity = new RoomAmenity(RoomAmenity.AmenitiesRoom.BABYHIGHCHAIR);
        amenityRoomRepository.save(roomAmenity);
        RoomAmenity roomAmenity1 = new RoomAmenity(RoomAmenity.AmenitiesRoom.MINIBAR);
        amenityRoomRepository.save(roomAmenity1);



        ena.getRoomAmenity().add(roomAmenity);
        ena.getRoomAmenity().add(roomAmenity1);
        roomRepository.save(ena);

        Order order1 = new Order(null, LocalDate.of( 2007, 12,03), LocalDate.of(2007, 12, 07), false, client,dio);
        orderRepository.save(order1);
        dio.getOrders().add(order1);
        roomRepository.save(dio);

    }
}
