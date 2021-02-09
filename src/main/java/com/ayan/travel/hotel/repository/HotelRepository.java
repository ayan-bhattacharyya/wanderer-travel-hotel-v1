package com.ayan.travel.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelRepository extends CrudRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

	List<Hotel> findByHotelCodeAndStatus(String hotelCode, Status status);

	List<Hotel> findByHotelCode(String hotelCode);

	List<Hotel> findByHotelNameAndStatus(String hotelName, Status status);
	
	static Specification<Hotel> byParameters(ArrayList<String> parameters){
		
		return new Specification<Hotel>() {
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				builder.equal(hotel)
			}
		};
	}

}
