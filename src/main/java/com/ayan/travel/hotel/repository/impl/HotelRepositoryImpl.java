package com.ayan.travel.hotel.repository.impl;

import java.util.List;
import java.util.Optional;

import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.repository.HotelRepository;

public class HotelRepositoryImpl implements HotelRepository {

	@Override
	public <S extends Hotel> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Hotel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Hotel> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Hotel entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Hotel> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Hotel> findByHotelCodeAndStatus(String hotelCode, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByHotelCode(String hotelCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByHotelNameAndStatus(String hotelName, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
