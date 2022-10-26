package com.sphy.hotelmanagementapplication.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class MapperUtil {

	private static ModelMapper modelMapper = new ModelMapper();

	private MapperUtil() {
	}

	public static <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass) {

		return source
				.stream()
				.map(element -> modelMapper.map(element, targetClass))
				.collect(Collectors.toSet());
	}

	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

		return source
				.stream()
				.map(element -> modelMapper.map(element, targetClass))
				.collect(Collectors.toList());
	}
}
