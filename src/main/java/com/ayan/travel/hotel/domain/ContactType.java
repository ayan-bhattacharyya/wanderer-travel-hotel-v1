package com.ayan.travel.hotel.domain;

public enum ContactType {
	WEM("Work Email"), PEM("Personal Email"), WPH("Work Phone"),
	MPH("Mobile Phone"), FAX("Fascimile");
	
	public final String label;
	
	private ContactType(String label) {
		this.label = label;
	}
	
	public static ContactType valueOfLabel(String byLabel) {
		for (ContactType ct : ContactType.values()) {
			if(ct.label.equalsIgnoreCase(byLabel)) {
				return ct;
			}
		}
		throw new RuntimeException("No Enum value exists for " + byLabel);
	}

}
