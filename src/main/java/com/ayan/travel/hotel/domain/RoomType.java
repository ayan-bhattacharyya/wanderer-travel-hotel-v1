package com.ayan.travel.hotel.domain;

public enum RoomType {
	SBD("Single Bed"), DBD("Double Bed"), DOM("Dormitory"),
	SUITE("Suite"), ESUITE("Executive Suite");
	
	public final String label;
	
	private RoomType(String label) {
		this.label = label;
	}
	
	public static RoomType valueOfLabel(String byLabel) {
		for (RoomType rt : RoomType.values()) {
			if(rt.label.equalsIgnoreCase(byLabel)) {
				return rt;
			}
		}
		throw new RuntimeException("No Enum value exists for " + byLabel);
	}

}
