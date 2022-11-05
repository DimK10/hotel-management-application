package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * created by gp
 */
@RestController
public class RoomController {

    private final RoomService service;

    private final HotelService hotelService;

    private final RoomRepository roomRepository;


    public RoomController(RoomService service, HotelService hotelService, RoomRepository roomRepository) {
        this.service = service;
        this.hotelService = hotelService;
        this.roomRepository = roomRepository;
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
            throw new ApiRequestException("There is no Hotel registered with that id, or the id is null!");
        }
        return service.saveRoomDTO(roomDTO);
    }

    /***
     * create new rooms
     * @param roomsDTO is the list of the hotels we want to save
     * @return the list with the saved rooms
     * @throws Exception if the rooms that are going to save does not have a hotel
     */
    @PostMapping("/api/rooms/create")
    public List<RoomDTO> addRooms(@RequestBody List<RoomDTO> roomsDTO) throws Exception {

		return service.saveRooms(roomsDTO);
    }

    /***
     * finds all rooms
     * @return all rooms
     * @throws Exception if no room is saved
     */
    @GetMapping("/api/rooms")
    public List<RoomDTO> findAllRooms() throws Exception {
        if (roomRepository.count() == 0){
            throw new ApiRequestException("There are no rooms");
        }else {
            return service.getRooms();
        }
    }

    /***
     * finds a room by his id
     * @param id id of the room we want to save
     * @return the room with the given id
     * @throws Exception if there is no room with the given id
     */
    @GetMapping("/api/roomId/{id}")
    public RoomDTO findRoomById(@PathVariable Long id) throws Exception {

        if (!roomRepository.existsById(id)){
            throw new ApiRequestException("There is now room with such id");

        }else {
            return service.getRoomById(id);
        }
    }

    /***
     * finds a room by his name
     * @param name name of the room we want to save
     * @return the room with that name
     * @throws ApiRequestException if there is no room with the given name
     */
    @GetMapping("/api/roomName/{name}")
    public RoomDTO findRoomByName(@PathVariable String name) throws ApiRequestException {
        if (!roomRepository.findByName(name).isPresent()){
            throw new ApiRequestException("There is no room with the given name");
        }else {
            return service.getRoomByName(name);
        }
    }

    /***
     * updates a room
     * @param roomDTO the room we want to update
     * @return the updated room for confirmation
     * @throws ApiRequestException if the room that is going to update is not exists
     */
    @PutMapping("/api/room/update")
    public RoomDTO updateRoom(@RequestBody RoomDTO roomDTO)throws ApiRequestException {

        if (!roomRepository.findById(roomDTO.getId()).isPresent()){
            throw new ApiRequestException("The room you wont to update does not exist");
        }else {
            return service.updateRoom(roomDTO);
        }
    }

    /***
     * enables a room by his id
     * @param id id of the room we want to disable
     * @return a confirmation message or an error message
     * @throws ApiRequestException if the room does not exist or is already activated
     */
	@PostMapping("/api/room/enable/{id}")
	ResponseEntity<String> enableRoom(@PathVariable Long id) throws ApiRequestException {

		if (!roomRepository.findById(id).isPresent()) {
			throw  new ApiRequestException("The room you want to activate does not exist");
		}if (!roomRepository.findById(id).get().isDisabled()){
            throw new ApiRequestException("The room you want to activate is already activated");
        }else {
			return ResponseEntity.status(HttpStatus.OK)
					.body("Room with id " + id + " was successfully activated");
		}
	}


    /***
     * disables a room by his id
     * @param id of the room we want to disable
     * @return a message of confirmation or an error message
     * @throws ApiRequestException if the room does not exist or is already deactivated
     */
    @PostMapping("/api/room/disable/{id}")
    ResponseEntity<String> disableRoom(@PathVariable Long id) throws ApiRequestException{

        if (!roomRepository.findById(id).isPresent()) {
            throw new ApiRequestException("The room want to deactivate does not exist");
        }else if (roomRepository.findById(id).get().isDisabled() ){
            throw new ApiRequestException("The room you want to deactivate is already deactivated");
        }else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Room with id " + id + " was successfully deactivated");
        }
    }

}
