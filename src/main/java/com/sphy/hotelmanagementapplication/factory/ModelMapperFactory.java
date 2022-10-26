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

		TypeMap<Room, RoomDTO> propertyMapper = null;

		switch (modelMapperType) {
			case ROOM:

				// Add a custom way to convert all Set<Object> to Set<Long>
				modelMapper.addConverter(baseEntitySetConverter);

				// Check if TypeMap exists, if not create
				if (modelMapper.getTypeMap(Room.class, RoomDTO.class) == null) {
					propertyMapper =
							modelMapper.createTypeMap(Room.class, RoomDTO.class);
				} else {
					propertyMapper = modelMapper.getTypeMap(Room.class, RoomDTO.class);
				}

				// Custom mapping for Room, defining the way modelMapper maps the Hotel object to a Long
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
