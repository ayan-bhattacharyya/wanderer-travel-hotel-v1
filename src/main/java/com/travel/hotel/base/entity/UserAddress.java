package com.travel.hotel.base.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.travel.hotel.base.domain.AddressType;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {

	private static final long serialVersionUID = 1740869414999752409L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private ApplicationUser applicationUser;

	@Column(name = "type", nullable = false)
	private AddressType type;

	@Column(name = "address_line_1", nullable = false)
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "address_line_3")
	private String addressLine3;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "postcode", nullable = false)
	private String postcode;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	protected UserAddress() {}
	
	protected UserAddress(ApplicationUser applicationUser, AddressType type, String addressLine1, String addressLine2,
			String addressLine3, String city, String state, String postcode, String country, String createdBy) {
		this.applicationUser = applicationUser;
		this.type = type;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.country = country;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public ApplicationUser getUser() {
		return applicationUser;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt() {
		this.modifiedAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public static class UserAddressBuilder {
		private ApplicationUser user;
		private String type;
		private String addressLine1;
		private String addressLine2;
		private String addressLine3;
		private String city;
		private String state;
		private String postcode;
		private String country;
		private String createdBy;

		public UserAddressBuilder withHotel(final ApplicationUser user) {
			this.user = user;
			return this;
		}

		public UserAddressBuilder withType(final String type) {
			this.type = type;
			return this;
		}

		public UserAddressBuilder withAddressLine1(final String addressLine1) {
			this.addressLine1 = addressLine1;
			return this;
		}

		public UserAddressBuilder withAddressLine2(final String addressLine2) {
			this.addressLine2 = addressLine2;
			return this;
		}

		public UserAddressBuilder withAddressLine3(final String addressLine3) {
			this.addressLine3 = addressLine3;
			return this;
		}

		public UserAddressBuilder withCity(final String city) {
			this.city = city;
			return this;
		}

		public UserAddressBuilder withState(final String state) {
			this.state = state;
			return this;
		}

		public UserAddressBuilder withPostcode(final String postcode) {
			this.postcode = postcode;
			return this;
		}

		public UserAddressBuilder withCountry(final String country) {
			this.country = country;
			return this;
		}

		public UserAddressBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public UserAddress build() {
			return new UserAddress(user, AddressType.findByLabel(type), addressLine1, addressLine2, addressLine3, city,
					state, postcode, country, createdBy);
		}
	}

	@Override
	public String toString() {
		return "ApplicationUser Address {" + ", User Address Id ='" + id + '\'' + ", User Address Type ='" + type + '\''
				+ ", User Address Line 1 ='" + addressLine1 + '\'' + ", User Address Line 2 ='" + addressLine2 + '\''
				+ ", User Address Line 3 ='" + addressLine3 + '\'' + ", city ='" + city + '\'' + ", State ='" + state
				+ '\'' + ", Postcode ='" + postcode + '\'' + ", Country ='" + country + '\'' + ", Created at ='"
				+ createdAt + '\'' + ", Created by ='" + createdBy + '\'' + ", Modified at ='" + modifiedAt + '\''
				+ ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserAddress hotelAddress = (UserAddress) o;
		return Objects.equals(id, hotelAddress.id) && Objects.equals(applicationUser, hotelAddress.applicationUser)
				&& type == hotelAddress.type && Objects.equals(addressLine1, hotelAddress.addressLine1)
				&& Objects.equals(addressLine2, hotelAddress.addressLine2)
				&& Objects.equals(addressLine3, hotelAddress.addressLine3) && Objects.equals(city, hotelAddress.city)
				&& Objects.equals(state, hotelAddress.state) && Objects.equals(postcode, hotelAddress.postcode)
				&& Objects.equals(country, hotelAddress.country) && Objects.equals(createdAt, hotelAddress.createdAt)
				&& Objects.equals(createdBy, hotelAddress.createdBy)
				&& Objects.equals(modifiedAt, hotelAddress.modifiedAt)
				&& Objects.equals(modifiedBy, hotelAddress.modifiedBy);
	}

}
