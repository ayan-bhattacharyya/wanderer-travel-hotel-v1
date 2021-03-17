package com.travel.hotel.base.entity;

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

import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.ReservationStatus;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "room_reservation")
public class Reservation implements Serializable {

	private static final long serialVersionUID = -4055371429256019034L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@OneToOne
	@JoinColumn(name = "user_id")
	private ApplicationUser user;

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
	
	@Column(name = "booking_fare")
	private Double bookingFare;
	
	@Column(name = "booking_amount")
	private Double bookingAmount;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	protected Reservation() {}
	
	protected Reservation(Room room, ApplicationUser user, ReservationStatus status, Date startDate, Date endDate,
			String email, String mobile, String extReference, String agent, Double bookingFare, Double bookingAmount,
			String createdBy) {
		super();
		this.room = room;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.email = email;
		this.mobile = mobile;
		this.extReference = extReference;
		this.agent = agent;
		this.bookingFare = bookingFare;
		this.bookingAmount = bookingAmount;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public Room getRoom() {
		return room;
	}

	public ApplicationUser getUser() {
		return user;
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

	public Double getBookingFare() {
		return bookingFare;
	}

	public void setBookingFare(Double bookingFare) {
		this.bookingFare = bookingFare;
	}

	public Double getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(Double bookingAmount) {
		this.bookingAmount = bookingAmount;
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

	public static class RoomReservationBuilder {
		private Room room;
		private ApplicationUser user;
		private String status;
		private Date startDate;
		private Date endDate;
		private String email;
		private String mobile;
		private String extReference;
		private String agent;
		private Double bookingFare;
		private Double bookingAmount;
		private String createdBy;

		public RoomReservationBuilder withRoom(final Room room) {
			this.room = room;
			return this;
		}

		public RoomReservationBuilder withUser(final ApplicationUser user) {
			this.user = user;
			return this;
		}

		public RoomReservationBuilder withStatus(final String status) {
			this.status = status;
			return this;
		}

		public RoomReservationBuilder withStartDate(final Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public RoomReservationBuilder withEndDate(final Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public RoomReservationBuilder withEmail(final String email) {
			this.email = email;
			return this;
		}

		public RoomReservationBuilder withMobile(final String mobile) {
			this.mobile = mobile;
			return this;
		}

		public RoomReservationBuilder withExternalReference(final String extReference) {
			this.extReference = extReference;
			return this;
		}

		public RoomReservationBuilder withAgent(final String agent) {
			this.agent = agent;
			return this;
		}
		
		public RoomReservationBuilder withBookingFare(final Double bookingFare) {
			this.bookingFare = bookingFare;
			return this;
		}
		
		public RoomReservationBuilder withBookingAmount(final Double bookingAmount) {
			this.bookingAmount = bookingAmount;
			return this;
		}

		public RoomReservationBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Reservation build() {
			return new Reservation(room, user, ReservationStatus.findByLabel(status), startDate, endDate, email,
					mobile, extReference, agent, bookingFare, bookingAmount, createdBy);

		}

	}

	@Override
	public String toString() {
		return "Room Reservation {" + ", Reservation Id ='" + id + '\'' + ", Room ='" + room + '\''
				+ ", ApplicationUser ='" + user + '\'' + ", Status ='" + status + '\'' + ", Start Date ='" + startDate
				+ '\'' + ", End Date ='" + endDate + '\'' + ", Email ='" + email + '\'' + ", Mobile ='" + mobile + '\''
				+ ", External Reference =" + extReference + '\'' + ", Agent =" + agent + '\'' + ", Created at ='"
				+ createdAt + '\'' + ", Created by ='" + createdBy + '\'' + ", Modified at ='" + modifiedAt + '\''
				+ ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Reservation reservation = (Reservation) o;
		return Objects.equals(id, reservation.id) && Objects.equals(room, reservation.room)
				&& Objects.equals(user, reservation.user) && Objects.equals(startDate, reservation.startDate)
				&& status == reservation.status && Objects.equals(endDate, reservation.endDate)
				&& Objects.equals(email, reservation.email) && Objects.equals(mobile, reservation.mobile)
				&& Objects.equals(extReference, reservation.extReference) && Objects.equals(agent, reservation.agent)
				&& Objects.equals(createdAt, reservation.createdAt) && Objects.equals(createdBy, reservation.createdBy)
				&& Objects.equals(modifiedAt, reservation.modifiedAt)
				&& Objects.equals(modifiedBy, reservation.modifiedBy);
	}

}
