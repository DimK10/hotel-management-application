package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory;
import com.sphy.hotelmanagementapplication.factory.ReverseModelMapperFactory;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.service.AdminService;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HotelController {

    private final HotelService service;

    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    private final AdminRepository adminRepository;

    private final ModelMapperFactory modelMapperFactory;

    private final ReverseModelMapperFactory reverseModelMapperFactory;

    private final AdminService adminService;

    public HotelController(HotelService hotelService, RoomRepository roomRepository, HotelRepository hotelRepository, AdminRepository adminRepository, ModelMapperFactory modelMapperFactory, ReverseModelMapperFactory reverseModelMapperFactory, RoomService roomService, AdminService adminService) {
        this.service = hotelService;
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.adminRepository = adminRepository;
        this.modelMapperFactory = modelMapperFactory;
        this.reverseModelMapperFactory = reverseModelMapperFactory;
        this.adminService = adminService;
    }

    @PostMapping("/api/hotel/create")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO, List<RoomDTO> roomsDTO) throws Exception {
        if (hotelDTO.getOwner() == null || adminService.getAdminById(hotelDTO.getOwner()) == null) {
            throw new Exception("There is no Owner registered with that id, or the id is null!");
        }
        if (roomsDTO.isEmpty()) {
            throw new Exception("For adding a Hotel compulsorily you mast add one ore more rooms");
        }
        return service.saveHotelDTO(hotelDTO, roomsDTO);
    }

    @PostMapping("/api/hotels/create")
    public List<HotelDTO> addHotels(@RequestBody List<HotelDTO> hotelsDTO, List<RoomDTO> roomsDTO) throws Exception {
        if (roomsDTO.isEmpty()){
            throw new Exception("For adding Hotels compulsorily you mast add one ore more rooms for each one");
        }
        return (List<HotelDTO>) service.saveHotels(hotelsDTO, roomsDTO);
    }

    @GetMapping("/api/hotels")
    public List<HotelDTO> findAllHotels(){
        ModelMapper modelMapper = modelMapperFactory.create(ModelMapperFactory.ModelMapperType.HOTEL);
        return service
                .getHotels()
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());
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
