package com.sphy.hotelmanagementapplication.factory;

import com.sphy.hotelmanagementapplication.converter.BaseEntitySetConverter;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.springframework.stereotype.Component;

@Component
public class ModelMapperFactory implements AbstractFactory<ModelMapper> {

	private final ModelMapper modelMapper;
	private final BaseEntitySetConverter baseEntitySetConverter;

	public ModelMapperFactory(ModelMapper modelMapper, BaseEntitySetConverter baseEntitySetConverter) {
		this.modelMapper = modelMapper;
		this.baseEntitySetConverter = baseEntitySetConverter;
	}

	@Override
	public ModelMapper create(ModelMapperType modelMapperType) {
		switch (modelMapperType) {
			case ROOM:
				modelMapper.addConverter(baseEntitySetConverter);

				TypeMap<Room, RoomDTO> propertyMapper =
						modelMapper.createTypeMap(Room.class, RoomDTO.class);

				propertyMapper.addMappings(
						mapper -> mapper.map(src -> src.getHotel().getId(), RoomDTO::setHotel)
				);

				return modelMapper;
		}

		return new ModelMapper();
	}

	public enum ModelMapperType {
		ROOM,HOTEL,ORDER
	}




}
