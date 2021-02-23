package com.travel.hotel.base.domain;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status attribute) {
		return attribute.getLabel();
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		return Status.findByLabel(dbData);
	}

}
