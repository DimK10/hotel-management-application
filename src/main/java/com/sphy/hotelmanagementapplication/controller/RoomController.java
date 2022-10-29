package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory.ModelMapperType;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    private final RoomService service;

	private final HotelService hotelService;

	private final ModelMapperFactory modelMapperFactory;

	public RoomController(RoomService service, HotelService hotelService, ModelMapperFactory modelMapperFactory) {
		this.service = service;
		this.hotelService = hotelService;
		this.modelMapperFactory = modelMapperFactory;
	}

    @PostMapping("/api/rooms/create")
    public List<RoomDTO> addRooms(@RequestBody List<RoomDTO> roomsDTO) {
        return (List<RoomDTO>) service.saveRooms(roomsDTO);
    }


    @GetMapping("/api/rooms")
    public List<RoomDTO> findAllRooms(){
		ModelMapper modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);

        return service
				.getRooms()
				.stream()
				.map(room -> modelMapper.map(room, RoomDTO.class))
				.collect(Collectors.toList());
    }

    @GetMapping("/api/roomId/{id}")
    public RoomDTO findRoomById(@PathVariable Long id) throws Exception {
        return service.getRoomById(id);
    }

    @GetMapping("/api/roomName/{name}")
    public RoomDTO findRoomByName (@PathVariable String name){
        return service.getRoomByName(name);
    }

    @PutMapping("/api/room/update")
    public RoomDTO updateRoom(@RequestBody RoomDTO roomDTO) {
        return service.updateRoom(roomDTO);
    }

    @PostMapping("/api/room/enable/{id}")
    ResponseEntity<String> enableRoom(@PathVariable Long id) {

        if (!service.enableRoom(id)) {
            return ResponseEntity.badRequest()
                    .body("The id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Room with id " + id + " was successfully activated");
        }
    }
    

    @PostMapping("/api/room/disable/{id}")
    ResponseEntity<String> disableRoom(@PathVariable Long id) {

        if (!service.disableRoom(id)) {
            return ResponseEntity.badRequest()
                    .body("The id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Room with id " + id + " was successfully deactivated");
        }
    }


}
