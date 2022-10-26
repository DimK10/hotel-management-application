package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.BaseEntity;
import com.sphy.hotelmanagementapplication.domain.Order;
import org.modelmapper.AbstractConverter;

import org.springframework.stereotype.Component;

@Component
public class BaseEntityConverter extends AbstractConverter<BaseEntity, Long> {
	@Override
	protected Long convert(BaseEntity baseEntity) {
		return baseEntity.getId();
	}
}
