package com.sphy.hotelmanagementapplication.factory;

import com.sphy.hotelmanagementapplication.converter.BaseEntitySetConverter;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import com.sphy.hotelmanagementapplication.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReverseModelMapperFactory implements AbstractFactory{
    private final ModelMapper modelMapper;
    private final BaseEntitySetConverter baseEntitySetConverter;
    private final HotelRepository hotelRepository;


    public ReverseModelMapperFactory(ModelMapper modelMapper, BaseEntitySetConverter baseEntitySetConverter, HotelRepository hotelRepository) {
        this.modelMapper = modelMapper;
        this.baseEntitySetConverter = baseEntitySetConverter;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public ModelMapper create(ModelMapperFactory.ModelMapperType modelMapperType) {

        TypeMap<RoomDTO, Room> propertyMapper = null;

        switch (modelMapperType) {
            case ROOM:
               
                // Add a custom way to convert all Set<Object> to Set<Long>
                modelMapper.addConverter(baseEntitySetConverter);

                // Check if TypeMap exists, if not create
                if (modelMapper.getTypeMap(RoomDTO.class, Room.class) == null) {
                    propertyMapper =
                            modelMapper.createTypeMap(RoomDTO.class, Room.class);
                } else {
                    propertyMapper = modelMapper.getTypeMap(RoomDTO.class, Room.class);
                }

                // Custom mapping for Room, defining the way modelMapper maps the Hotel object to a Long
                propertyMapper.addMappings(
                        mapper -> mapper.map(src -> hotelRepository.findById(src.getHotel()).isPresent(), Room::setHotel)
                );

                return modelMapper;
        }

        return new ModelMapper();
    }

    public enum ModelMapperType {
        ROOM,HOTEL,ORDER
    }




}
