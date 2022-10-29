package com.sphy.hotelmanagementapplication.factory;

import com.sphy.hotelmanagementapplication.converter.BaseEntitySetConverter;
import com.sphy.hotelmanagementapplication.domain.Hotel;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.dto.HotelDTO;
import com.sphy.hotelmanagementapplication.dto.RoomDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;

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
		TypeMap<Hotel, HotelDTO> propertyMapperHotel=null;
		TypeMap<List<Hotel>,List<Long>> propertyMapperHotelList=null;

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
				System.out.println();

				return modelMapper;

			case HOTEL:

				modelMapper.addConverter(baseEntitySetConverter);

				if (modelMapper.getTypeMap(Hotel.class, HotelDTO.class) == null) {
					propertyMapperHotel =
							modelMapper.createTypeMap(Hotel.class, HotelDTO.class);
				} else {
					propertyMapperHotel = modelMapper.getTypeMap(Hotel.class, HotelDTO.class);
				}

				propertyMapperHotel.addMappings(
						mapper -> mapper.map(src -> src.getOwner().getId(), HotelDTO::setOwner)
				);
				System.out.println();

				return modelMapper;
		}

		return null;
	}

	public enum ModelMapperType {
		ROOM,HOTEL,ORDER
	}




}
