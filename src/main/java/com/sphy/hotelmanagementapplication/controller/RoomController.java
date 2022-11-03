package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService service;

    private final HotelService hotelService;


    public RoomController(RoomService service, HotelService hotelService) {
        this.service = service;
        this.hotelService = hotelService;
	}

    /***
     * creates a new room
     * @param roomDTO is the room we want to save
     * @return the saved room for confirmation
     * @throws Exception if the room's hotel does not exist
     */
    @PostMapping("/api/room/create")
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) throws Exception {
        if (roomDTO.getHotel() == null || hotelService.getHotelById(roomDTO.getHotel()) == null) {
            throw new Exception("There is no Hotel registered with that id, or the id is null!");
        }
        return service.saveRoomDTO(roomDTO);
    }

    /***
     * create new rooms
     * @param roomsDTO is the list of the hotels we want to save
     * @return the list with the saved rooms
     * @throws Exception
     */
    @PostMapping("/api/rooms/create")
    public List<RoomDTO> addRooms(@RequestBody List<RoomDTO> roomsDTO) throws Exception {

		return service.saveRooms(roomsDTO);
    }

    /***
     * finds all rooms
     * @return all rooms
     * @throws Exception
     */
    @GetMapping("/api/rooms")
    public List<RoomDTO> findAllRooms() throws Exception {
        return service.getRooms();
    }

    /***
     * finds a room by his id
     * @param id id of the room we want to save
     * @return the room with the given id
     * @throws Exception
     */
    @GetMapping("/api/roomId/{id}")
    public RoomDTO findRoomById(@PathVariable Long id) throws Exception {
        return service.getRoomById(id);
    }

    /***
     * finds a room by his name
     * @param name name of the room we want to save
     * @return the room with that name
     * @throws Exception
     */
    @GetMapping("/api/roomName/{name}")
    public RoomDTO findRoomByName(@PathVariable String name) throws Exception {
        return service.getRoomByName(name);
    }

    /***
     * updates a room
     * @param roomDTO the room we want to update
     * @return the updated room for confirmation
     */
    @PutMapping("/api/room/update")
    public RoomDTO updateRoom(@RequestBody RoomDTO roomDTO) {
        return service.updateRoom(roomDTO);
    }

    /***
     * enables a room by his id
     * @param id id of the room we want to disable
     * @return a confirmation message or an error message
     */
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


    /***
     * disables a room by his id
     * @param id of the room we want to disable
     * @return a message of confirmation or an error message
     */
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
