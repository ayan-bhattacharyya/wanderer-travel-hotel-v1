package com.travel.hotel.base.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.travel.hotel.base.api.dto.SearchCriteriaDTO;
import com.travel.hotel.base.entity.Hotel;

public class HotelSpecification implements Specification<Hotel> {

	private static final long serialVersionUID = 1337353297962318367L;

	private SearchCriteriaDTO criteria;
	
	private HotelSpecification() {
		
	}

	public HotelSpecification(SearchCriteriaDTO criteria) {
		super();
		this.criteria = criteria;
	}



	@Override
	public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return criteria.predicate(root, criteria.getOperation(), 
				criteria.getKey(), criteria.getValue(), builder);
	}
}
