package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {


	private final RoomRepository roomRepository;

	private final HotelRepository hotelRepository;

	private final AdminRepository adminRepository;

	private final HotelDTOToHotel hotelDTOToHotel;

	private final HotelToHotelDTO hotelToHotelDTO;

	private final RoomService roomService;

	private final AdminService adminService;

	public HotelService(RoomRepository roomRepository, HotelRepository hotelRepository, AdminRepository adminRepository, HotelDTOToHotel hotelDTOToHotel, HotelToHotelDTO hotelToHotelDTO, RoomService roomService, AdminService adminService) {
		this.roomRepository = roomRepository;
		this.hotelRepository = hotelRepository;
		this.adminRepository = adminRepository;
		this.hotelDTOToHotel = hotelDTOToHotel;
		this.hotelToHotelDTO = hotelToHotelDTO;
		this.roomService = roomService;
		this.adminService = adminService;
	}

	public HotelDTO getHotelById(Long id) {
		Optional<Hotel> hotelOPT = hotelRepository.findById(id);

		if (hotelOPT.isPresent()){
			return hotelToHotelDTO.converter(hotelRepository.findById(id).get());
		}else return null;
	}


	public Set<Hotel> getHotels(){
		Set<Hotel> hotels = new HashSet<>();

		hotelRepository.findAll().spliterator().forEachRemaining(hotels::add);

		return hotels;
	}


	public HotelDTO getHotelByName(String name){
		Hotel hotelOpt = hotelRepository.findByName(name);

		if (hotelOpt != null){
			return hotelToHotelDTO.converter(hotelOpt);
		}else return null;
	}

	public boolean enableHotel(Long id){
		if (hotelRepository.existsById(id)){
			Hotel hotel = hotelRepository.findById(id).get();
			hotel.setDisabled(false);
			hotelRepository.save(hotel);
			return true;
		}else return false;

	}

	public boolean disableHotel(Long id){
		if (hotelRepository.existsById(id)){
			Hotel hotel = hotelRepository.findById(id).get();
			hotel.setDisabled(true);
			hotelRepository.save(hotel);
			return true;
		}else return false;

	}


	public String deleteHotel(Long id){
		hotelRepository.deleteById(id);
		return "Hotel with id" + id + "has be successfully removed";
	}

	public HotelDTO updateHotel(HotelDTO hotelDTO, List<RoomDTO> roomDTOS) throws NullPointerException{
		Optional<Hotel> hotelOpt = hotelRepository.findById(hotelDTO.getId());
		if (hotelOpt.isPresent()){
			Hotel existingHotel = hotelRepository.findById(hotelDTO.getId()).orElse(null);
			existingHotel.setName(hotelDTO.getName());
			existingHotel.setStars(hotelDTO.getStars());
			existingHotel.setAreaName(hotelDTO.getAreaName());
			Optional<Admin>  admin = adminRepository.findById(hotelDTO.getId());
			admin.ifPresent(existingHotel::setOwner);
			for (RoomDTO roomDTO : roomDTOS){
				roomService.updateRoom(roomDTO);
			}
			hotelRepository.save(existingHotel);
		}
		return hotelDTO;
	}

	public HotelDTO saveHotelDTO(HotelDTO hotelDTO) throws Exception {
		Hotel hotel = new Hotel();
		Optional<Admin> adminOpt =
				adminRepository.findById(hotelDTO.getOwner());

		Set<RoomDTO> roomOpt = hotelDTO.getRooms();

		if (adminOpt.isPresent() && roomOpt.size()>0){
			hotel = hotelDTOToHotel.converter(hotelDTO);
		}
		if (!adminOpt.isPresent()){
			throw new Exception("There is no Owner registered with that id, or the id is null!");
		}
		if (roomOpt.size() == 0){
			throw new Exception("If you wont to save hotels you mast add one or more rooms");
		}

		return hotelToHotelDTO.converter(hotel);
	}

	public List<HotelDTO> saveHotels(List<HotelDTO> hotelsDTO) throws Exception {
		List<Hotel> hotels = new ArrayList<>();
		for (HotelDTO hotelDTO : hotelsDTO){

			if (hotelDTO.getOwner() == null || adminService.getAdminById(hotelDTO.getOwner()) == null) {
				throw new Exception("There is no Owner registered with that id, or the id is null!");
			}

			if (hotelDTO.getRooms() == null){
				throw new Exception("If you wont to save hotels you mast add one or more rooms");
			}

			hotels.add(hotelDTOToHotel.converter(hotelDTO));
		}

		hotelRepository.saveAll(hotels);

		return hotelsDTO;
	}

}
