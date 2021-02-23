package com.travel.hotel.base.domain;

import javax.persistence.AttributeConverter;

public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {

	@Override
	public String convertToDatabaseColumn(ReservationStatus attribute) {
		return attribute.getLabel();
	}

	@Override
	public ReservationStatus convertToEntityAttribute(String dbData) {
		return ReservationStatus.findByLabel(dbData);
	}

}

