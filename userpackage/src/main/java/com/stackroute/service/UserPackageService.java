package com.stackroute.service;

import com.stackroute.domain.UserPackage;

import java.util.List;

public interface UserPackageService {
    public UserPackage saveUser(UserPackage userPackage) ;
    public List<UserPackage> getAllUsers();
    public UserPackage deleteUser(String emailId);
}
