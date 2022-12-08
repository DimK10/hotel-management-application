package com.sphy.hotelmanagementapplication.controller;


import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.service.HotelService;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * created by gp
 */
@RestController
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService hotelService) {
        this.service = hotelService;

    }

    /***
     * Creates a new hotel
     * @param hotelDTO new hotel to be saved
     * @return the saved hotel for confirmation
     */
    @PostMapping("/api/hotel/create")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO) throws ApiRequestException {
        return service.saveHotelDTO(hotelDTO);
    }

    /***
     * Create new hotels
     * @param hotelsDTO is a list of hotels to be saved
     * @return the list of hotels that where saved
     */
    @PostMapping("/api/hotels/create")
    public List<HotelDTO> addHotels(@RequestBody List<HotelDTO> hotelsDTO) {

        return service.saveHotels(hotelsDTO);

	}

    /***
     * counts all the hotels in the database
     * @return the number of hotels that exists in the database
     */
    @GetMapping("/api/hotels/quantity")
    public int countHotels(){

        return service.countHotels();
    }

    /***
     * Finds all hotels
     * @return all hotels
     * @throws ApiRequestException if There are no hotels
     */
    @GetMapping("/api/hotels/{pageNo}/{pageSize}/{sortBy}")
    @Transactional
    public ResponseEntity<List<HotelDTO>> findAllRooms(
            @PathVariable Integer pageNo,
            @PathVariable Integer pageSize,
            @PathVariable String sortBy)
            throws ApiRequestException {

        List<HotelDTO> hotelDTOS = service.getHotels(pageNo,pageSize, sortBy);

        return new ResponseEntity<List<HotelDTO>>(hotelDTOS, new HttpHeaders(), HttpStatus.OK);
    }

    /***
     * Finds a hotel by his id
     * @param id id of the hotel that we want to find
     * @return the hotel with the given id
     * @throws ApiRequestException if the hotel does not exist
     */
    @GetMapping("/api/hotelId/{id}")
    public HotelDTO findHotelById(@PathVariable Long id) throws ApiRequestException {

            return service.getHotelById(id);

    }

    /***
     * Finds a hotel by his name
     * @param name the name of the hotel we want to find
     * @return the hotel with the given name
     * @throws ApiRequestException if the hotel does not exist
     */
    @GetMapping("/api/hotelName/{name}")
    public HotelDTO findHotelByName (@PathVariable String name) throws ApiRequestException {

            return service.getHotelByName(name);

    }

    /***
     * update a hotels parameters
     * @param hotelDTO new hotel parameters
     * @return tha updated hotel for confirmation
     * @throws ApiRequestException if the hotel does not exist
     */
    @PutMapping("/api/hotel/update")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO) throws ApiRequestException {

            return service.updateHotel(hotelDTO);

    }


    /***
     * enables a hotel by his id
     * @param id of the hotel we want to enable
     * @return a message of confirmation of the action or not found
     * @throws ApiRequestException if the hotel does not exist or is already activated
     */
    @PostMapping("/api/hotel/enable/{id}")
    ResponseEntity<String> enableHotel(@PathVariable Long id)throws ApiRequestException {

            service.enableHotel(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Hotel with id " + id + " was successfully activated");

    }


    /***
     * disables a hotel by his id
     * @param id of the hotel we want to disable
     * @return a message of confirmation or not found
     * @throws ApiRequestException if the hotel does not exist or is already deactivated
     */
    @PostMapping("/api/hotel/disable/{id}")
    ResponseEntity<String> disableHotel(@PathVariable Long id) throws ApiRequestException {

            service.disableHotel(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Hotel with id " + id + " was successfully deactivated");

    }


}
