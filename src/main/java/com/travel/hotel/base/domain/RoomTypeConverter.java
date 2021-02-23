package com.travel.hotel.base.domain;

import javax.persistence.AttributeConverter;

public class RoomTypeConverter implements AttributeConverter<RoomType, String> {

	@Override
	public String convertToDatabaseColumn(RoomType attribute) {
		return attribute.getLabel();
	}

	@Override
	public RoomType convertToEntityAttribute(String dbData) {
		return RoomType.findByLabel(dbData);
	}

}

