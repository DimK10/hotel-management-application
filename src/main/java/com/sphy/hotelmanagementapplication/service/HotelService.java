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
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	/***
	 * get a hotel by his id
	 * @param id of the hotel tobe found
	 * @return the hotel with the current id
	 * @throws Exception
	 */
	public HotelDTO getHotelById(Long id) throws Exception {
		Optional<Hotel> hotelOPT = hotelRepository.findById(id);

		if (hotelOPT.isPresent()){
			return hotelToHotelDTO.converter(hotelRepository.findById(id).get());
		}else return null;
	}


	/***
	 * get all hotels
	 * @return a list of all hotels
	 * @throws Exception
	 */
	public List<HotelDTO> getHotels() throws Exception {

		List<Hotel> hotels = new ArrayList<>();
		List<HotelDTO> hotelDTOS = new ArrayList<>();

		hotelRepository.findAll().spliterator().forEachRemaining(hotels::add);

		for (Hotel hotel : hotels){
			hotelDTOS.add(hotelToHotelDTO.converter(hotel));
		}

		return hotelDTOS;
	}


	/***
	 * get a hotel by his name
	 * @param name of hotel to be found
	 * @return the hotel with the current id
	 * @throws Exception
	 */
	public HotelDTO getHotelByName(String name) throws Exception {

		Optional<Hotel> hotelOpt = hotelRepository.findByName(name);

		if (hotelOpt.isPresent()){
			return hotelToHotelDTO.converter(hotelOpt.get());
		}else return null;
	}

	/***
	 * enables a hotel by his id
	 * @param id of the hotel to be enabled
	 * @return  a boolean if the action is done or not
	 */
	public boolean enableHotel(Long id){
		if (hotelRepository.existsById(id)){
			Hotel hotel = hotelRepository.findById(id).get();
			hotel.setDisabled(false);
			hotelRepository.save(hotel);
			return true;
		}else return false;

	}

	/***
	 * disbel a hotel by his id
	 * @param id of the hotel to be disabled
	 * @return a boolean if the action has done or not
	 */
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

	/***
	 * update a hotel
	 * @param hotelDTO the hotel to be updated
	 * @return the updated hotel
	 * @throws NullPointerException
	 */
	public HotelDTO updateHotel(HotelDTO hotelDTO) throws Exception {
		Optional<Hotel> hotelOpt = hotelRepository.findById(hotelDTO.getId());
		if (hotelOpt.isPresent()){
			Hotel existingHotel = hotelRepository.findById(hotelDTO.getId()).orElse(null);
			existingHotel.setName(hotelDTO.getName());
			existingHotel.setStars(hotelDTO.getStars());
			existingHotel.setAreaName(hotelDTO.getAreaName());
			Optional<Admin>  admin = adminRepository.findById(hotelDTO.getId());
			admin.ifPresent(existingHotel::setOwner);

			return hotelToHotelDTO.converter(hotelRepository.save(existingHotel));
		}
		return hotelDTO;
	}

	/***
	 * saves a hotel
	 * @param hotelDTO hotel to be saved
	 * @return the saved hotel for confirmation
	 * @throws Exception
	 */
	public HotelDTO saveHotelDTO(HotelDTO hotelDTO) throws Exception {
		Hotel hotel = new Hotel(1L);
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

	/***
	 * save a list of hotels
	 * @param hotelsDTO list of hotels to be saved
	 * @return the saved hotels for confirmation
	 * @throws Exception
	 */
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
