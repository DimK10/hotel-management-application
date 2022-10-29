package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory;
import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory.ModelMapperType;
import com.sphy.hotelmanagementapplication.factory.ReverseModelMapperFactory;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {


    private final RoomRepository roomRepository;

	private final HotelRepository hotelRepository;

	private final ModelMapperFactory modelMapperFactory;

    private final ReverseModelMapperFactory reverseModelMapperFactory;

	public RoomService(RoomRepository repository, HotelRepository hotelRepository, ModelMapperFactory modelMapperFactory, ReverseModelMapperFactory reverseModelMapperFactory) {
		this.roomRepository = repository;
		this.hotelRepository = hotelRepository;
		this.modelMapperFactory = modelMapperFactory;
        this.reverseModelMapperFactory = reverseModelMapperFactory;
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

        if (hotelOpt.isPresent()){
            hotelOpt.get().getRooms().add(room);
            hotelRepository.save(hotelOpt.get());
        }
		// Get ModelMapper object specifically set up for converting Room to RoomDTO
		ModelMapper modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);

		// The save method returns a Room object, which is then passed to map method of
		// modelMapper and a RoomDTO is returned instead
		return modelMapper.map(roomRepository.save(room), RoomDTO.class);
	}

    public List<Room> saveRooms(List<RoomDTO> roomsDTO) throws Exception{
        List<Room> rooms = new ArrayList<>();
        ModelMapper modelMapper = reverseModelMapperFactory.create(ModelMapperType.ROOM);

		rooms = roomsDTO
				.stream()
				.map(roomDTO -> modelMapper.map(roomDTO, Room.class))
				.collect(Collectors.toList());

		for(Room room : rooms) {
			if (room.getHotel() == null) {
				throw new Exception(
						"One of the rooms provided does not have a hotel (room.getHotel == null)."
								+ " Room with id: " + room.getId() + " has no hotel"
				);
			}

            room.getHotel().getRooms().add(room);
		}

        Iterable<Room> roomsSaved = roomRepository.saveAll(rooms);

        roomsSaved.spliterator().forEachRemaining(rooms::add);


		rooms.clear();



        return rooms;
    }

    public List<Room> getRooms(){
		List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
		return rooms;
    }

    public RoomDTO getRoomById(Long id){
        Optional<Room> roomOpt = roomRepository.findById(id);

        if (roomOpt.isPresent()){
            ModelMapper modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);
            return modelMapper.map(roomOpt.get(), RoomDTO.class);
        }else return null;
    }


    public RoomDTO getRoomByName(String name){
       Room roomOpt = roomRepository.findByName(name);

        if (roomOpt != null){
            ModelMapper modelMapper = modelMapperFactory.create(ModelMapperType.ROOM);
            return modelMapper.map(roomOpt, RoomDTO.class);
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
            roomRepository.save(existingRoom);
            hotel.get().getRooms().add(existingRoom);
            hotelRepository.save(hotel.get());
        }
        return roomDTO;
    }




}
