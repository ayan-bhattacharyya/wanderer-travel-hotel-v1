package com.ayan.travel.hotel.domain;

import javax.persistence.AttributeConverter;

public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

	@Override
	public String convertToDatabaseColumn(AddressType attribute) {
		return attribute.getLabel();
	}

	@Override
	public AddressType convertToEntityAttribute(String dbData) {
		return AddressType.findByLabel(dbData);
	}

}