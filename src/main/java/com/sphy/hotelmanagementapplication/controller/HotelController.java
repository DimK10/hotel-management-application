package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.service.AdminService;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping("/api/hotel/create")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
        return service.saveHotelDTO(hotelDTO);
    }

    @PostMapping("/api/hotels/create")
    public List<HotelDTO> addHotels(@RequestBody List<HotelDTO> hotelsDTO) throws Exception {

        return service.saveHotels(hotelsDTO);

	}

    @GetMapping("/api/hotels")
    public Set<HotelDTO> findAllHotels(){
		Set<Hotel> hotels = service.getHotels();
		Set<HotelDTO> hotelDTOS = new HashSet<>();
		for (Hotel hotel : hotels){
			hotelDTOS.add(hotelToHotelDTO.converter(hotel));
		}

		return hotelDTOS;
    }

    @GetMapping("/api/hotelId/{id}")
    public HotelDTO findHotelById(@PathVariable Long id) throws Exception {
        return service.getHotelById(id);

    }

    @GetMapping("/api/hotelName/{name}")
    public HotelDTO findHotelByName (@PathVariable String name){
        return service.getHotelByName(name);
    }

    @PutMapping("/api/hotel/update")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO, List<RoomDTO> roomDTOS) {
        return service.updateHotel(hotelDTO, roomDTOS);
    }


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
