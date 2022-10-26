package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public Room saveRoom(Room room){
       return repository.save(room);
    }

    public List<Room> saveRooms(List<Room> rooms){
        return (List<Room>) repository.saveAll(rooms);
    }

    public List<Room> getRooms(){
        return (List<Room>) repository.findAll();
    }

    public Room getRoomById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Room getRoomByName(String name){
        return repository.findByName(name);
    }

    public String deleteRoom(Long id){
        repository.deleteById(id);
        return "Room with id" + id + "has be successfully removed";
    }

    public Room updateRoom(Room room){
        Room existingRoom = repository.findById(room.getId()).orElse(null);
        existingRoom.setName(room.getName());
        existingRoom.setHotel(room.getHotel());
        existingRoom.setLuxurity(room.getLuxurity());
        existingRoom.setPrice(room.getPrice());
        return repository.save(existingRoom);
    }




}
