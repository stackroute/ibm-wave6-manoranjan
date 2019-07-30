package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws UserAlreadyExistsException; //Added new viewer

    List<User> getAllUsers() throws UserNotFoundException;  // get all viewer

    User findByEmailIdAndPassword(String emailId, String password); //find viewer by id and password
}
