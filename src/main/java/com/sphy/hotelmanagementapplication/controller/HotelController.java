package com.sphy.hotelmanagementapplication.controller;


import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.sphy.hotelmanagementapplication.domain.HotelAmenity;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.BasicSearchDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.UserService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/***
 * created by gp
 */
@RestController
public class HotelController {

    private final HotelService service;
    private final UserService userService;

    public HotelController(HotelService hotelService, UserService userService) {
        this.service = hotelService;
        this.userService = userService;
    }

    /***
     * Creates a new hotel
     * @param hotelDTO new hotel to be saved
     * @return the saved hotel for confirmation
     */
    @PostMapping("/api/hotel/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HotelDTO addHotel(@RequestHeader(name = "Authorization") String token, @RequestBody HotelDTO hotelDTO) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token).getId(), hotelDTO.getOwner())) {

            return service.saveHotelDTO(hotelDTO);
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

    /***
     * Create new hotels
     * @param hotelsDTO is a list of hotels to be saved
     * @return the list of hotels that where saved
     */
    @PostMapping("/api/hotels/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HotelDTO> addHotels(@RequestHeader(name = "Authorization") String token, @RequestBody List<HotelDTO> hotelsDTO) {

        boolean equal = true;

        for (HotelDTO hotelDTO : hotelsDTO) {

            if (!hotelDTO.getOwner().equals(userService.getUserFromToken(token).getId())) {
                equal = false;
            }
        }

        if (equal){

            return service.saveHotels(hotelsDTO);
        } else {

            throw new RuntimeException("Unauthorized");
        }
    }

    /***
     * counts all the hotels in the database for a specific user id
     * @return the number of hotels that exists in the database
     */
    @GetMapping("/api/hotels/quantity/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public int countHotels(@RequestHeader(name = "Authorization") String token, @PathVariable Long userId) {

        if (Objects.equals(userId, userService.getUserFromToken(token).getId())) {

            return service.countHotels(userId);
        } else {

            throw new RuntimeException("Unauthorized");
        }
    }

    /***
     * Finds all hotels
     * @return all hotels for a specific user id
     * @throws ApiRequestException if There are no hotels
     */
    @GetMapping("/api/hotels/{pageNo}/{pageSize}/{sortBy}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ResponseEntity<List<HotelDTO>> findAllHotelsByPage(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable Integer pageNo,
            @PathVariable Integer pageSize,
            @PathVariable String sortBy)
            throws ApiRequestException {

        Long userId = userService.getUserFromToken(token).getId();

        if (Objects.equals(userId, userService.getUserFromToken(token).getId())) {

            List<HotelDTO> hotelDTOS = service.getHotels(pageNo, pageSize, sortBy, userId);

            return new ResponseEntity<>(hotelDTOS, new HttpHeaders(), HttpStatus.OK);
        } else {
            throw new ApiRequestException("Unauthorized");
        }
    }

    /***
     * Finds a hotel by his id
     * @param id id of the hotel that we want to find
     * @return the hotel with the given id
     * @throws ApiRequestException if the hotel does not exist
     */
    @GetMapping("/api/hotelId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HotelDTO findHotelById(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        User user = userService.getUserFromToken(token);
        return service.getHotelById(id, user.getId());
    }

    /***
     * Finds a hotel by his name
     * @param name the name of the hotel we want to find
     * @return the hotel with the given name
     * @throws ApiRequestException if the hotel does not exist
     */
    @GetMapping("/api/hotelName/{name}")
    public HotelDTO findHotelByName(@PathVariable String name) throws ApiRequestException {

        return service.getHotelByName(name);

    }

    /***
     * update a hotels parameters
     * @param hotelDTO new hotel parameters
     * @return tha updated hotel for confirmation
     * @throws ApiRequestException if the hotel does not exist
     */
    @PutMapping("/api/hotel/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HotelDTO updateHotel(@RequestHeader(name = "Authorization") String token, @RequestBody HotelDTO hotelDTO) throws ApiRequestException {

        if (Objects.equals(hotelDTO.getOwner(), userService.getUserFromToken(token).getId())) {

            return service.updateHotel(hotelDTO);
        } else {
            throw new ApiRequestException("Unauthorized");
        }

    }


    /***
     * enables a hotel by his id
     * @param id of the hotel we want to enable
     * @return a message of confirmation of the action or not found
     * @throws ApiRequestException if the hotel does not exist or is already activated
     */
    @PostMapping("/api/hotel/enable/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> enableHotel(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        HotelDTO hotelOptional = service.getHotelById(id);

        if (Objects.equals(hotelOptional.getOwner(), userService.getUserFromToken(token).getId())) {

            service.enableHotel(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Hotel with id " + id + " was successfully activated");
        } else {
            throw new ApiRequestException("Unauthorized");
        }
    }


    /***
     * disables a hotel by his id
     * @param id of the hotel we want to disable
     * @return a message of confirmation or not found
     * @throws ApiRequestException if the hotel does not exist or is already deactivated
     */
    @PostMapping("/api/hotel/disable/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> disableHotel(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        HotelDTO hotelOptional = service.getHotelById(id);

        if (Objects.equals(hotelOptional.getOwner(), userService.getUserFromToken(token).getId())) {

            service.disableHotel(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Hotel with id " + id + " was successfully deactivated");

        } else {
            throw new ApiRequestException("Unauthorized");
        }
    }

    /***
     * Retrieves a set of HotelAmenity for the hotel with the given ID
     * @param hotelId The ID of the hotel for which to retrieve amenities
     * @return A set of HotelAmenity representing the amenities of the hotel with the given ID
     * @throws ApiRequestException if the id does not exist
     */

    @GetMapping("/api/hotel/amenities/{hotelId}")
    public Set<HotelAmenity> findHotelAmenitiesByHotelId(@PathVariable Long hotelId) throws ApiRequestException {

            return service.getHotelAmenitiesByHotelId(hotelId);

    }

    /***
     * returns the hotels that are available in specific dates in a location
     * or a hotel if it is available at that dates if the search made by the hotel name
     * @param basicSearchDTO basic search fields (check in date, check out date, location name or hotel name)
     * @return the hotels than mach with the search
     * @throws RuntimeException if this that made the search is not a role client
     */
    @GetMapping("/api/hotel/basic/search")
    public Set<HotelDTO> findHotelBasicSearch(@RequestBody BasicSearchDTO basicSearchDTO)throws RuntimeException{

            return service.getHotelBasicSearch(basicSearchDTO);

    }


}
