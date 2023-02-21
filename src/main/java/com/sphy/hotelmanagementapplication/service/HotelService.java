package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.HotelDTOToHotel;
import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.BasicSearchDTO;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repository.AmenityHotelRepository;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.IntermediateHotelAmenityRepository;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

/***
 * created by gp
 */
@Service
public class HotelService {

    private final EntityManager entityManager;

    private final HotelRepository hotelRepository;

    private final UserRepository userRepository;

    private final HotelDTOToHotel hotelDTOToHotel;

    private final HotelToHotelDTO hotelToHotelDTO;

    private final UserService userService;

    private final RoomService roomService;

    private final IntermediateHotelAmenityRepository intermediateHotelAmenityRepository;

    private final AmenityHotelRepository amenityHotelRepository;


    public HotelService(EntityManager entityManager, HotelRepository hotelRepository, HotelDTOToHotel hotelDTOToHotel, HotelToHotelDTO hotelToHotelDTO, RoomService roomService, UserRepository userRepository, UserService userService, IntermediateHotelAmenityRepository intermediateHotelAmenityRepository, AmenityHotelRepository amenityHotelRepository) {
        this.entityManager = entityManager;
        this.hotelRepository = hotelRepository;
        this.hotelDTOToHotel = hotelDTOToHotel;
        this.hotelToHotelDTO = hotelToHotelDTO;
        this.roomService = roomService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.intermediateHotelAmenityRepository = intermediateHotelAmenityRepository;
        this.amenityHotelRepository = amenityHotelRepository;
    }

    /***
     * get a hotel by his id
     * @param id of the hotel tobe found
     * @return the hotel with the current id
     * @throws ApiRequestException if the hotel does not exist
     */
    public HotelDTO getHotelById(Long id) throws ApiRequestException {

        Optional<Hotel> hotel = hotelRepository.findById(id);

        if (hotel.isEmpty()) {
            throw new ApiRequestException("There is no hotel with id: " + id);
        } else {
            return hotelToHotelDTO.converter(hotel.get());
        }

    }

    /***
     * Get a hotel by id and owners id
     * @param id of the hotel tobe found
     * @param userId of the user
     * @return the hotel with the current id
     * @throws ApiRequestException
     */
    public HotelDTO getHotelById(Long id, Long userId) throws ApiRequestException {

        Optional<Hotel> hotel = hotelRepository.findHotelByIdAndOwner(id, userId);

        if (hotel.isEmpty()) {
            throw new ApiRequestException("There is no hotel with id: " + id);
        } else {
            return hotelToHotelDTO.converter(hotel.get());
        }

    }

    /***
     * counts all the hotels in the database for a specific user id
     * @return the number of hotels that exists in the database
     */
    public int countHotels(Long userId) {

        return hotelRepository.countAll(userId);
    }


    /**
     * Get all hotels by user id
     *
     * @param userId The use to get the hotels
     * @return A Hashset of hotels in DTO object
     */
    public Set<HotelDTO> getHotels(Long userId) {


        Set<Hotel> hotels = hotelRepository.findAllHotelsByOwner(userId);

        Set<HotelDTO> hotelDTOS = new LinkedHashSet<>();

        hotels.forEach(hotel -> hotelDTOS.add(hotelToHotelDTO.converter(hotel)));

        return hotelDTOS;
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

        for (Hotel hotel : pageResult.getContent()) {
            hotelDTOS.add(hotelToHotelDTO.converter(hotel));
        }

        Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOS, paging, hotelDTOS.size());

        if (!hotelDTOPage.isEmpty()) {
            return hotelDTOPage.getContent();

        } else {
            return new ArrayList<>() {
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
        if (hotel.isEmpty()) {
            throw new ApiRequestException("There is no hotel with name: " + name);
        } else {
            return hotelToHotelDTO.converter(hotel.get());

        }
    }

    /***
     * enables a hotel by his id
     * @param id of the hotel to be enabled
     * @return a boolean if the action is done or not
     * @throws ApiExceptionFront if the hotel does not exist or is already activated
     */
    public boolean enableHotel(Long id) throws ApiExceptionFront {

        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (hotelOptional.isEmpty()) {
            throw new ApiExceptionFront("There is no hotel with id: " + id);
        } else if (!hotelOptional.get().isDisabled()) {
            throw new ApiExceptionFront("The hotel with id: " + id + " is already activated");
        } else {
            Hotel hotel = hotelOptional.get();
            hotel.setDisabled(false);
            hotelRepository.save(hotel);
            return true;
        }

    }

    /***
     * disable a hotel by his id
     * @param id of the hotel to be disabled
     * @return a boolean if the action has done or not
     * @throws ApiExceptionFront if the hotel does not exist or is already deactivated
     */
    public boolean disableHotel(Long id) throws ApiExceptionFront {

        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (hotelOptional.isEmpty()) {
            throw new ApiExceptionFront("There is no hotel with id: " + id);
        } else if (hotelOptional.get().isDisabled()) {
            throw new ApiExceptionFront("The hotel with id: " + id + " is already deactivated");
        } else {
            Hotel hotel = hotelOptional.get();
            hotel.setDisabled(true);
            hotelRepository.save(hotel);
            return true;
        }
    }

    /***
     * update a hotel
     * @param hotelDTO the hotel to be updated
     * @return the updated hotel
     * @throws ApiRequestException if the hotel does not exist
     */
    public HotelDTO updateHotel(HotelDTO hotelDTO) throws ApiRequestException {

        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelDTO.getId());


        synchronized (this) {

            if (hotelOptional.isEmpty()) {
                throw new ApiRequestException("The hotel with id: " + hotelDTO.getId() + " does not exist to update it");

            } else {
                Hotel existingHotel = hotelOptional.get();
                existingHotel.setName(hotelDTO.getName());
                existingHotel.setStars(hotelDTO.getStars());
                existingHotel.setAreaName(hotelDTO.getAreaName());
                existingHotel.setAddress(hotelDTO.getAddress());
                existingHotel.setDescription(hotelDTO.getDescription());
                Optional<User> admin = userRepository.findById(hotelDTO.getOwner());
                admin.ifPresent(existingHotel::setOwner);

                hotelDTO.getAmenities().forEach(amenity ->
                        existingHotel.getIntermediateHotelAmenities()
                                .add(intermediateHotelAmenityRepository
                                        .save(new IntermediateHotelAmenity(existingHotel, amenity))));

                return hotelToHotelDTO.converter(hotelRepository.save(existingHotel));
            }
        }
    }


    /***
     * saves a hotel
     * @param hotelDTO hotel to be saved
     * @return the saved hotel for confirmation
     * @throws ApiRequestException if the hotel does not have an Owner or does not have rooms
     */
    public HotelDTO saveHotelDTO(HotelDTO hotelDTO) throws ApiRequestException {

        Optional<User> adminOpt =
                userRepository.findById(hotelDTO.getOwner());

        List<RoomDTO> roomDTOS = new ArrayList<>(hotelDTO.getRooms());

        if (adminOpt.isEmpty()) {
            throw new ApiRequestException("There is no Owner registered with that id, or the id is null!");
        }
        if (roomDTOS.isEmpty()) {
            throw new ApiRequestException("If you wont to save hotels you mast add one or more rooms");
        }

        if (hotelDTO.getAmenities().isEmpty()) {
            throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " there are no Amenities");
        }

        synchronized (this) {
            hotelDTO.getRooms().clear();
            Hotel hotel = hotelDTOToHotel.converter(hotelDTO);
            hotelRepository.save(hotel);

            roomDTOS.forEach(roomDTO -> roomDTO.setHotel(hotel.getId()));

            roomService.saveRooms(roomDTOS);

            hotelDTO.getAmenities().forEach(amenity ->
                    hotel.getIntermediateHotelAmenities()
                            .add(intermediateHotelAmenityRepository
                                    .save(new IntermediateHotelAmenity(hotel, amenity))));

            return hotelToHotelDTO.converter(hotelRepository.findById(hotel.getId()).get());
        }
    }

    /***
     * save a list of hotels
     * @param hotelsDTO list of hotels to be saved
     * @return the saved hotels for confirmation
     * @throws ApiRequestException if one of the hotels does not have an owner , the owner does not exist or does not have rooms
     */
    public List<HotelDTO> saveHotels(List<HotelDTO> hotelsDTO) throws ApiRequestException {
        List<Hotel> hotels = new ArrayList<>();

        for (HotelDTO hotelDTO : hotelsDTO) {

            List<RoomDTO> roomDTOS = new ArrayList<>(hotelDTO.getRooms());

            if (hotelDTO.getOwner() == null || userService.getUserById(hotelDTO.getOwner()) == null) {
                throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " There Owner does not exist or you have not add one");
            }

            if (roomDTOS.isEmpty()) {
                throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " there are no rooms");
            }

            if (hotelDTO.getAmenities().isEmpty()) {
                throw new ApiRequestException("In hotel with name: " + hotelDTO.getName() + " there are no Amenities");
            }

            synchronized (this) {

                hotelDTO.getRooms().clear();
                Hotel hotel = hotelDTOToHotel.converter(hotelDTO);
                hotelRepository.save(hotel);

                roomDTOS.forEach(roomDTO -> roomDTO.setHotel(hotel.getId()));

                roomService.saveRooms(roomDTOS);
                hotels.add(hotelRepository.findById(hotel.getId()).get());

                hotelDTO.getAmenities().forEach(amenity ->
                        hotel.getIntermediateHotelAmenities()
                                .add(intermediateHotelAmenityRepository
                                        .save(new IntermediateHotelAmenity(hotel, amenity))));
            }
        }

        List<HotelDTO> hotelDTOS = new ArrayList<>();

        hotels.forEach(hotel -> hotelDTOS.add(hotelToHotelDTO.converter(hotel)));

        return hotelDTOS;
    }


    /***
     * Retrieves a set of HotelAmenity for the hotel with the given ID
     * @param id The ID of the hotel for which to retrieve amenities
     * @return A set of HotelAmenity representing the amenities of the hotel with the given ID
     */
    public Set<HotelAmenity> getHotelAmenitiesByHotelId(Long id) throws ApiRequestException {

        if (hotelRepository.findById(id).isPresent()) {

            Set<HotelAmenity> hotelAmenities = new HashSet<>(hotelRepository.findAmenityByHotelId(id));

            if (!hotelAmenities.isEmpty()) {

                return hotelAmenities;

            } else {

                throw new ApiRequestException("The hotel has no Amenities whet");
            }
        } else {

            throw new ApiRequestException("The hotel with id " + id + " does not exist.");
        }
    }

    /***
     * returns the hotels that are available in specific dates in a location
     * or a hotel if it is available at that dates if the search made by the hotel name
     * @param basicSearchDTO basic search fields (check in date, check out date, location name or hotel name)
     * @return the hotels than mach with the search
     */
    public Set<HotelDTO> getHotelBasicSearch(BasicSearchDTO basicSearchDTO) {

        Set<HotelDTO> hotelDTOS = new HashSet<>();

        if (basicSearchDTO.getCheckInDate() != null && basicSearchDTO.getCheckOutDate() != null
                && basicSearchDTO.getNameOrLocation() != null) {

            basicSearchDTO.setNameOrLocation("%" + basicSearchDTO.getNameOrLocation() + "%");

            hotelRepository.findByBasicSearch(
                            basicSearchDTO.getCheckInDate(),
                            basicSearchDTO.getCheckOutDate(),
                            basicSearchDTO.getNameOrLocation())
                    .forEach(hotel -> hotelDTOS
                            .add(hotelToHotelDTO
                                    .converter(hotel)));

            if (!hotelDTOS.isEmpty()) {

                for (HotelDTO hotelDTO : hotelDTOS) {

                    Set<HotelAmenity> amenities = getHotelAmenitiesByHotelId(hotelDTO.getId());

                    if (!amenities.isEmpty()) {

                        hotelDTO.getAmenities().addAll(amenities);
                    }

                    Set<RoomDTO> roomsDto = hotelDTO.getRooms();

                    if (!roomsDto.isEmpty()) {


                        for (RoomDTO roomDTO : roomsDto) {

                            Set<RoomAmenity> RAmenities = roomService.getRoomAmenitiesByRoomId(roomDTO.getId());

                            if (!RAmenities.isEmpty()) {

                                roomDTO.getAmenities().addAll(RAmenities);
                            }
                        }
                    }
                }


                hotelDTOS.forEach(hotelDTO -> hotelDTO
                        .getRooms()
                        .forEach(roomDTO -> roomDTO.getAmenities()
                                .addAll(roomService.getRoomAmenitiesByRoomId(roomDTO.getId()))));
            }
            return hotelDTOS;

        } else {

            throw new RuntimeException("Something went wrong");
        }

    }

    /***
     * returns all hotel amenities
     * @return all hotel amenities
     */
    public Set<HotelAmenity> getHotelAmenities() throws ApiRequestException {

        Set<HotelAmenity> amenities = new HashSet<>();

        amenityHotelRepository.findAllEnabled().forEach(amenities::add);

        if (amenities.isEmpty()) {

            throw new ApiRequestException("There are no hotel amenities whet.");

        } else {
            return amenities;
        }
    }

    /**
     * Created by Akd
     * saves a new Hotel Amenity
     *
     * @param hotelAmenity to be saved
     * @return the saved hotel amenity for confirmation
     * @throws ApiRequestException if the hotel amenity is not created and does not be enabled
     */
    public HotelAmenity saveHotelAmenity(HotelAmenity hotelAmenity) throws ApiRequestException {

        if (hotelAmenity.gethAmenity().isEmpty()) {
            throw new ApiRequestException("There is no Hotel Amenity");
        }

        if (!hotelAmenity.getEnabled()) {
            throw new ApiRequestException("There is no activated Hotel Amenity");
        }

        return amenityHotelRepository.save(hotelAmenity);
    }


    public List<HotelDTO> advancedSearchMethod(List<HotelAmenity> hotelAmenities, List<RoomAmenity> roomAmenities, LocalDate checkInDate, LocalDate checkOutDate,
                                               Long priceFrom, Long priceTo, Integer adultsRange, Integer stars, String nameOrLocation, Integer pageNo, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.unsorted());

        Map<String, Object> parametrMap = new HashMap<>();

        StringBuilder query = new StringBuilder("select r.hotel from rooms r inner join Hotel h on r.hotel.id = h.id " +
                "inner join IntermediateHotelAmenity ih on h.id = ih.hotel.id " +
                "inner join HotelAmenity ha on ih.hotelAmenity.id = ha.id " +
                "inner join IntermediateRoomAmenity ir on r.id = ir.room.id " +
                "inner join RoomAmenity ra on ra.id = ir.roomAmenity.id " +
                "inner join orders o on o.room.id = r.id where h.disabled = false ");

        if (adultsRange != null) {

            query.append("and r.capacity = :adults ");

            parametrMap.put("adults", adultsRange);
        }

        if (priceFrom != null) {

            query.append("and r.price >= :priceFrom ");
            parametrMap.put("priceFrom", priceFrom);
        }

        if (priceTo != null) {

            query.append("and r.price <= :priceTo ");
            parametrMap.put("priceTo", priceTo);
        }

        if (stars != null) {

            query.append("and r.hotel.stars >= :stars ");
            parametrMap.put("stars", stars);
        }

        if (nameOrLocation != null) {

            query.append("and (r.hotel.name like :NameOrLocation or r.hotel.areaName like :NameOrLocation) ");
            parametrMap.put("NameOrLocation", "%" + nameOrLocation.toLowerCase() + "%");
        }

        if (checkInDate != null && checkOutDate != null) {

            query.append("and (((:checkIn < o.checkInDate) and (:checkOut < o.checkInDate)) or ((:checkIn > o.checkOutDate) and (:checkOut > o.checkOutDate))) ");
            parametrMap.put("checkOut", checkOutDate);
            parametrMap.put("checkIn", checkInDate);
        }


        for (int i = 0; i < hotelAmenities.size(); i++) {

            query.append("and ha.hAmenity = :hAmenity" + i + " ");
            parametrMap.put("hAmenity" + i, hotelAmenities.get(i).gethAmenity());
        }

        for (int i = 0; i < roomAmenities.size(); i++) {

            query.append("and ra.rAmenity = :rAmenity" + i + " ");
            parametrMap.put("rAmenity" + i, roomAmenities.get(i).getrAmenity());
        }

        query.append(" order by r.price");


        Query queryFinal = entityManager.createQuery(String.valueOf(query), Hotel.class);

        for (String key : parametrMap.keySet()) {

            queryFinal.setParameter(key, parametrMap.get(key));
        }

        List<Hotel> hotels = queryFinal.getResultList();

        Set<Hotel> hotels1 = new HashSet<>();

        hotels.forEach(hotels1::add);

        List<HotelDTO> hotelDTOSList = new ArrayList<>();

        hotels1.forEach(hotel -> hotelDTOSList.add(hotelToHotelDTO.converter(hotel)));

        Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOSList, paging, hotelDTOSList.size());

        if (hotelDTOPage.hasContent())
            return hotelDTOPage.getContent();

        return new ArrayList<>();
    }

    public Map<String, Integer> getStatistics(Long id, LocalDate date) {

        Map<String, Integer> statistic = new HashMap<>();

        statistic.put("all", hotelRepository.countAllRooms(id));
        statistic.put("vacant", hotelRepository.countAllRoomsVacant(id, date));

        return statistic;

    }

    /**
     * Created by AKd
     * enables Hotel Amenity by id
     * @param id of the hotel Amenity to be enabled
     * @return a boolean if the action is done or not
     * @throws ApiRequestException if the Hotel Amenity does not exist or is already enabled
     */

    public boolean enableHotelAmenity(Long id) throws ApiRequestException {

        Optional<HotelAmenity> hotelAmenityOptional = amenityHotelRepository.findById(id);

        if (hotelAmenityOptional.isEmpty()) {
            throw new ApiRequestException("There is no Hotel Amenity with id: " + id);
        } else if (hotelAmenityOptional.get().getEnabled()) {
            throw new ApiRequestException("The hotel amenity with id: " + id + " is already enabled");
        }else {
            HotelAmenity hotelAmenity = hotelAmenityOptional.get();
            hotelAmenity.setEnabled(true);
            amenityHotelRepository.save(hotelAmenity);
            return true;
        }
    }

    /**
    * Created by AKd
    * disables Hotel Amenity by id
    * @param id of the hotel Amenity to be disabled
    * @return a boolean if the action is done or not
    * @throws ApiRequestException if the Hotel Amenity does not exist or is already disabled
    */
    public boolean disableHotelAmenity(Long id) throws ApiRequestException{

        Optional<HotelAmenity> hotelAmenityOptional = amenityHotelRepository.findById(id);

        if(hotelAmenityOptional.isEmpty()){
            throw new ApiRequestException("There is not an amenity hotel with the id: " + id);
        }else if (!hotelAmenityOptional.get().getEnabled()){
            throw new ApiRequestException("The hotel amenity with id: " + id + " is disabled");
        }else{
            HotelAmenity hotelAmenity = hotelAmenityOptional.get();
            hotelAmenity.setEnabled(false);
            amenityHotelRepository.save(hotelAmenity);
            return true;
        }

    }
}
