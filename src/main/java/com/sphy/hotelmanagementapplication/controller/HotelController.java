package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.service.AdminService;
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
public class HotelController {

    private final HotelService service;

    private final RoomService roomService;

    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    private final AdminRepository adminRepository;

    private final AdminService adminService;

	private final HotelToHotelDTO hotelToHotelDTO;

    private final HotelDTOToHotel hotelDTOToHotel;

    public HotelController(HotelService hotelService, RoomService roomService1, RoomRepository roomRepository, HotelRepository hotelRepository, AdminRepository adminRepository, RoomService roomService, AdminService adminService, HotelToHotelDTO hotelToHotelDTO, HotelDTOToHotel hotelDTOToHotel) {
        this.service = hotelService;
        this.roomService = roomService1;
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.adminRepository = adminRepository;
        this.adminService = adminService;
		this.hotelToHotelDTO = hotelToHotelDTO;
        this.hotelDTOToHotel = hotelDTOToHotel;
    }

    /***
     * Creates a new hotel
     * @param hotelDTO new hotel to be saved
     * @return the saved hotel for confirmation
     * @throws Exception
     */
    @PostMapping("/api/hotel/create")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
        return service.saveHotelDTO(hotelDTO);
    }

    /***
     * Create new hotels
     * @param hotelsDTO is a list of hotels to be saved
     * @return the list of hotels that where saved
     * @throws Exception
     */
    @PostMapping("/api/hotels/create")
    public List<HotelDTO> addHotels(@RequestBody List<HotelDTO> hotelsDTO) throws Exception {

        return service.saveHotels(hotelsDTO);

	}

    /***
     * Finds all hotels
     * @return all hotels
     * @throws Exception
     */
    @GetMapping("/api/hotels")
    public List<HotelDTO> findAllHotels() throws Exception {

		return service.getHotels();
    }

    /***
     * Finds a hotel by his id
     * @param id id of the hotel that we want to find
     * @return the hotel with the given id
     * @throws Exception
     */
    @GetMapping("/api/hotelId/{id}")
    public HotelDTO findHotelById(@PathVariable Long id) throws Exception {
        return service.getHotelById(id);

    }

    /***
     * Finds a hotel by his name
     * @param name the name of the hotel we want to find
     * @return the hotel with the given name
     * @throws Exception
     */
    @GetMapping("/api/hotelName/{name}")
    public HotelDTO findHotelByName (@PathVariable String name) throws Exception {
        return service.getHotelByName(name);
    }

    /***
     * update a hotels parameters
     * @param hotelDTO new hotel parameters
     * @return tha updated hotel for confirmation
     */
    @PutMapping("/api/hotel/update")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
        return service.updateHotel(hotelDTO);
    }


    /***
     * enables a hotel by his id
     * @param id of the hotel we want to enable
     * @return a message of confirmation of the action or not found
     */
    @PostMapping("/api/hotel/enable/{id}")
    ResponseEntity<String> enableHotel(@PathVariable Long id) {

        if (!service.enableHotel(id)) {
            return ResponseEntity.badRequest()
                    .body("The id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Hotel with id " + id + " was successfully activated");
        }
    }


    /***
     * disables a hotel by his id
     * @param id of the hotel we want to disable
     * @return a message of confirmation or not found
     */
    @PostMapping("/api/hotel/disable/{id}")
    ResponseEntity<String> disableHotel(@PathVariable Long id) {

        if (!service.disableHotel(id)) {
            return ResponseEntity.badRequest()
                    .body("The id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Hotel with id " + id + " was successfully deactivated");
        }
    }


}
