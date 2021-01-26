package com.ayan.travel.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ayan.travel.hotel.domain.RoomType;
import com.ayan.travel.hotel.domain.Status;

@Entity
@Table(name= "room")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "hotel_id")
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
	
	
	

}
