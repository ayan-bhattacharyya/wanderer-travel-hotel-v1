package com.travel.hotel.base.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateTimeService {

	public String getDateTime(String dateFormat) {
		LocalDateTime localDateTime = LocalDateTime.now(TimeZone.getTimeZone("GMT").toZoneId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return localDateTime.format(formatter);
	}

}
