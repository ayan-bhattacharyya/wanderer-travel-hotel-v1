package com.travel.hotel.base.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.RoomDTO;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.RoomType;
import com.travel.hotel.base.domain.Status;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.entity.Room;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.exception.ElementUpdatedException;
import com.travel.hotel.base.exception.InputMappingException;
import com.travel.hotel.base.repository.RoomRepository;

@Service
public class RoomCrudServiceImpl {
	
	private RoomRepository roomRepository;
	private HotelCrudServiceImpl hotelCrudServiceImpl;

	@Autowired
	public RoomCrudServiceImpl(RoomRepository roomRepository,
			HotelCrudServiceImpl hotelCrudServiceImpl) {
		this.roomRepository = roomRepository;
		this.hotelCrudServiceImpl = hotelCrudServiceImpl;
	}
	
	public Room createRoom(RoomDTO input, String hotelCode) {
		Hotel hotel = validateAndGetHotel(input, hotelCode);
		String status;
		
		if(input.getStatus() == null) {
			status = "Active";
		} else {
			status = input.getStatus();
		}
		
		if(getRoomByNumber(hotel, input.getNumber()) == null) {
			return roomRepository.save(new Room.RoomBuilder()
					.withHotel(hotel)
					.withName(input.getName())
					.withNumber(input.getNumber())
					.withType(input.getType())
					.withStatus(status)
					.build()
					);
		} else {
			throw new ElementExistsException(Constants.ROOM_EXISTS);
		}
		
	}
	
	public Room updateRoom(RoomDTO input, String hotelCode) {

		Hotel hotel = validateAndGetHotel(input, hotelCode);
		Room room = getRoomByNumber(hotel, input.getNumber());
		
		if(room != null) {
			if (StringUtils.isBlank(input.getReadDateTime()) && StringUtils.isNotBlank(room.getModifiedAt())) {
			throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);

			} else if (StringUtils.isBlank(room.getModifiedAt())
					|| input.getReadDateTime().equals(room.getModifiedAt())) {
				
				if(StringUtils.isNotBlank(input.getName())) {
					room.setName(input.getName());
				}
				if(StringUtils.isNotBlank(input.getType())) {
					room.setType(RoomType.findByLabel(input.getType()));
				}
				if(StringUtils.isNotBlank(input.getStatus())) {
					room.setStatus(Status.findByLabel(input.getStatus()));
				}
				room.setModifiedAt();
				room.setModifiedBy(input.getUser());
				return roomRepository.save(room);

			} else {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
			}
		} else {
			throw new NoSuchElementException(Constants.ROOM_NOT_EXISTS);
		}
	}
	
	public void deleteHotel(String hotelCode, String roomNumber) {
		Hotel hotel = hotelCrudServiceImpl.getHotelByCode(hotelCode);
		if (hotel== null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);
		}
		
		Room room = getRoomByNumber(hotel, roomNumber);
		
		if (room == null) {
			throw new NoSuchElementException(Constants.ROOM_NOT_EXISTS);

		} else {
			roomRepository.delete(room);
		}

	}
	
	
	private Room getRoomByNumber(Hotel hotel, String number) {
		List<Room> rooms = roomRepository.findByHotelAndNumber(hotel, number);
		
		if(rooms.size() == 0) {
			return null;
		} else if (rooms.size() > 1) {
			throw new RuntimeException("More than one Room exists for the hotel and number " + number);
		} else {
			return rooms.get(0);
		}
		
	}
	
	private Hotel validateAndGetHotel(RoomDTO input, String hotelCode) {
		if (StringUtils.isBlank(input.getNumber())) {
			throw new InputMappingException(Constants.ROOM_NUMBER_MISSING);

		} else if (StringUtils.isBlank(input.getType())) {
			throw new InputMappingException(Constants.ROOM_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getUser())) {
			throw new InputMappingException(Constants.USER_MISSING);

		} else if (hotelCrudServiceImpl.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);
		} else {
			return hotelCrudServiceImpl.getHotelByCode(hotelCode);
		}
	}

}
