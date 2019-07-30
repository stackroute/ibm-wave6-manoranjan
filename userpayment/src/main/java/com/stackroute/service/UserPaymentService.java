package com.stackroute.service;

import com.stackroute.domain.UserPayment;

import java.util.List;

public interface UserPaymentService {
    public UserPayment saveUser(UserPayment userPayment) ;
    public List<UserPayment> getAllUsers();

}
