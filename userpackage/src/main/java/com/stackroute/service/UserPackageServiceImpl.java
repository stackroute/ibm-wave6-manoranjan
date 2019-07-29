package com.stackroute.service;

import com.stackroute.domain.UserPackage;
import com.stackroute.repository.UserPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPackageServiceImpl implements UserPackageService {
    UserPackage userPackage=null;
    UserPackageRepository userPackageRepository;
    @Autowired
    public UserPackageServiceImpl(UserPackageRepository userPackageRepository)
    {
        this.userPackageRepository=userPackageRepository;
    }

    @Autowired
    KafkaTemplate<UserPackage,UserPackage> kafkaTemplate;

    private static String topic= "saveUser";

    @Override
    public UserPackage saveUser(UserPackage userPackage)  {

            UserPackage saveUser = (UserPackage) userPackageRepository.save(userPackage);

        System.out.println(saveUser);
        kafkaTemplate.send(topic,saveUser);
        return saveUser;


    }

    @Override
    public List<UserPackage> getAllUsers() {

        return userPackageRepository.findAll();
    }
    @Override
    public UserPackage deleteUser(String emailId) {
        userPackage= null;
        Optional optional = userPackageRepository.findById(emailId);
        if (optional.isPresent()) {
            userPackage = userPackageRepository.findById(emailId).get();
            userPackageRepository.deleteById(emailId);
        }
        return userPackage;
    }
}
