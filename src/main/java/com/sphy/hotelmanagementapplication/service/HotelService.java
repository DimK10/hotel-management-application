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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

@Service
public class HotelService {


	private final RoomRepository roomRepository;

	private final HotelRepository hotelRepository;

	private final AdminRepository adminRepository;

	private final HotelDTOToHotel hotelDTOToHotel;

	private final HotelToHotelDTO hotelToHotelDTO;

	private final RoomService roomService;

	public HotelService(RoomRepository roomRepository, HotelRepository hotelRepository, AdminRepository adminRepository, HotelDTOToHotel hotelDTOToHotel, HotelToHotelDTO hotelToHotelDTO, RoomService roomService) {
		this.roomRepository = roomRepository;
		this.hotelRepository = hotelRepository;
		this.adminRepository = adminRepository;
		this.hotelDTOToHotel = hotelDTOToHotel;
		this.hotelToHotelDTO = hotelToHotelDTO;
		this.roomService = roomService;
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
		adminOpt.ifPresent(hotel::setOwner);
		hotel.setName(hotelDTO.getName());
		hotel.setAreaName(hotelDTO.getAreaName());
		hotel.setStars(hotelDTO.getStars());

		return hotelDTO;
	}

	public List<HotelDTO> saveHotels(List<HotelDTO> hotelsDTO, List<RoomDTO> roomsDTOS) throws Exception {
		List<Hotel> hotels = new ArrayList<>();

		for (HotelDTO hotelDTO : hotelsDTO){
			hotels.add(hotelDTOToHotel.converter(hotelDTO));
		}

		roomService.saveRooms(roomsDTOS);

		hotelRepository.saveAll(hotels);

		return hotelsDTO;
	}

}
