package com.ayan.travel.hotel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ayan.travel.hotel.domain.ReservationStatus;

@Entity
@Table(name = "room_reservation")
public class RoomReservation implements Serializable {

	private static final long serialVersionUID = -4055371429256019034L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_reservation_id")
	private Long reservationId;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "status", nullable = false)
	private ReservationStatus status;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "ext_reference")
	private String extReference;
	
	@Column(name = "agent")
	private String agent;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	public RoomReservation() {

	}

	public RoomReservation(Room room, ReservationStatus status, Date startDate, Date endDate, String email,
			String mobile, String extReference, String agent, String createdAt, String createdBy) {
		super();
		this.room = room;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.email = email;
		this.mobile = mobile;
		this.extReference = extReference;
		this.agent = agent;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExtReference() {
		return extReference;
	}

	public void setExtReference(String extReference) {
		this.extReference = extReference;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
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
		return "Room Reservation {" + ", Reservation Id ='" + reservationId + '\'' + ", Room ='"
				+ room + '\'' + ", User ='"+ user + '\'' + ", Status ='" + status + '\'' + ", Start Date ='"
				+ startDate + '\'' + ", End Date ='" + endDate + '\'' + ", Email ='"
				+ email + '\'' + ", Mobile ='" + mobile +'\'' + ", External Reference ="
				+ extReference + '\'' + ", Agent =" + agent +'\'' + ", Created at ='" 
				+ createdAt + '\'' + ", Created by ='" + createdBy + '\''
				+ ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RoomReservation reservation = (RoomReservation) o;
		return Objects.equals(reservationId, reservation.reservationId) && Objects.equals(room, reservation.room)
				&& Objects.equals(user, reservation.user) && Objects.equals(startDate, reservation.startDate)
				&& status == reservation.status && Objects.equals(endDate, reservation.endDate)
				&& Objects.equals(email, reservation.email) && Objects.equals(mobile, reservation.mobile)
				&& Objects.equals(extReference, reservation.extReference) && Objects.equals(agent, reservation.agent)
				&& Objects.equals(createdAt, reservation.createdAt) && Objects.equals(createdBy, reservation.createdBy) 
				&& Objects.equals(modifiedAt, reservation.modifiedAt) && Objects.equals(modifiedBy, reservation.modifiedBy);
	}

}
