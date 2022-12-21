package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.RoomAmenityToRoomAmenityDTO;
import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.RoomAmenity;
import com.sphy.hotelmanagementapplication.dto.RoomAmenityDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repository.HotelRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
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

    private final RoomAmenityToRoomAmenityDTO roomAmenityToRoomAmenityDTO;


	public RoomService(RoomRepository repository, HotelRepository hotelRepository, RoomDTOToRoom roomDTOToRoom, RoomToRoomDTO roomToRoomDTO, RoomAmenityToRoomAmenityDTO roomAmenityToRoomAmenityDTO) {
		this.roomRepository = repository;
		this.hotelRepository = hotelRepository;
        this.roomDTOToRoom = roomDTOToRoom;
        this.roomToRoomDTO = roomToRoomDTO;
        this.roomAmenityToRoomAmenityDTO = roomAmenityToRoomAmenityDTO;
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

        if (hotelOpt.isPresent()){
            return roomToRoomDTO.converter(roomRepository.save(room));
        }else{
            throw  new ApiRequestException("There is no hotel that room belongs");
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

		for(RoomDTO roomDto : roomsDTO) {
            if (roomDto.getHotel() == null) {
                throw new ApiRequestException(
                        " Room with name: " + roomDto.getName() + " has not have a hotel"
                );
            }else if (!hotelRepository.findById(roomDto.getHotel()).isPresent()){
                throw new ApiRequestException("hotel with id: " + roomDto.getHotel() + " does not exist");
            }else {
                 rooms.add(roomDTOToRoom.converter(roomDto));
            }
		}

        roomRepository.saveAll(rooms);

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room:rooms){
            roomDTOS.add(roomToRoomDTO.converter(roomRepository.findById(room.getId()).get()));
        }
        return roomDTOS;
    }

    /***
     * get all rooms
     * @return a list of all rooms
     * @throws ApiRequestException if no room is saved
     */
    public List<RoomDTO> getRooms() throws ApiRequestException {


            List<Room> rooms = new ArrayList<>();

            roomRepository.findAll().forEach(rooms::add);


            List<RoomDTO> roomsDTO = new ArrayList<>();

            for (Room room : rooms) {
                roomsDTO.add(roomToRoomDTO.converter(room));
            }
            return roomsDTO;

    }

    /***
     * find a room by his id
     * @param id of the room to be found
     * @return the room with the current id
     * @throws ApiRequestException if there is no room with the given id
     */
    public RoomDTO getRoomById(Long id) throws ApiRequestException {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent()){
            throw new ApiRequestException("There is now room with id: " + id);
        }else {
            return roomToRoomDTO.converter(roomRepository.findById(id).get());
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
        if (!room.isPresent()){
            throw new ApiRequestException("There is now room with name: " + name);
        }else {
            return roomToRoomDTO.converter(roomRepository.findByName(name).get());
        }
    }

    /***
     * enables a room
     * @param id of the room to be enabled
     * @return a boolean if  the room enabled or not
     * @throws ApiExceptionFront if the room does not exist or is already activated
     */
	public boolean enableRoom(Long id) throws ApiExceptionFront {
        if (!roomRepository.existsById(id)) {
            throw  new ApiExceptionFront("The room with id: " + id + " does not exist");
        }else if (!roomRepository.findById(id).get().isDisabled()){
            throw new ApiExceptionFront("The room with id: " + id + " is already activated");
        }else {
			Room room = roomRepository.findById(id).get();
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

        if (!roomRepository.existsById(id)) {
            throw new ApiExceptionFront("The room with id:" + id + " does not exist");
        }else if (roomRepository.findById(id).get().isDisabled() ){
            throw new ApiExceptionFront("The room with id: " + id + "is already deactivated");
        }else {
		   Room room = roomRepository.findById(id).get();
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
        if (room.isPresent()){
            Room existingRoom = roomRepository.findById(roomDTO.getId()).orElse(null);
            existingRoom.setName(roomDTO.getName());
            Optional<Hotel> hotel = hotelRepository.findById(roomDTO.getHotel());
            existingRoom.setLuxurity(roomDTO.getLuxurity());
            existingRoom.setPrice(roomDTO.getPrice());
            hotel.ifPresent(existingRoom::setHotel);
			existingRoom.setDisabled(roomDTO.isDisabled());

            hotel.get().getRooms().add(existingRoom);
            hotelRepository.save(hotel.get());

            return roomToRoomDTO.converter(roomRepository.save(existingRoom));
        }else{

            throw new ApiRequestException("The room with id: " + roomDTO.getId() + " does not exist");
        }
    }


    /***
     *
     * @param id
     * @return
     */
    public Set<RoomAmenityDTO> getRoomAmenitiesByRoomId(Long id) {

        Set<RoomAmenityDTO> amenitiesDTO = new HashSet<>();
        roomRepository.findByRoomID(id)
                .stream()
                .map(roomAmenity ->  amenitiesDTO.add(roomAmenityToRoomAmenityDTO.converter(roomAmenity)));

        return amenitiesDTO;
    }
}
