package com.avin.common.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainMapperImpl implements DomainMapper{

	private ModelMapper modelMapper;

	public DomainMapperImpl(@Autowired ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	/**
	 * <D> = Domain Object
	 * <E> = Entity Object
	 * 
	 * @return Entity To Domain
	 * */
	@Override
	public <D, E> D convertToDomain(E source, Class<? extends D> classLiteral) {
		return modelMapper.map(source, classLiteral);
	}
}
