package com.payment.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.StripeData;

@Repository
public interface StripeRepository extends MongoRepository<StripeData, String> {

}
