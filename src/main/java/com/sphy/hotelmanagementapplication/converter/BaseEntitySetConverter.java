package com.sphy.hotelmanagementapplication.converter;

import java.util.Set;
import java.util.stream.Collectors;

import com.sphy.hotelmanagementapplication.domain.BaseEntity;
import org.modelmapper.AbstractConverter;

import org.springframework.stereotype.Component;

@Component
public class BaseEntitySetConverter extends AbstractConverter<Set<BaseEntity>, Set<Long>> {
	@Override
	protected Set<Long> convert(Set<BaseEntity> baseEntities) {
		return baseEntities
				.stream()
				.map(BaseEntity::getId)
				.collect(Collectors.toSet());
	}
}
