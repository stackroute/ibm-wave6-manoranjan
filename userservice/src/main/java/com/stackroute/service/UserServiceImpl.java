package com.stackroute.service;

import com.stackroute.domain.UserPayment;
import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.repository.UserPaymentRepository;
import com.stackroute.repository.UserRepository;
import com.stackroute.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    User user=null;
    UserRepository userRepository;

    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public UserServiceImpl(UserPaymentRepository userPaymentRepository)
    {
        this.userPaymentRepository=userPaymentRepository;
    }

    @Autowired
    KafkaTemplate<User,User> kafkaTemplate;

    private static String topic= "saveUser";

    @Override
    public User saveUser(User user) throws UserAllReadyExistException {
        if(userRepository.existsById(user.getEmailId()))
        {
            throw new UserAllReadyExistException("User already exist!!!!");
        }
        User saveUser= (User) userRepository.save(user);
        if(saveUser==null)
        {
            throw new UserAllReadyExistException("User already exist!!!");
        }
        kafkaTemplate.send(topic,saveUser);
        return saveUser;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User deleteUser(String emailId) {
        user = null;
        Optional optional = userRepository.findById(emailId);
        if (optional.isPresent()) {
            user = userRepository.findById(emailId).get();
            userRepository.deleteById(emailId);
        }
        return user;
    }

    @Override
    public User updateUser(String emailId,User user) {
        User user1=new User();
        if (userRepository.existsById(user.getEmailId())) {
            user1=userRepository.findById(emailId).get();
            user1.setEmailId(user.getEmailId());
            user1.setName(user.getName());
            user1.setMobileNo(user.getMobileNo());
            user1.setAge(user.getAge());
        }
        return userRepository.save(user1);
    }

    @Override
    public User getById(String emailId) {
        user = null;
        Optional optional = userRepository.findById(emailId);
        if (optional.isPresent()) {
             user= userRepository.findById(emailId).get();
        }
        return user;
    }

    @Override
    @KafkaListener(topics = "savedUser",groupId = "Group_JsonObject")
    public UserPayment saveUser(UserPayment userPayment) {
        UserPayment saveUser = (UserPayment) userPaymentRepository.save(userPayment);

        System.out.println(saveUser);
        return saveUser;

    }

}
