package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory;
import com.sphy.hotelmanagementapplication.factory.ReverseModelMapperFactory;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {


	private final RoomRepository roomRepository;

	private final HotelRepository hotelRepository;

	private final AdminRepository adminRepository;

	private final ModelMapperFactory modelMapperFactory;

	private final ReverseModelMapperFactory reverseModelMapperFactory;

	private final RoomService roomService;

	public HotelService(RoomRepository roomRepository, HotelRepository hotelRepository, AdminRepository adminRepository, ModelMapperFactory modelMapperFactory, ReverseModelMapperFactory reverseModelMapperFactory, RoomService roomService) {
		this.roomRepository = roomRepository;
		this.hotelRepository = hotelRepository;
		this.adminRepository = adminRepository;
		this.modelMapperFactory = modelMapperFactory;
		this.reverseModelMapperFactory = reverseModelMapperFactory;
		this.roomService = roomService;
	}


	public HotelDTO getHotelById(Long id) {
		Optional<Hotel> hotelOPT = hotelRepository.findById(id);

		if (hotelOPT.isPresent()){
			ModelMapper modelMapper = modelMapperFactory.create(ModelMapperFactory.ModelMapperType.HOTEL);
			System.out.println(modelMapper.map(hotelOPT.get(), HotelDTO.class));
			return modelMapper.map(hotelOPT.get(), HotelDTO.class);
		}else return null;
	}


	public List<Hotel> getHotels(){
		List<Hotel> hotels = new ArrayList<>();
		hotelRepository.findAll().forEach(hotels::add);
		return hotels;
	}


	public HotelDTO getHotelByName(String name){
		Hotel hotelOpt = hotelRepository.findByName(name);

		if (hotelOpt != null){
			ModelMapper modelMapper = modelMapperFactory.create(ModelMapperFactory.ModelMapperType.HOTEL);			return modelMapper.map(hotelOpt, HotelDTO.class);
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

	public HotelDTO saveHotelDTO(HotelDTO hotelDTO, List<RoomDTO> roomsDTO) {
		Hotel hotel = new Hotel();

		Optional<Admin> adminOpt =
				adminRepository.findById(hotelDTO.getOwner());
		adminOpt.ifPresent(hotel::setOwner);
		hotel.setName(hotelDTO.getName());
		hotel.setAreaName(hotelDTO.getAreaName());
		hotel.setStars(hotelDTO.getStars());

		roomService.saveRooms(roomsDTO);

		ModelMapper modelMapper = modelMapperFactory.create(ModelMapperFactory.ModelMapperType.HOTEL);

		return modelMapper.map(hotelRepository.save(hotel), HotelDTO.class);
	}

	public List<HotelDTO> saveHotels(List<HotelDTO> hotelsDTO, List<RoomDTO> roomsDTOS) {
		List<Hotel> hotels = new ArrayList<>();
		ModelMapper modelMapper = reverseModelMapperFactory.create(ModelMapperFactory.ModelMapperType.HOTEL);
		modelMapper.map(hotelsDTO, hotels);

		roomService.saveRooms(roomsDTOS);

		hotelRepository.saveAll(hotels);

		return hotelsDTO;
	}

}
