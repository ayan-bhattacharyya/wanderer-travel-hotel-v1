package com.travel.hotel.base.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.PaymentMode;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "reservation_payment")
public class Payment implements Serializable{
	
	private static final long serialVersionUID = -4055371429256019034L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "payment_mode")
	private PaymentMode paymentMode;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	protected Payment() {}
	
	protected Payment(Reservation reservation, Date paymentDate, Double paymentAmount,
			PaymentMode paymentMode, String transactionId, String createdBy) {
		this.reservation = reservation;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.paymentMode = paymentMode;
		this.transactionId = transactionId;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getRoomReservation() {
		return reservation;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
		this.modifiedAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public static class ReservationPaymentBuilder {
		private Reservation reservation;
		private Date paymentDate;
		private Double paymentAmount;
		private String paymentMode;
		private String transactionId;
		private String createdBy;
		
		public ReservationPaymentBuilder withReservation(final Reservation reservation) {
			this.reservation = reservation;
			return this;
		}
		
		public ReservationPaymentBuilder withPaymentDate(final Date paymentDate) {
			this.paymentDate = paymentDate;
			return this;
		}
		
		public ReservationPaymentBuilder withPaymentAmount(final Double paymentAmount) {
			this.paymentAmount = paymentAmount;
			return this;
		}
		
		public ReservationPaymentBuilder withPaymentMode(final String paymentMode) {
			this.paymentMode = paymentMode;
			return this;
		}
		
		public ReservationPaymentBuilder withTransactionId(final String transactionId) {
			this.transactionId = transactionId;
			return this;
		}
		
		public ReservationPaymentBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}
		
		public Payment build() {
			return new Payment(reservation, paymentDate, paymentAmount, PaymentMode.findByLabel(paymentMode),
					transactionId, createdBy);
		}
		
		
	}
	
	
	
	

}
