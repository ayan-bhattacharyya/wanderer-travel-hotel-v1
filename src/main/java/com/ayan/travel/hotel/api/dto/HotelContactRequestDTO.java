package com.ayan.travel.hotel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelContactRequestDTO {
	
	@JsonProperty("ContactType")
	private String contactType;

	@JsonProperty("ContactValue")
	private String contactValue;

	@JsonProperty("ModifiedDateTime")
	private String modifiedAt;

	@JsonProperty("ResponsibleUser")
	private String responsibleUser;
	
	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getResponsibleUser() {
		return responsibleUser;
	}

	public void setResponsibleUser(String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}


}
