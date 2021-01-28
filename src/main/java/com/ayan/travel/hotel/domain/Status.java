package com.ayan.travel.hotel.domain;

public enum Status {
	A("Active"), I("Inactive");
	
	public final String label;
	
	private Status(String label) {
		this.label = label;
	}
	
	public static Status valueOfLabel(String byLabel) {
		for (Status st : Status.values()) {
			if(st.label.equals(byLabel)) {
				return st;
			}
		}
		throw new RuntimeException("No Enum value exists for " + byLabel);
	}

}
