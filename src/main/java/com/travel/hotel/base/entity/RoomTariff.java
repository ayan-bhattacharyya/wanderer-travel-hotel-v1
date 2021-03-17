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

import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.RoomType;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "room_tariff")
public class RoomTariff implements Serializable {

	private static final long serialVersionUID = -4055371429256019034L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "type", nullable = false)
	private RoomType type;

	@Column(name = "tariff", nullable = false)
	private Double tariff;

	@Column(name = "is_current", nullable = false)
	private Boolean isCurrent;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	protected RoomTariff() {
	}

	protected RoomTariff(RoomType type, Double tariff, Boolean isCurrent, String createdBy) {
		this.type = type;
		this.tariff = tariff;
		this.isCurrent = isCurrent;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public RoomType getType() {
		return type;
	}

	public Double getTariff() {
		return tariff;
	}

	public void setTariff(Double tariff) {
		this.tariff = tariff;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt() {
		this.modifiedAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public static class RoomTariffBuilder {
		private String type;
		private Double tariff;
		private Boolean isCurrent;
		private String createdBy;

		public RoomTariffBuilder withType(final String type) {
			this.type = type;
			return this;
		}

		public RoomTariffBuilder withTariff(final Double tariff) {
			this.tariff = tariff;
			return this;
		}
		
		public RoomTariffBuilder withCurrent(final Boolean isCurrent) {
			this.isCurrent = isCurrent;
			return this;
		}

		public RoomTariffBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public RoomTariff build() {
			return new RoomTariff(RoomType.findByLabel(type), tariff, isCurrent, createdBy);
		}

	}

	@Override
	public String toString() {
		return "Room Tariff {" + ", Room Type ='" + type + '\'' + ", Room Tariff ='" + tariff + '\'' + ", Current ='"
				+ isCurrent + '\'' + ", Created at ='"
				+ createdAt + '\'' + ", Created by ='" + createdBy + '\'' + ", Modified at ='" + modifiedAt + '\''
				+ ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RoomTariff roomTariff = (RoomTariff) o;
		return Objects.equals(id, roomTariff.id) && Objects.equals(tariff, roomTariff.tariff) 
				&& Objects.equals(isCurrent, roomTariff.isCurrent) && type == roomTariff.type
				&& Objects.equals(createdAt, roomTariff.createdAt) && Objects.equals(createdBy, roomTariff.createdBy)
				&& Objects.equals(modifiedAt, roomTariff.modifiedAt) && Objects.equals(modifiedBy, roomTariff.modifiedBy);
	}

}
