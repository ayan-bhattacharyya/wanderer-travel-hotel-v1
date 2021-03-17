package com.travel.hotel.base.domain;

import java.util.NoSuchElementException;

public enum PaymentMode {
	
	CSH("Cash"), CHK("Cheque"), BNK("Bank Transfer");

	public final String label;

	private PaymentMode(String label) {
		this.label = label;
	}

	public static PaymentMode findByLabel(String label) {
		for (PaymentMode ct : PaymentMode.values()) {
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
