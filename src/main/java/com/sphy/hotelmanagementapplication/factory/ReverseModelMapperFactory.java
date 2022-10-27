package com.sphy.hotelmanagementapplication.factory;

import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import com.sphy.hotelmanagementapplication.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ReverseModelMapperFactory implements AbstractFactory{
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
	private final OrderRepository orderRepository;


    public ReverseModelMapperFactory(ModelMapper modelMapper, HotelRepository hotelRepository, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.hotelRepository = hotelRepository;
		this.orderRepository = orderRepository;
	}

    @Override
    public ModelMapper create(ModelMapperFactory.ModelMapperType modelMapperType) {

        TypeMap<RoomDTO, Room> propertyMapper = null;

        switch (modelMapperType) {
            case ROOM:
               

                // Check if TypeMap exists, if not create
                if (modelMapper.getTypeMap(RoomDTO.class, Room.class) == null) {
                    propertyMapper =
                            modelMapper.createTypeMap(RoomDTO.class, Room.class);
                } else {
                    propertyMapper = modelMapper.getTypeMap(RoomDTO.class, Room.class);
                }

                // Custom mapping for Room, defining the way modelMapper maps the Hotel object to a Long
                propertyMapper.addMappings(
						mapper -> mapper.map(src -> {
							// Check if the source has an id, else return null
							// this prevents findById below to throw IllegalArgumentException
							// if id == null
							if (src.getHotel() == null) {
								return null;
							}

							return hotelRepository.findById(src.getHotel()).orElse(null);
						}, Room::setHotel)
                );

                return modelMapper;
        }

        return new ModelMapper();
    }

    public enum ModelMapperType {
        ROOM,HOTEL,ORDER
    }




}
