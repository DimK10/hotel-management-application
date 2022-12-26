package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.HotelAmenityToHotelAmenityDTO;
import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.HotelAmenityDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/***
 * created by gp
 */
@Service
public class HotelService {


	private final HotelRepository hotelRepository;

	private final UserRepository userRepository;

	private final HotelDTOToHotel hotelDTOToHotel;

	private final HotelToHotelDTO hotelToHotelDTO;

	private final UserService userService;

	private final RoomService roomService;

	private final HotelAmenityToHotelAmenityDTO hotelAmenityToHotelAmenityDTO;

	public HotelService(HotelRepository hotelRepository, HotelDTOToHotel hotelDTOToHotel, HotelToHotelDTO hotelToHotelDTO, RoomService roomService, HotelAmenityToHotelAmenityDTO hotelAmenityToHotelAmenityDTO, UserRepository userRepository, UserService userService) {
		this.hotelRepository = hotelRepository;
		this.hotelDTOToHotel = hotelDTOToHotel;
		this.hotelToHotelDTO = hotelToHotelDTO;
		this.roomService = roomService;
		this.hotelAmenityToHotelAmenityDTO = hotelAmenityToHotelAmenityDTO;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	/***
	 * get a hotel by his id
	 * @param id of the hotel tobe found
	 * @return the hotel with the current id
	 * @throws ApiRequestException if the hotel does not exist
	 */
	public HotelDTO getHotelById(Long id) throws ApiRequestException {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isEmpty()){
			throw new ApiRequestException("There is no hotel with id: " + id);
		}else {
			return hotelToHotelDTO.converter(hotel.get());
		}

	}

	public HotelDTO getHotelById(Long id, Long userId) throws ApiRequestException {

		Optional<Hotel> hotel = hotelRepository.findHotelByIdAndOwner(id, userId);

		if (hotel.isEmpty()){
			throw new ApiRequestException("There is no hotel with id: " + id);
		}else {
			return hotelToHotelDTO.converter(hotel.get());
		}

	}

	/***
	 * counts all the hotels in the database for a specific user id
	 * @return the number of hotels that exists in the database
	 */
	public int countHotels(Long userId){

		return hotelRepository.countAll(userId);
	}


	/***
	 * get all hotels
	 * @return a list of all hotels
	 * @throws ApiRequestException if There are no hotels
	 */
	@Transactional
	public List<HotelDTO> getHotels(Integer pageNo, Integer pageSize, String sortBy, Long userId) throws ApiRequestException {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Hotel> pageResult = hotelRepository.findAllHotelsByOwner(userId, paging);

		List<HotelDTO> hotelDTOS = new ArrayList<>();

		for (Hotel hotel: pageResult.getContent()){
			hotelDTOS.add(hotelToHotelDTO.converter(hotel));
		}

		Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOS, paging,hotelDTOS.size());

		if (!hotelDTOPage.isEmpty()) {
			return hotelDTOPage.getContent();

		}else {
			return new ArrayList<HotelDTO>() {
			};
		}
	}


	/***
	 * get a hotel by his name
	 * @param name of hotel to be found
	 * @return the hotel with the current id
	 * @throws ApiRequestException if the hotel does not exist
	 */
	public HotelDTO getHotelByName(String name) throws ApiRequestException {
		Optional<Hotel> hotel = hotelRepository.findByName(name);
		if (hotel.isEmpty()){
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
			Optional<User>  admin = userRepository.findById(hotelDTO.getOwner());
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
		Optional<User> adminOpt =
				userRepository.findById(hotelDTO.getOwner());

		Set<RoomDTO> roomOpt = hotelDTO.getRooms();

		if (adminOpt.isEmpty()){
			throw new ApiRequestException("There is no Owner registered with that id, or the id is null!");
		}
		if (roomOpt.isEmpty()){
			throw new ApiRequestException("If you wont to save hotels you mast add one or more rooms");
		}
		List<RoomDTO> roomDTOS = new ArrayList<>();
		roomDTOS.addAll(hotelDTO.getRooms());
		hotelDTO.getRooms().clear();
		Hotel hotel = hotelDTOToHotel.converter(hotelDTO);
		hotelRepository.save(hotel);
		for(RoomDTO roomDTO : roomDTOS){
			roomDTO.setHotel(hotel.getId());
		}
		roomService.saveRooms(roomDTOS);

		return hotelToHotelDTO.converter(hotelRepository.findById(hotel.getId()).get());

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

			if (hotelDTO.getOwner() == null || userService.getUserById(hotelDTO.getOwner()) == null) {
				throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " There Owner does not exist or you have not add one");
			}

			if (hotelDTO.getRooms() == null){
				throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " In hotel with name: " + hotelDTO.getName() + " there are no rooms");
			}
			List<RoomDTO> roomDTOS = new ArrayList<>();
			roomDTOS.addAll(hotelDTO.getRooms());
			hotelDTO.getRooms().clear();
			Hotel hotel = hotelDTOToHotel.converter(hotelDTO);
			hotelRepository.save(hotel);
			for(RoomDTO roomDTO : roomDTOS){
				roomDTO.setHotel(hotel.getId());
			}
			roomService.saveRooms(roomDTOS);
			hotels.add(hotelRepository.findById(hotel.getId()).get());
		}

		List<HotelDTO> hotelDTOS = new ArrayList<>();
		for (Hotel hotel:hotels){
			hotelDTOS.add(hotelToHotelDTO.converter(hotel));
		}
		return hotelDTOS;
	}


	/***
	 * Retrieves a set of HotelAmenity for the hotel with the given ID
	 * @param id The ID of the hotel for which to retrieve amenities
	 * @return A set of HotelAmenity representing the amenities of the hotel with the given ID
	 */

	public Set<HotelAmenityDTO> getHotelAmenitiesByHotelId(Long id){

		Set<HotelAmenityDTO> amenitiesHotelDTO = new HashSet<>();
		Optional<Hotel> hotelOptional = hotelRepository.findById(id);
		hotelOptional.ifPresent(hotel -> hotel.getHotelAmenity()
								.forEach(hotelAmenity -> {
								amenitiesHotelDTO.add(hotelAmenityToHotelAmenityDTO.converter(hotelAmenity));
								}));

		return amenitiesHotelDTO;
	}


}
