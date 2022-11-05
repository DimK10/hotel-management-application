package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.HotelToHotelDTO;
import com.sphy.hotelmanagementapplication.converter.RoomDTOToRoom;
import com.sphy.hotelmanagementapplication.converter.RoomToRoomDTO;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {


    private final RoomRepository roomRepository;

	private final HotelRepository hotelRepository;

    private final RoomDTOToRoom roomDTOToRoom;

    private final RoomToRoomDTO roomToRoomDTO;


	public RoomService(RoomRepository repository, HotelRepository hotelRepository, RoomDTOToRoom roomDTOToRoom, RoomToRoomDTO roomToRoomDTO) {
		this.roomRepository = repository;
		this.hotelRepository = hotelRepository;
        this.roomDTOToRoom = roomDTOToRoom;
        this.roomToRoomDTO = roomToRoomDTO;
    }

    /***
     * save a room
     * @param roomDTO the room to be saved
     * @return the saved room for confirmation
     * @throws Exception
     */
	public RoomDTO saveRoomDTO(RoomDTO roomDTO) throws Exception {
		Room room = new Room();

		Optional<Hotel> hotelOpt =
				hotelRepository.findById(roomDTO.getHotel());

		room = roomDTOToRoom.converter(roomDTO);

        if (hotelOpt.isPresent()){
            hotelOpt.get().getRooms().add(room);
            hotelRepository.save(hotelOpt.get());
        }else{
            throw new Exception("The Room can't be saved without a hotel");
        }

		return roomToRoomDTO.converter(room);
	}

    /***
     * save a list of rooms
     * @param roomsDTO the rooms to be saved
     * @return the saved rooms for confirmation
     * @throws Exception
     */
    public List<RoomDTO> saveRooms(List<RoomDTO> roomsDTO) throws Exception{
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO roomDTO : roomsDTO){

        }
		for(RoomDTO roomDto : roomsDTO) {
			if (roomDto.getHotel() == null) {
				throw new Exception(
						"One of the rooms provided does not have a hotel (room.getHotel == null)."
								+ " Room with name: " + roomDto.getName() + " has no hotel"
				);
			}else {
                rooms.add(roomDTOToRoom.converter(roomDto));
            }
		}

        Iterable<Room> roomsSaved = roomRepository.saveAll(rooms);

        roomsSaved.spliterator().forEachRemaining(rooms::add);

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room:rooms){
            roomDTOS.add(roomToRoomDTO.converter(room));
        }


        return roomDTOS;
    }

    /***
     * get all rooms
     * @return a list of all rooms
     * @throws Exception
     */
    public List<RoomDTO> getRooms() throws Exception {
		List<Room> rooms = new ArrayList<>();
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        for (Room room : rooms){
            roomsDTO.add(roomToRoomDTO.converter(room));
        }
		return roomsDTO;
    }

    /***
     * find a room by his id
     * @param id of the room to be found
     * @return the room with the current id
     * @throws Exception
     */
    public RoomDTO getRoomById(Long id) throws Exception {
        Optional<Room> roomOpt = roomRepository.findById(id);

        if (roomOpt.isPresent()){
            return roomToRoomDTO.converter(roomOpt.get());
        }else return null;
    }


    /***
     * get a room by his name
     * @param name of the room to be found
     * @return the room with the current name
     * @throws Exception
     */
    public RoomDTO getRoomByName(String name) throws Exception {
        Optional<Room> roomOpt = roomRepository.findByName(name);
        if (roomOpt.isPresent()){
            return roomToRoomDTO.converter(roomOpt.get());
        }else return null;
    }

    /***
     * enables a room
     * @param id of the room to be enabled
     * @return a boolean if  the room enabled or not
     */
	public boolean enableRoom(Long id){
		if (roomRepository.existsById(id)){
			Room room = roomRepository.findById(id).get();
			room.setDisabled(false);
			roomRepository.save(room);
			return true;
		}else return false;

	}

    /***
     * disable a room by his id
     * @param id of the room to be disabled
     * @return a boolean if the room disabled or not
     */
    public boolean disableRoom(Long id){
       if (roomRepository.existsById(id)){
		   Room room = roomRepository.findById(id).get();
		   room.setDisabled(true);
           roomRepository.save(room);
           return true;
       }else return false;

    }

    /***
     * updates a room
     * @param roomDTO room to be updated
     * @return the updated room for confirmation
     * @throws NullPointerException
     */
    public RoomDTO updateRoom(RoomDTO roomDTO) throws NullPointerException{
        Optional<Room> roomOpt = roomRepository.findById(roomDTO.getId());

        if (roomOpt.isPresent()){
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

        }
        return null;
    }




}
