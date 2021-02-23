package com.travel.hotel.base.domain;

import java.util.NoSuchElementException;

public enum AddressType {
	H("Home Address"), C("Contact Address"), P("Potal Address"),
	B("Biiling Address"), O("Office Address") ;

	public final String label;

	private AddressType(String label) {
		this.label = label;
	}

	public static AddressType findByLabel(String label) {
		for (AddressType at : AddressType.values()) {
			if (at.label.equalsIgnoreCase(label)) {
				return at;
			}
		}
		throw new NoSuchElementException("No Enum value exists for " + label);
	}

	public String getLabel() {
		return label;
	}

}
