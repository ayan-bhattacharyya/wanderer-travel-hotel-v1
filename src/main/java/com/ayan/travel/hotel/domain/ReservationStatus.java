package com.ayan.travel.hotel.domain;

import java.util.NoSuchElementException;

public enum ReservationStatus {
	P("Pending Payment"), B("Booked"),
	C("Cancelled"), R("Rejected");

	public final String label;

	private ReservationStatus(String label) {
		this.label = label;
	}

	public static ReservationStatus findByLabel(String label) {
		for (ReservationStatus st : ReservationStatus.values()) {
			if (st.label.equals(label)) {
				return st;
			}
		}
		throw new NoSuchElementException("No Enum value exists for " + label);
	}

	public String getLabel() {
		return label;
	}

}
