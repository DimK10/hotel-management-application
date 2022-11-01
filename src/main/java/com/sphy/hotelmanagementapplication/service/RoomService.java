package com.sphy.hotelmanagementapplication.service;

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

	public RoomDTO saveRoomDTO(RoomDTO roomDTO) {
		Room room = new Room();

		// find hotel from db by its id
		Optional<Hotel> hotelOpt =
				hotelRepository.findById(roomDTO.getHotel());

		room = roomDTOToRoom.converter(roomDTO);

		roomRepository.save(room);

		return roomDTO;
	}

    public List<RoomDTO> saveRooms(List<RoomDTO> roomsDTO) throws Exception{
        List<Room> rooms = new ArrayList<>();

        for (RoomDTO roomDTO : roomsDTO){
            rooms.add(roomDTOToRoom.converter(roomDTO));
        }

		for(Room room : rooms) {
			if (room.getHotel() == null) {
				throw new Exception(
						"One of the rooms provided does not have a hotel (room.getHotel == null)."
								+ " Room with id: " + room.getId() + " has no hotel"
				);
			}else {
                room.getHotel().getRooms().add(room);
            }
		}

        Iterable<Room> roomsSaved = roomRepository.saveAll(rooms);

        roomsSaved.spliterator().forEachRemaining(rooms::add);

		rooms.clear();

        return roomsDTO;
    }

    public List<RoomDTO> getRooms(){
		List<Room> rooms = new ArrayList<>();
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        for (Room room : rooms){
            roomsDTO.add(roomToRoomDTO.converter(room));
        }
		return roomsDTO;
    }

    public RoomDTO getRoomById(Long id){
        Optional<Room> roomOpt = roomRepository.findById(id);

        if (roomOpt.isPresent()){
            return roomToRoomDTO.converter(roomOpt.get());
        }else return null;
    }


    public RoomDTO getRoomByName(String name){
        Optional<Room> roomOpt = roomRepository.findByName(name);
        if (roomOpt.isPresent()){
            return roomToRoomDTO.converter(roomOpt.get());
        }else return null;
    }

	public boolean enableRoom(Long id){
		if (roomRepository.existsById(id)){
			Room room = roomRepository.findById(id).get();
			room.setDisabled(false);
			roomRepository.save(room);
			return true;
		}else return false;

	}

    public boolean disableRoom(Long id){
       if (roomRepository.existsById(id)){
		   Room room = roomRepository.findById(id).get();
		   room.setDisabled(true);
           roomRepository.save(room);
           return true;
       }else return false;

    }

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
            roomRepository.save(existingRoom);
            hotel.get().getRooms().add(existingRoom);
            hotelRepository.save(hotel.get());
        }
        return roomDTO;
    }




}
