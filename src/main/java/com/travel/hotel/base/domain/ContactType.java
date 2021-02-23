package com.travel.hotel.base.domain;

import java.util.NoSuchElementException;

public enum ContactType {
	WEM("Work Email"), PEM("Personal Email"), WPH("Work Phone"), MPH("Mobile Phone"), FAX("Fascimile");

	public final String label;

	private ContactType(String label) {
		this.label = label;
	}

	public static ContactType findByLabel(String label) {
		for (ContactType ct : ContactType.values()) {
			if (ct.label.equalsIgnoreCase(label)) {
				return ct;
			}
		}
		throw new NoSuchElementException("No Enum value exists for " + label);
	}

	public String getLabel() {
		return label;
	}

}
