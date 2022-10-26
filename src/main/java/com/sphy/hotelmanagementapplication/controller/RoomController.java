package com.sphy.hotelmanagementapplication.controller;
import com.sphy.hotelmanagementapplication.converter.BaseEntityConverter;
import com.sphy.hotelmanagementapplication.converter.BaseEntitySetConverter;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory.ModelMapperType;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    private final RoomService service;

	private final BaseEntityConverter baseEntityConverter;

	private final BaseEntitySetConverter baseEntitySetConverter;

	private final ModelMapperFactory modelMapperFactory;

	public RoomController(RoomService service, BaseEntityConverter baseEntityConverter, BaseEntitySetConverter baseEntitySetConverter, ModelMapperFactory modelMapperFactory) {
		this.service = service;
		this.baseEntityConverter = baseEntityConverter;
		this.baseEntitySetConverter = baseEntitySetConverter;
		this.modelMapperFactory = modelMapperFactory;
	}

	@PostMapping("/api/room/create")
    public Room addRoom(@RequestBody Room room){
        return service.saveRoom(room);
    }

    @PostMapping("/api/rooms/create")
    public List<Room> addRooms(@RequestBody List<Room> rooms){
        return (List<Room>) service.saveRooms(rooms);
    }

    @GetMapping("/api/rooms")
    public List<RoomDTO> findAllRooms(){
		ModelMapper modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);

        return service.getRooms().stream().map(room -> modelMapper.map(room, RoomDTO.class))
				.collect(Collectors.toList());
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
