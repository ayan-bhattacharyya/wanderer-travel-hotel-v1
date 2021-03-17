package com.travel.hotel.base.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travel.hotel.base.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
	
	List<Payment> findByTransactionId(String transactionId);
	
	List<Payment> findByPaymentDate(Date paymentDate);
	
	

}
