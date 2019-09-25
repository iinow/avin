package com.avin.common.modelmapper;

public interface DomainMapper {
	public <D,E> D convertToDomain(E source,Class<? extends D> classLiteral);
}
