package com.sphy.hotelmanagementapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory.ModelMapperType;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

@Service
public class RoomService {


    private final RoomRepository repository;

	private final HotelRepository hotelRepository;

	private final ModelMapperFactory modelMapperFactory;

	public RoomService(RoomRepository repository, HotelRepository hotelRepository, ModelMapperFactory modelMapperFactory) {
		this.repository = repository;
		this.hotelRepository = hotelRepository;
		this.modelMapperFactory = modelMapperFactory;
	}

	public RoomDTO saveRoomDTO(RoomDTO roomDTO) {
		Room room = new Room();

		// find hotel from db by its id
		Optional<Hotel> hotelOpt =
				hotelRepository.findById(roomDTO.getHotel());

		room.setName(roomDTO.getName());
		room.setLuxurity(roomDTO.getLuxurity());
		room.setPrice(roomDTO.getPrice());

		hotelOpt.ifPresent(room::setHotel);

		// Get ModelMapper object specifically set up for converting Room to RoomDTO
		ModelMapper modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);

		// The save method returns a Room object, which is then passed to map method of
		// modelmapper and a RoomDTO is returned instead
		return modelMapper.map(repository.save(room), RoomDTO.class);
	}

    public Room saveRoom(Room room){
       return repository.save(room);
    }

    public List<Room> saveRooms(List<Room> rooms){
        return (List<Room>) repository.saveAll(rooms);
    }

    public List<Room> getRooms(){
		List<Room> rooms = new ArrayList<>();
        repository.findAll().forEach(rooms::add);
		return rooms;
    }

    public Room getRoomById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Room getRoomByName(String name){
        return repository.findByName(name);
    }

    public String deleteRoom(Long id){
        repository.deleteById(id);
        return "Room with id" + id + "has be successfully removed";
    }

    public Room updateRoom(Room room){
        Room existingRoom = repository.findById(room.getId()).orElse(null);
        existingRoom.setName(room.getName());
        existingRoom.setHotel(room.getHotel());
        existingRoom.setLuxurity(room.getLuxurity());
        existingRoom.setPrice(room.getPrice());
        return repository.save(existingRoom);
    }




}
