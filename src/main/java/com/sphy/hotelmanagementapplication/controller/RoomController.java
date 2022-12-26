package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.RoomAmenityDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/***
 * created by gp
 */
@RestController
public class RoomController {

    private final RoomService service;



    public RoomController(RoomService service) {
        this.service = service;

    }

    /***
     * creates a new room
     * @param roomDTO is the room we want to save
     * @return the saved room for confirmation
     * @throws ApiRequestException if the room's hotel does not exist or if the hotel doesn't given
     */
    @PostMapping("/api/room/create")
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) throws ApiRequestException {

        return service.saveRoomDTO(roomDTO);
    }

    /***
     * create new rooms
     * @param roomsDTO is the list of the rooms we want to save
     * @return the list with the saved rooms
     */
    @PostMapping("/api/rooms/create")
    public List<RoomDTO> addRooms(@RequestBody List<RoomDTO> roomsDTO) throws ApiRequestException {

		return service.saveRooms(roomsDTO);
    }

    /***
     * counts all the rooms in the database
     * @return the number of rooms that exists in the database
     */
    @GetMapping("/api/rooms/quantity")
    public int countRooms(){

        return service.countRooms();
    }

    /***
     * finds all rooms
     * @return all rooms
     * @throws ApiRequestException if no room is saved
     */
    @GetMapping("/api/rooms/{pageNo}/{pageSize}/{sortBy}")
    public ResponseEntity<List<RoomDTO>> findAllRooms(
            @PathVariable Integer pageNo,
            @PathVariable Integer pageSize,
            @PathVariable String sortBy)
            throws ApiRequestException {

        List<RoomDTO> rooms = service.getRooms(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<RoomDTO>>(rooms, new HttpHeaders(), HttpStatus.OK);
    }

    /***
     * finds a room by his id
     * @param id id of the room we want to save
     * @return the room with the given id
     * @throws ApiRequestException if there is no room with the given id
     */
    @GetMapping("/api/roomId/{id}")
    public RoomDTO findRoomById(@PathVariable Long id) throws ApiRequestException {

            return service.getRoomById(id);
    }

    /***
     * finds a room by his name
     * @param name name of the room we want to save
     * @return the room with that name
     * @throws ApiRequestException if there is no room with the given name
     */
    @GetMapping("/api/roomName/{name}")
    public RoomDTO findRoomByName(@PathVariable String name) throws ApiRequestException {
            return service.getRoomByName(name);
    }

    /***
     * updates a room
     * @param roomDTO the room we want to update
     * @return the updated room for confirmation
     * @throws ApiRequestException if the room that is going to update is not exists
     */
    @PutMapping("/api/room/update")
    public RoomDTO updateRoom(@RequestBody RoomDTO roomDTO)throws ApiRequestException {

            return service.updateRoom(roomDTO);

    }

    /***
     * enables a room by his id
     * @param id id of the room we want to disable
     * @return a confirmation message or an error message
     * @throws ApiRequestException if the room does not exist or is already activated
     */
	@PostMapping("/api/room/enable/{id}")
	ResponseEntity<String> enableRoom(@PathVariable Long id) throws ApiRequestException {

            service.enableRoom(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Room with id " + id + " was successfully activated");

	}


    /***
     * disables a room by his id
     * @param id of the room we want to disable
     * @return a message of confirmation or an error message
     * @throws ApiRequestException if the room does not exist or is already deactivated
     */
    @PostMapping("/api/room/disable/{id}")
    ResponseEntity<String> disableRoom(@PathVariable Long id) throws ApiRequestException {

            service.disableRoom(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Room with id " + id + " was successfully deactivated");

    }

    /***
     * Retrieves a set of RoomAmenity for the room with the given ID
     * @param roomId The ID of the room for which to retrieve amenities
     * @return A set of RoomAmenity representing the amenities of the room with the given ID
     * @throws ApiRequestException if the id does not exist
     */

    @GetMapping("/api/room/amenities/{roomId}")
    public Set<RoomAmenityDTO> findRoomAmenitiesByRoomId(@PathVariable Long roomId) throws ApiRequestException {

        return service.getRoomAmenitiesByRoomId(roomId);
    }

}
