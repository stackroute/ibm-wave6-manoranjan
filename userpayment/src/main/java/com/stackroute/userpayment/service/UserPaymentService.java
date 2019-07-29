package com.stackroute.userpayment.service;

import com.stackroute.userpayment.domain.UserPayment;

import java.util.List;

public interface UserPaymentService {
    public UserPayment saveUser(UserPayment userPackage) ;
    public List<UserPayment> getAllUsers();
    public UserPayment deleteUser(String emailId);
}
