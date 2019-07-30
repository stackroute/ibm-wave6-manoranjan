package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.domain.UserPayment;

import java.util.List;

public interface UserPaymentService {
    public UserPayment saveUser(UserPayment userPackage) ;
    public List<UserPayment> getAllUsers();
    public UserPayment deleteUser(String emailId);
    public User saveUser(User user);
}
