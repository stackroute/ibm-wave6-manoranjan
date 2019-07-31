package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private User user;

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.userRepo = repo;
    }


    @Override
    public User findByEmailIdAndPassword(String emailId, String password) {
        return userRepo.findByEmailIdAndPassword(emailId, password);
    }


    @Override
    @KafkaListener(topics = "saveUser", groupId = "Group_JsonObject")
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepo.existsByEmailId(user.getEmailId())) {
            throw new UserAlreadyExistsException();
        }
        else
            return userRepo.save(user);
    }


    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
        return userRepo.findAll();
    }

}