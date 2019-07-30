package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.domain.UserPayment;
import com.stackroute.repository.UserPaymentRepository;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    UserPayment userPackage=null;
    UserPaymentRepository userPaymentRepository;
    @Autowired
    UserRepository userRepository;

    public UserPaymentServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Autowired
    public UserPaymentServiceImpl(UserPaymentRepository userPaymentRepository)
    {
        this.userPaymentRepository=userPaymentRepository;
    }

    @Autowired
    KafkaTemplate<UserPayment,UserPayment> kafkaTemplate;

    private static String topic= "savedUser";

    @Override
    public UserPayment saveUser(UserPayment userPackage)  {

            UserPayment saveUser = (UserPayment) userPaymentRepository.save(userPackage);

        System.out.println(saveUser);
        kafkaTemplate.send(topic,saveUser);
        return saveUser;


    }

    @Override
    public List<UserPayment> getAllUsers() {

        return userPaymentRepository.findAll();
    }
    @Override
    public UserPayment deleteUser(String emailId) {
        userPackage= null;
        Optional optional = userPaymentRepository.findById(emailId);
        if (optional.isPresent()) {
            userPackage = userPaymentRepository.findById(emailId).get();
            userPaymentRepository.deleteById(emailId);
        }
        return userPackage;
    }

    @Override
    @KafkaListener(topics = "saveUser",groupId = "Group_JsonObject1")
    public User saveUser(User user) {
        User saveUser = (User) userRepository.save(user);
        System.out.println(saveUser);
        return saveUser;
    }
}
