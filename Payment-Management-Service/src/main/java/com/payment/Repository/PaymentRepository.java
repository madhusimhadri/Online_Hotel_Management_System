package com.payment.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.PaymentData;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentData, String> {

	List<PaymentData> findByStatus(String status);
}
