package com.travel.hotel.base.domain;

import java.util.NoSuchElementException;

public enum RoomType {
	SBD("Single Bed"), DBD("Double Bed"), DOM("Dormitory"), SUITE("Suite"), ESUITE("Executive Suite");

	public final String label;

	private RoomType(String label) {
		this.label = label;
	}

	public static RoomType findByLabel(String label) {
		for (RoomType rt : RoomType.values()) {
			if (rt.label.equalsIgnoreCase(label)) {
				return rt;
			}
		}
		throw new NoSuchElementException("No Enum value exists for " + label);
	}
	
	public String getLabel() {
		return label;
	}

}
