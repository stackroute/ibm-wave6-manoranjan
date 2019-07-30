package com.stackroute.service;

import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    //save user
    public User saveUser(User user) throws UserAllReadyExistException;
    //display list of all user
    public List<User> getAllUsers() throws UserNotFoundException;
    //delete particular user
    public User deleteUser(String emailId) throws UserNotFoundException;
    //update particular user
    public User updateUser(String emailId,User user) throws UserNotFoundException;
    //get user by their emailId
    public User getById(String emailId) throws UserNotFoundException;
}
