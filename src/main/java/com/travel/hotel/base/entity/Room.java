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
import javax.persistence.UniqueConstraint;

import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.RoomType;
import com.travel.hotel.base.domain.Status;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = { "hotel_id", "number" }))
public class Room implements Serializable {

	private static final long serialVersionUID = -4055371429256019034L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@Column(name = "name")
	private String name;

	@Column(name = "number", nullable = false)
	private String number;

	@Column(name = "type", nullable = false)
	private RoomType type;

	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	protected Room() {}

	protected Room(Hotel hotel, String name, String number, RoomType type, Status status,
			String createdBy) {
		this.hotel = hotel;
		this.name = name;
		this.number = number;
		this.type = type;
		this.status = status;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public static class RoomBuilder {
		private Hotel hotel;
		private String name;
		private String number;
		private String type;
		private String status;
		private String createdBy;

		public RoomBuilder withHotel(final Hotel hotel) {
			this.hotel = hotel;
			return this;
		}

		public RoomBuilder withName(final String name) {
			this.name = name;
			return this;
		}

		public RoomBuilder withNumber(final String number) {
			this.number = number;
			return this;
		}

		public RoomBuilder withType(final String type) {
			this.type = type;
			return this;
		}

		public RoomBuilder withStatus(final String status) {
			this.status = status;
			return this;
		}

		public RoomBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Room build() {
			return new Room(hotel, name, number, RoomType.findByLabel(type), Status.findByLabel(status),
					createdBy);
		}

	}

	@Override
	public String toString() {
		return "Hotel Room {" + ", Room Id ='" + id + '\'' + ", Room Name ='" + name + '\'' + ", Room Number ='"
				+ number + '\'' + ", Room Type ='" + type + '\'' + ", Status ='"
				+ status + '\'' + ", Created at ='" + createdAt + '\'' + ", Created by ='" + createdBy + '\''
				+ ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Room room = (Room) o;
		return Objects.equals(id, room.id) && Objects.equals(hotel, room.hotel) && Objects.equals(name, room.name)
				&& Objects.equals(number, room.number) && type == room.type
				&& status == room.status && Objects.equals(createdAt, room.createdAt)
				&& Objects.equals(createdBy, room.createdBy) && Objects.equals(modifiedAt, room.modifiedAt)
				&& Objects.equals(modifiedBy, room.modifiedBy);
	}

}
