package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/***
 * created by gp
 */
@RestController
public class RoomController {

    private final RoomService service;

    private final HotelService hotelService;

    private final UserService userService;


    public RoomController(RoomService service, HotelService hotelService, UserService userService) {
        this.service = service;
        this.hotelService = hotelService;

        this.userService = userService;
    }

    /***
     * creates a new room
     * @param roomDTO is the room we want to save
     * @return the saved room for confirmation
     * @throws ApiRequestException if the room's hotel does not exist
     */
    @PostMapping("/api/room/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoomDTO addRoom(@RequestHeader(name = "Authorization") String token, @RequestBody RoomDTO roomDTO) throws ApiRequestException {

        if (Objects.equals(hotelService.getHotelById(roomDTO.getHotel()).getOwner()
                , userService.getUserFromToken(token).getId())) {

            return service.saveRoomDTO(roomDTO);

        } else {

            throw new RuntimeException("Unauthorized");
        }
    }

    /***
     * create new rooms
     * @param roomsDTO is the list of the rooms we want to save
     * @return the list with the saved rooms
     */
    @PostMapping("/api/rooms/create")
    @PreAuthorize("hasAuthority('ADMIN')")

    public List<RoomDTO> addRooms(@RequestHeader(name = "Authorization") String token, @RequestBody List<RoomDTO> roomsDTO) throws ApiRequestException {

        if (Objects.deepEquals(hotelService.getHotelById(roomsDTO.get(0).getHotel()).getOwner()
                , userService.getUserFromToken(token).getId())) {

            return service.saveRooms(roomsDTO);
        } else {

            throw new RuntimeException("Unauthorized");
        }


    }

    /***
     * counts all the rooms in the database
     * @return the number of rooms that exists in the database
     */
    @GetMapping("/api/rooms/quantity")
    @PreAuthorize("hasAuthority('ADMIN')")
    public int countRooms(@RequestHeader(name = "Authorization") String token) {

        return service.countRooms(userService.getUserFromToken(token).getId());

    }

    /***
     * finds all rooms
     * @return all rooms
     * @throws ApiRequestException if no room is saved
     */
    @GetMapping("/api/rooms/{pageNo}/{pageSize}/{sortBy}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RoomDTO>> findAllRooms(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable Integer pageNo,
            @PathVariable Integer pageSize,
            @PathVariable String sortBy)
            throws ApiRequestException {

        List<RoomDTO> rooms = service.getRooms(pageNo, pageSize, sortBy, userService.getUserFromToken(token).getId());

        return new ResponseEntity<>(rooms, new HttpHeaders(), HttpStatus.OK);
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoomDTO updateRoom(@RequestHeader(name = "Authorization") String token, @RequestBody RoomDTO roomDTO) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token).getId(), hotelService.getHotelById(roomDTO.getHotel()).getOwner())) {

            return service.updateRoom(roomDTO);
        } else {

            throw new RuntimeException("Unauthorized");
        }

    }

    /***
     * enables a room by his id
     * @param id id of the room we want to disable
     * @return a confirmation message or an error message
     * @throws ApiRequestException if the room does not exist or is already activated
     */
    @PostMapping("/api/room/enable/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> enableRoom(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token).getId(), hotelService.getHotelById(service.getRoomById(id).getHotel()).getOwner())) {

            service.enableRoom(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Room with id " + id + " was successfully activated");

        } else {

            throw new RuntimeException("Unauthorized");
        }
    }


    /***
     * disables a room by his id
     * @param id of the room we want to disable
     * @return a message of confirmation or an error message
     * @throws ApiRequestException if the room does not exist or is already deactivated
     */
    @PostMapping("/api/room/disable/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> disableRoom(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token).getId(), hotelService.getHotelById(service.getRoomById(id).getHotel()).getOwner())) {

            service.disableRoom(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Room with id " + id + " was successfully deactivated");

        } else {

            throw new RuntimeException("Unauthorized");
        }
    }

    /***
     * created by AKd
     * Retrieves a set of RoomAmenity for the room with the given ID
     * @param roomId The ID of the room for which to retrieve amenities
     * @return A set of RoomAmenity representing the amenities of the room with the given ID
     * @throws ApiRequestException if the id does not exist
     */

    @GetMapping("/api/room/amenities/{roomId}")
    public Set<RoomAmenity> findRoomAmenitiesByRoomId(@PathVariable Long roomId) throws ApiRequestException {

            return service.getRoomAmenitiesByRoomId(roomId);
    }

    /***
     * returns all room amenities
     * @return room amenities
     * @throws RuntimeException when not exist any hotel amenity
     */
    @GetMapping("/api/room/amenities")
    public Set<RoomAmenity> findRoomAmenities()throws RuntimeException{

        return service.getRoomAmenities();

    }

    /***
     * Created by BP
     * Add Room Amenity
     * @param roomAmenity new room Amenity parameters
     * @return added the new Room Amenity
     * @throws ApiRequestException if the user is not Authorised to add Room Amenity
     */
    @PutMapping("/api/room/saveRoomAmenity")
    @PreAuthorize("hasAuthority('SUPERUSER')")
    public RoomAmenity saveRoomAmenity(@RequestHeader(name="Authorization")String token, @RequestBody RoomAmenity roomAmenity) throws ApiRequestException{

        if (Objects.equals(User.Role.SUPERUSER, userService.getUserFromToken(token).getRole())) {

            return service.saveRoomAmenity(roomAmenity);
        } else {
            throw new ApiRequestException("Unauthorized");
        }
    }


}
