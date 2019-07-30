package com.stackroute.service;

import com.stackroute.domain.UserPayment;
import com.stackroute.repository.UserPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    UserPayment userPayment=null;
    UserPaymentRepository userPackageRepository;
    @Autowired
    public UserPaymentServiceImpl(UserPaymentRepository userPackageRepository)
    {
        this.userPackageRepository=userPackageRepository;
    }
    @Override
    public UserPayment saveUser(UserPayment userPayment)  {
        UserPayment saveUser = (UserPayment) userPackageRepository.save(userPayment);
        System.out.println(saveUser);
            return saveUser;
    }
    @Override
    public List<UserPayment> getAllUsers() {
        return userPackageRepository.findAll();
    }
}
