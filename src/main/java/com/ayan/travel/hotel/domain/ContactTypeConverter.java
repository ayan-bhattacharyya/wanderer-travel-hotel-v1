package com.ayan.travel.hotel.domain;

import javax.persistence.AttributeConverter;

public class ContactTypeConverter implements AttributeConverter<ContactType, String> {

	@Override
	public String convertToDatabaseColumn(ContactType attribute) {
		return attribute.getLabel();
	}

	@Override
	public ContactType convertToEntityAttribute(String dbData) {
		return ContactType.findByLabel(dbData);
	}

}
