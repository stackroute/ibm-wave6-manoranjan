package com.stackroute.userpayment.service;

import com.stackroute.userpayment.domain.UserPayment;
import com.stackroute.userpayment.repository.UserPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    UserPayment userPackage=null;
    UserPaymentRepository userPackageRepository;
    @Autowired
    public UserPaymentServiceImpl(UserPaymentRepository userPackageRepository)
    {
        this.userPackageRepository=userPackageRepository;
    }





    @Override
    public UserPayment saveUser(UserPayment userPackage)  {

            UserPayment saveUser = (UserPayment) userPackageRepository.save(userPackage);

        System.out.println(saveUser);
            return saveUser;


    }

    @Override
    public List<UserPayment> getAllUsers() {

        return userPackageRepository.findAll();
    }
    @Override
    public UserPayment deleteUser(String emailId) {
        userPackage= null;
        Optional optional = userPackageRepository.findById(emailId);
        if (optional.isPresent()) {
            userPackage = userPackageRepository.findById(emailId).get();
            userPackageRepository.deleteById(emailId);
        }
        return userPackage;
    }
}
