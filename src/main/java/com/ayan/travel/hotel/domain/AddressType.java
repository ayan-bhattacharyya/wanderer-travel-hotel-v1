package com.ayan.travel.hotel.domain;

public enum AddressType {
	H("Hotel Address"), C("Contact Address"), P("Potal Address");

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
		throw new RuntimeException("No Enum value exists for " + label);
	}

	public String getLabel() {
		return label;
	}

}
