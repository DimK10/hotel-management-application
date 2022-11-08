package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/***
 * created by gp
 */
@Service
public class HotelService {


	private final HotelRepository hotelRepository;

	private final AdminRepository adminRepository;

	private final HotelDTOToHotel hotelDTOToHotel;

	private final HotelToHotelDTO hotelToHotelDTO;

	private final AdminService adminService;

	public HotelService(HotelRepository hotelRepository, AdminRepository adminRepository, HotelDTOToHotel hotelDTOToHotel, HotelToHotelDTO hotelToHotelDTO, RoomService roomService, AdminService adminService) {
		this.hotelRepository = hotelRepository;
		this.adminRepository = adminRepository;
		this.hotelDTOToHotel = hotelDTOToHotel;
		this.hotelToHotelDTO = hotelToHotelDTO;
		this.adminService = adminService;
	}

	/***
	 * get a hotel by his id
	 * @param id of the hotel tobe found
	 * @return the hotel with the current id
	 * @throws ApiRequestException if the hotel does not exist
	 */
	public HotelDTO getHotelById(Long id) throws ApiRequestException {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (!hotel.isPresent()){
			throw new ApiRequestException("There is no hotel with id: " + id);
		}else {
			return hotelToHotelDTO.converter(hotelRepository.findById(id).get());
		}

	}


	/***
	 * get all hotels
	 * @return a list of all hotels
	 * @throws ApiRequestException if There are no hotels
	 */
	public List<HotelDTO> getHotels() throws ApiRequestException {

			List<Hotel> hotels = new ArrayList<>();
			List<HotelDTO> hotelDTOS = new ArrayList<>();

			hotelRepository.findAll().spliterator().forEachRemaining(hotels::add);

			for (Hotel hotel : hotels) {
				hotelDTOS.add(hotelToHotelDTO.converter(hotel));
			}

			return hotelDTOS;

	}


	/***
	 * get a hotel by his name
	 * @param name of hotel to be found
	 * @return the hotel with the current id
	 * @throws ApiRequestException if the hotel does not exist
	 */
	public HotelDTO getHotelByName(String name) throws ApiRequestException {
		Optional<Hotel> hotel = hotelRepository.findByName(name);
		if (!hotel.isPresent()){
			throw new ApiRequestException("There is no hotel with name: " + name);
		}else {
			Optional<Hotel> hotelOpt = hotelRepository.findByName(name);
				return hotelToHotelDTO.converter(hotelRepository.findByName(name).get());

		}
	}

	/***
	 * enables a hotel by his id
	 * @param id of the hotel to be enabled
	 * @return  a boolean if the action is done or not
	 * @throws ApiExceptionFront if the hotel does not exist or is already activated
	 */
	public boolean enableHotel(Long id) throws ApiExceptionFront {

		if (!hotelRepository.existsById(id)) {
			throw new ApiExceptionFront("There is no hotel with id: " + id);
		}else if(!hotelRepository.findById(id).get().isDisabled()){
			throw new ApiExceptionFront("The hotel with id: " + id + " is already activated");
		}else{
			Hotel hotel = hotelRepository.findById(id).get();
			hotel.setDisabled(false);
			hotelRepository.save(hotel);
			return true;
		}

	}

	/***
	 * disbel a hotel by his id
	 * @param id of the hotel to be disabled
	 * @return a boolean if the action has done or not
	 * @throws ApiExceptionFront if the hotel does not exist or is already deactivated
	 */
	public boolean disableHotel(Long id) throws ApiExceptionFront{

		if (!hotelRepository.existsById(id)){
			throw new ApiExceptionFront("There is no hotel with id: " + id);
		}else if (hotelRepository.findById(id).get().isDisabled()) {
			throw new ApiExceptionFront("The hotel with id: " + id + " is already deactivated");
		} else {
			Hotel hotel = hotelRepository.findById(id).get();
			hotel.setDisabled(true);
			hotelRepository.save(hotel);
			return true;
		}
	}


	public String deleteHotel(Long id){
		hotelRepository.deleteById(id);
		return "Hotel with id" + id + "has be successfully removed";
	}

	/***
	 * update a hotel
	 * @param hotelDTO the hotel to be updated
	 * @return the updated hotel
	 * @throws ApiRequestException if the hotel does not exist
	 */
	public HotelDTO updateHotel(HotelDTO hotelDTO) throws ApiRequestException {

		if (!hotelRepository.existsById(hotelDTO.getId())){
			throw new ApiRequestException("The hotel with id: " + hotelDTO.getId() + " does not exist to update it");
		}else {
			Hotel existingHotel = hotelRepository.findById(hotelDTO.getId()).orElse(null);
			existingHotel.setName(hotelDTO.getName());
			existingHotel.setStars(hotelDTO.getStars());
			existingHotel.setAreaName(hotelDTO.getAreaName());
			Optional<Admin>  admin = adminRepository.findById(hotelDTO.getId());
			admin.ifPresent(existingHotel::setOwner);

			return hotelToHotelDTO.converter(hotelRepository.save(existingHotel));
		}
	}

	/***
	 * saves a hotel
	 * @param hotelDTO hotel to be saved
	 * @return the saved hotel for confirmation
	 * @throws ApiRequestException if the hotel does not have an Owner or does not have rooms
	 */
	public HotelDTO saveHotelDTO(HotelDTO hotelDTO) throws ApiRequestException{
		Hotel hotel = new Hotel();
		Optional<Admin> adminOpt =
				adminRepository.findById(hotelDTO.getOwner());

		Set<RoomDTO> roomOpt = hotelDTO.getRooms();

		if (adminOpt.isPresent() && roomOpt.size()>0){
			hotel = hotelDTOToHotel.converter(hotelDTO);
		}
		if (!adminOpt.isPresent()){
			throw new ApiRequestException("There is no Owner registered with that id, or the id is null!");
		}
		if (roomOpt.size() == 0){
			throw new ApiRequestException("If you wont to save hotels you mast add one or more rooms");
		}

		return hotelToHotelDTO.converter(hotel);
	}

	/***
	 * save a list of hotels
	 * @param hotelsDTO list of hotels to be saved
	 * @return the saved hotels for confirmation
	 * @throws ApiRequestException if one of the hotels does not have an owner , the owner does not exist or does not have rooms
	 */
	public List<HotelDTO> saveHotels(List<HotelDTO> hotelsDTO) throws ApiRequestException {
		List<Hotel> hotels = new ArrayList<>();
		for (HotelDTO hotelDTO : hotelsDTO){

			if (hotelDTO.getOwner() == null || adminService.getAdminById(hotelDTO.getOwner()) == null) {
				throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " There Owner does not exist or you have not add one");
			}

			if (hotelDTO.getRooms() == null){
				throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " In hotel with name: " + hotelDTO.getName() + " there are no rooms");
			}

			hotels.add(hotelDTOToHotel.converter(hotelDTO));
		}

		hotelRepository.saveAll(hotels);

		return hotelsDTO;
	}

}
