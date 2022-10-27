package com.sphy.hotelmanagementapplication.factory;

import com.sphy.hotelmanagementapplication.factory.ModelMapperFactory.ModelMapperType;

public interface AbstractFactory <T>{
	T create(ModelMapperType modelMapperType);
}
