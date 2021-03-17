package com.travel.hotel.base.domain;

import javax.persistence.AttributeConverter;

public class PaymentModeConverter implements AttributeConverter<PaymentMode, String> {

	@Override
	public String convertToDatabaseColumn(PaymentMode attribute) {
		return attribute.getLabel();
	}

	@Override
	public PaymentMode convertToEntityAttribute(String dbData) {
		return PaymentMode.findByLabel(dbData);
	}
}
