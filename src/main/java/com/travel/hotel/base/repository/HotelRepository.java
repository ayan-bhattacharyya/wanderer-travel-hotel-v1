package com.travel.hotel.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.travel.hotel.base.domain.Status;
import com.travel.hotel.base.entity.Hotel;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelRepository extends CrudRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

	List<Hotel> findByCode(String code);
	List<Hotel> findByCodeAndStatus(String code, Status status);
	List<Hotel> findByNameAndStatus(String name, Status status);
}
