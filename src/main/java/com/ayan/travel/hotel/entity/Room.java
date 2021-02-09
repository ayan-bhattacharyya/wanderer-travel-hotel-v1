package com.ayan.travel.hotel.entity;

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

import com.ayan.travel.hotel.domain.RoomType;
import com.ayan.travel.hotel.domain.Status;

@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = { "hotel_id", "room_number" }))
public class Room implements Serializable {

	private static final long serialVersionUID = -4055371429256019034L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private Long roomId;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "room_number", nullable = false)
	private String roomNumber;

	@Column(name = "room_type", nullable = false)
	private RoomType roomType;

	@Column(name = "tariff", nullable = false)
	private double tariff;

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

	public Room() {

	}

	public Room(Hotel hotel, String roomName, String roomNumber, RoomType roomType, double tariff, Status status,
			String createdAt, String createdBy) {
		super();
		this.hotel = hotel;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.tariff = tariff;
		this.status = status;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public double getTariff() {
		return tariff;
	}

	public void setTariff(double tariff) {
		this.tariff = tariff;
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

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	@Override
	public String toString() {
		return "Hotel Room {" + ", Room Id ='" + roomId + '\'' + ", Room Name ='"
				+ roomName + '\'' + ", Room Number ='" + roomNumber + '\'' + ", Room Type ='"
				+ roomType + '\'' + ", Tariff ='" + tariff + '\'' + ", Status ='"
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
		return Objects.equals(roomId, room.roomId) && Objects.equals(hotel, room.hotel)
				&& Objects.equals(roomName, room.roomName) && Objects.equals(roomNumber, room.roomName)
				&& roomType == room.roomType && Objects.equals(tariff, room.tariff)
				&& status == room.status && Objects.equals(createdAt, room.createdAt)
				&& Objects.equals(createdBy, room.createdBy) && Objects.equals(modifiedAt, room.modifiedAt)
				&& Objects.equals(modifiedBy, room.modifiedBy);
	}

}
