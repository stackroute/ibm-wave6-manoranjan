package com.stackroute.userpayment.repository;

import com.stackroute.userpayment.domain.UserPayment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPaymentRepository extends MongoRepository<UserPayment,String> {
}
