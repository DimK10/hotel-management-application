package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repository.AmenityRoomRepository;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.IntermediateRoomAmenityRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

/***
 * created by gp
 */
@Service
public class RoomService {


    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    private final RoomDTOToRoom roomDTOToRoom;

    private final RoomToRoomDTO roomToRoomDTO;

    private final IntermediateRoomAmenityRepository intermediateRoomAmenityRepository;

    private final AmenityRoomRepository amenityRoomRepository;



    public RoomService(RoomRepository repository, HotelRepository hotelRepository, RoomDTOToRoom roomDTOToRoom, RoomToRoomDTO roomToRoomDTO, IntermediateRoomAmenityRepository intermediateRoomAmenityRepository, AmenityRoomRepository amenityRoomRepository) {
        this.roomRepository = repository;
        this.hotelRepository = hotelRepository;
        this.roomDTOToRoom = roomDTOToRoom;
        this.roomToRoomDTO = roomToRoomDTO;
        this.intermediateRoomAmenityRepository = intermediateRoomAmenityRepository;
        this.amenityRoomRepository = amenityRoomRepository;
    }

    /***
     * save a room
     * @param roomDTO the room to be saved
     * @return the saved room for confirmation
     * @throws ApiRequestException if there is no hotel added or the hotel is ni=ot exists
     */
    public RoomDTO saveRoomDTO(RoomDTO roomDTO) throws ApiRequestException {

        Room room = roomDTOToRoom.converter(roomDTO);

        Optional<Hotel> hotelOpt =
                hotelRepository.findById(roomDTO.getHotel());

        if (hotelOpt.isPresent()) {

            if (!roomDTO.getAmenities().isEmpty()) {

                roomRepository.save(room);

                roomDTO.getAmenities().forEach(roomAmenity ->
                        room.getIntermediateRoomAmenities()
                                .add(intermediateRoomAmenityRepository
                                        .save(new IntermediateRoomAmenity(room, roomAmenity))));

                return roomToRoomDTO.converter(room);
            } else {

                throw new ApiRequestException("There are no Amenities on the room");
            }

        } else {

            throw new ApiRequestException("There is no hotel that room belongs");
        }
    }

    /***
     * save a list of rooms
     * @param roomsDTO the rooms to be saved
     * @return the saved rooms for confirmation
     * @throws ApiRequestException if the rooms that are going to save does not have a hotel or the hotel does not exist
     */
    public List<RoomDTO> saveRooms(List<RoomDTO> roomsDTO) throws ApiRequestException {
        List<Room> rooms = new ArrayList<>();

        for (RoomDTO roomDto : roomsDTO) {

            Optional<Hotel> hotelOptional =
                    hotelRepository.findById(roomDto.getHotel());

            if (roomDto.getHotel() == null) {
                throw new ApiRequestException(
                        " Room with name: " + roomDto.getName() + " has not have a hotel"
                );
            } else if (hotelOptional.isEmpty()) {

                throw new ApiRequestException("hotel with id: " + roomDto.getHotel() + " does not exist");

            } else if (roomDto.getAmenities().isEmpty()) {

                throw new ApiRequestException("The room does not have amenities");

            }else {
                Room room = roomDTOToRoom.converter(roomDto);

                roomRepository.save(room);

                roomDto.getAmenities().forEach(roomAmenity ->
                        room.getIntermediateRoomAmenities()
                                .add(intermediateRoomAmenityRepository
                                        .save(new IntermediateRoomAmenity(room, roomAmenity))));



                rooms.add(room);
            }
        }


        List<RoomDTO> roomDTOS = new ArrayList<>();

        rooms.forEach(room -> roomsDTO.add(roomToRoomDTO.converter(room)));

        return roomDTOS;
    }

    /***
     * counts all the rooms in the database
     * @return the number of rooms that exists in the database
     */
    public int countRooms(Long id) {

        return roomRepository.countAll(id);
    }


    /***
     * get all rooms
     * @param pageNo what page do you wont
     * @param pageSize how much rooms per page
     * @param sortBy the page sorted by id, name etc
     * @param id the id of the hotel owner
     * @return the rooms of the owner
     * @throws ApiRequestException if anything wrong happens
     */
    public List<RoomDTO> getRooms(Integer pageNo, Integer pageSize, String sortBy, Long id) throws ApiRequestException {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Room> pageResult = roomRepository.findAllRoomsByOwner(id, paging);

        List<RoomDTO> roomDTOS = new ArrayList<>();

        pageResult.getContent().forEach(room -> roomDTOS.add(roomToRoomDTO.converter(room)));

        Page<RoomDTO> roomDTOPage = new PageImpl<>(roomDTOS, paging, roomDTOS.size());

        if (roomDTOPage.isEmpty()) {

            return new ArrayList<>();

        } else {

            return roomDTOPage.getContent();
        }
    }

    /***
     * find a room by his id
     * @param id of the room to be found
     * @return the room with the current id
     * @throws ApiRequestException if there is no room with the given id
     */
    public RoomDTO getRoomById(Long id) throws ApiRequestException {

        Optional<Room> room = roomRepository.findById(id);

        if (room.isEmpty()) {

            throw new ApiRequestException("There is now room with id: " + id);

        } else {

            return roomToRoomDTO.converter(room.get());
        }
    }


    /***
     * get a room by his name
     * @param name of the room to be found
     * @return the room with the current name
     * @throws ApiRequestException if there is no room with the given name
     */
    public RoomDTO getRoomByName(String name) throws ApiRequestException {

        Optional<Room> room = roomRepository.findByName(name);

        if (room.isEmpty()) {

            throw new ApiRequestException("There is now room with name: " + name);

        } else {

            return roomToRoomDTO.converter(room.get());
        }
    }

    /***
     * enables a room
     * @param id of the room to be enabled
     * @return a boolean if  the room enabled or not
     * @throws ApiExceptionFront if the room does not exist or is already activated
     */
    public boolean enableRoom(Long id) throws ApiExceptionFront {

        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {

            throw new ApiExceptionFront("The room with id: " + id + " does not exist");

        } else if (!roomOptional.get().isDisabled()) {

            throw new ApiExceptionFront("The room with id: " + id + " is already activated");

        } else {

            Room room = roomOptional.get();

            room.setDisabled(false);

            roomRepository.save(room);

            return true;
        }
    }

    /***
     * disable a room by his id
     * @param id of the room to be disabled
     * @return a boolean if the room disabled or not
     * @throws ApiExceptionFront if the room does not exist or is already deactivated
     */
    public boolean disableRoom(Long id) throws ApiExceptionFront {

        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {

            throw new ApiExceptionFront("The room with id:" + id + " does not exist");

        } else if (roomOptional.get().isDisabled()) {

            throw new ApiExceptionFront("The room with id: " + id + "is already deactivated");

        } else {

            Room room = roomOptional.get();

            room.setDisabled(true);

            roomRepository.save(room);

            return true;
        }
    }

    /***
     * updates a room
     * @param roomDTO room to be updated
     * @return the updated room for confirmation
     * @throws ApiRequestException if the room that is going to update is not exists
     */
    public RoomDTO updateRoom(RoomDTO roomDTO) throws ApiRequestException {

        Optional<Room> room = roomRepository.findById(roomDTO.getId());

        if (room.isPresent()) {

            Room existingRoom = room.get();
            existingRoom.setName(roomDTO.getName());
            Optional<Hotel> hotel = hotelRepository.findById(roomDTO.getHotel());
            existingRoom.setLuxurity(roomDTO.getLuxurity());
            existingRoom.setPrice(roomDTO.getPrice());
            hotel.ifPresent(existingRoom::setHotel);
            existingRoom.setDisabled(roomDTO.isDisabled());

            roomDTO.getAmenities().forEach(roomAmenity ->
                    existingRoom.getIntermediateRoomAmenities()
                            .add(intermediateRoomAmenityRepository
                                    .save(new IntermediateRoomAmenity(existingRoom, roomAmenity))));

            return roomToRoomDTO.converter(roomRepository.save(existingRoom));
        } else {

            throw new ApiRequestException("The room with id: " + roomDTO.getId() + " does not exist");
        }
    }


    /***
     * Retrieves a set of RoomAmenity for the room with the given ID
     * @param id The ID of the room for which to retrieve amenities
     * @return A set of RoomAmenity representing the amenities of the room with the given ID
     */
    public Set<RoomAmenity> getRoomAmenitiesByRoomId(Long id) {

        Set<RoomAmenity> amenitiesRoomDTO;

        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isPresent()) {

            amenitiesRoomDTO = new HashSet<>(roomRepository.findAmenitiesByRoomId(id));
        }else {

            throw new ApiRequestException("The room does not have any amenities whet");
        }

        return amenitiesRoomDTO;
    }

    /***
     * returns all room amenities
     * @return all room amenities
     */
    public Set<RoomAmenity> getRoomAmenities() throws ApiRequestException{

        Set<RoomAmenity> amenities = new HashSet<>();

        amenityRoomRepository.findAll().forEach(amenities::add);

        if (amenities.isEmpty()){

            throw new ApiRequestException("There are no room amenities whet.");

        }else {
            return amenities;
        }
    }
}
