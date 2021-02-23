package com.travel.hotel.base.api.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchCriteriaDTO {
	
	private String key;
	private String operation;
	private Object value;
	
	public SearchCriteriaDTO(String key, String operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Predicate predicate(Root<?> root, String operation, String key, Object value, CriteriaBuilder builder) {
		if("eq".equalsIgnoreCase(operation)) {
			return builder.equal(root.<String> get(key), value.toString());
			
		} else if("neq".equalsIgnoreCase(operation)) {
			return builder.notEqual(root.<String> get(key), value.toString());
			
		} else if("gte".equalsIgnoreCase(operation)) {
			return builder.greaterThanOrEqualTo(root.<String> get(key), value.toString());
			
		} else if("lte".equalsIgnoreCase(operation)) {
			return builder.lessThanOrEqualTo(root.<String> get(key), value.toString());
			
		} else if("gt".equalsIgnoreCase(operation)) {
			return builder.greaterThan(root.<String> get(key), value.toString());
			
		} else if("lt".equalsIgnoreCase(operation)) {
			return builder.lessThan(root.<String> get(key), value.toString());
			
		} else if("like".equalsIgnoreCase(operation)) {
			if(root.get(key).getJavaType() == String.class) {
				return builder.like(root.<String> get(key), "%" + value +"%");
			} else {
				return builder.equal(root.<String> get(key), value.toString());
			}
		} else {
			throw new RuntimeException("Invalid operation");
		}
	}
	
	

}
