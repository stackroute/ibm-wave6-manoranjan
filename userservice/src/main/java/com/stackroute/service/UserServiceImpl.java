package com.stackroute.service;

import com.stackroute.domain.UserPayment;
import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.exceptions.UserNotFoundException;
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
            throw new UserAllReadyExistException();
        }
        User saveUser= (User) userRepository.save(user);
       if(saveUser==null)
        {
            throw new UserAllReadyExistException();
        }
       else {
           kafkaTemplate.send(topic,user);

       }

       return saveUser;
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
            return userRepository.findAll();
    }

    @Override
    public User deleteUser(String emailId) throws UserNotFoundException {
        User user = null;
        Optional optional = userRepository.findById(emailId);
        if (optional.isPresent()) {
            user = userRepository.findById(emailId).get();
            userRepository.deleteById(emailId);
        }
        else
            throw new UserNotFoundException();
        return user;
    }

    @Override
    public User updateUser(String emailId,User user) throws UserNotFoundException {
        User user1=new User();
        if (userRepository.existsById(user.getEmailId())) {
            user1=userRepository.findById(emailId).get();
            user1.setEmailId(user.getEmailId());
            user1.setName(user.getName());
            user1.setMobileNo(user.getMobileNo());
            user1.setAge(user.getAge());
        }
        if(user1==null)
            throw new UserNotFoundException();
        return userRepository.save(user1);
    }

    @Override
    public User getById(String emailId) throws UserNotFoundException {
        User user;
        Optional optional = userRepository.findById(emailId);
        if (optional.isPresent()) {
             user= userRepository.findById(emailId).get();
        }
        else
            throw new UserNotFoundException("track");
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
