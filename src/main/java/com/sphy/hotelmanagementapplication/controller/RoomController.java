package com.sphy.hotelmanagementapplication.controller;
import com.sphy.hotelmanagementapplication.converter.BaseEntityConverter;
import com.sphy.hotelmanagementapplication.converter.BaseEntitySetConverter;
import com.sphy.hotelmanagementapplication.domain.BaseEntity;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomController {

	private final ModelMapper modelMapper;

    private final RoomService service;

	private final BaseEntityConverter baseEntityConverter;

	private final BaseEntitySetConverter baseEntitySetConverter;

	public RoomController(ModelMapper modelMapper, RoomService service, BaseEntityConverter baseEntityConverter, BaseEntitySetConverter baseEntitySetConverter) {
		this.modelMapper = modelMapper;
		this.service = service;
		this.baseEntityConverter = baseEntityConverter;
		this.baseEntitySetConverter = baseEntitySetConverter;
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
		modelMapper.addConverter(baseEntitySetConverter);

		TypeMap<Room,RoomDTO> propertyMapper = modelMapper.createTypeMap(Room.class, RoomDTO.class);
		propertyMapper.addMappings(
				mapper -> mapper.map(src -> src.getHotel().getId(), RoomDTO::setHotel)
		);

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
