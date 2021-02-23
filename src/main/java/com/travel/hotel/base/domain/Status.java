package com.travel.hotel.base.domain;

import java.util.NoSuchElementException;

public enum Status {
	A("Active"), I("Inactive");

	public final String label;

	private Status(String label) {
		this.label = label;
	}

	public static Status findByLabel(String label) {
		for (Status st : Status.values()) {
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
