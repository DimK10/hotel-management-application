package com.sphy.hotelmanagementapplication.controller;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/api/room/create")
    public Room addRoom(@RequestBody Room room){
        return service.saveRoom(room);
    }

    @PostMapping("/api/rooms/create")
    public List<Room> addRooms(@RequestBody List<Room> rooms){
        return (List<Room>) service.saveRooms(rooms);
    }

    @GetMapping("/api/rooms")
    public List<Room> findAllRooms(){
        return service.getRooms();
    }

    @GetMapping("/api/roomId/{id}")
    public Room findRoomById(@PathVariable Long id){
        return service.getRoomById(id);
    }

    @GetMapping("/api/roomName/{name}")
    public Room findRoomByName (@PathVariable String name){
        return service.getRoomByName(name);
    }

    @PutMapping("/api/room/update")
    public Room updateRoom(@RequestBody Room room) {
        return service.saveRoom(room);
    }


    @DeleteMapping("/api/room/delete/{id}")
    public String deleteRoom(@PathVariable Long id){
        return service.deleteRoom(id);
    }


}
