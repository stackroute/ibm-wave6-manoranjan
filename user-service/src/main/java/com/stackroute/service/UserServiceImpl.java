package com.stackroute.service;

import com.stackroute.domain.UserPayment;
import com.stackroute.exceptions.DataAlreadyExistException;
import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserPaymentRepository;
import com.stackroute.repository.UserRepository;
import com.stackroute.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Autowired
    KafkaTemplate<User, User> kafkaTemplate;

    private static String topic = "saveUser";

    //to handle delay
    public void simulateDelay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //adding new user
    @CacheEvict(allEntries = true)
    @Override
    public User saveUser(User user) throws UserAllReadyExistException {
        if (userRepository.existsById(user.getEmailId())) {
            throw new UserAllReadyExistException();
        }
        User saveUser = userRepository.save(user);
        if (saveUser == null) {
            throw new UserAllReadyExistException();

        } else {
            kafkaTemplate.send(topic, user);
        }

        return saveUser;
    }

    //fetching all the registered users
    @Cacheable
    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    //delete user by email
    @CacheEvict(allEntries = true)
    @Override
    public User deleteUser(String emailId) throws UserNotFoundException {
        User user = null;
        Optional optional = userRepository.findById(emailId);
        if (optional.isPresent()) {
            user = (User) optional.get();
            userRepository.deleteById(emailId);
        } else
            throw new UserNotFoundException();
        return user;
    }

    //updating user details by email
    @CacheEvict(allEntries = true)
    @Override
    public User updateUser(String emailId, User user) throws UserNotFoundException {
        User user1 = new User();
        if (userRepository.existsById(user.getEmailId())) {
            user1 = userRepository.findById(emailId).get();
            user1.setEmailId(user.getEmailId());
            user1.setName(user.getName());
            user1.setMobileNo(user.getMobileNo());
            user1.setAge(user.getAge());
            userRepository.save(user1);
        }
        else
            throw new UserNotFoundException();
        return userRepository.save(user1);
    }

    //fetching user by email id
    @Cacheable
    @Override
    public User getById(String emailId) throws UserNotFoundException {
        User user;
        Optional optional = userRepository.findById(emailId);
        if (optional.isPresent()) {
            user = userRepository.findById(emailId).get();

        } else
            throw new UserNotFoundException("track");
        return user;
    }

    //getting all the wishlist elements by emailid
    @Cacheable
    @Override
    public List<List<String>> getAllWishlist(String emailId) throws UserNotFoundException {
        List<List<String>> wish;
        User user = userRepository.findById(emailId).get();
        wish = user.getWishList();
        if (wish == null) {
            throw new UserNotFoundException();
        }
        return wish;
    }

    //fetching the history by emailId
    @Cacheable
    @Override
    public List<List<String>> getAllHistory(String emailId) throws UserNotFoundException {
        List<List<String>> history;
        User user = userRepository.findById(emailId).get();
        history = user.getHistory();
        if (history == null) {
            throw new UserNotFoundException();
        }
        return history;
    }

    //getting userpayment details from payment-service
    @CacheEvict(allEntries = true)
    @Override
    public List<String> addToWishlish(String emailId, String title, String category) throws UserNotFoundException, DataAlreadyExistException {
        User user;
        List<String> data=new ArrayList<>();
        data.add(title);
        data.add(category);
        if(userRepository.existsById(emailId)){
            user=userRepository.findById(emailId).get();
            if(user.getWishList().contains(data)){
                throw new DataAlreadyExistException();
            }
            List<List<String>> wishlist=user.getWishList();
            wishlist.add(data);
            user.setWishList(wishlist);
            userRepository.save(user);
        }
        else throw new UserNotFoundException();
        return data;
    }

    //add to history
    @CacheEvict(allEntries = true)
    @Override
    public List<String> addToHistory(String emailId, String title, String category) throws UserNotFoundException {
        User user;
        List<String> data=new ArrayList<>();
        data.add(title);
        data.add(category);
        if(userRepository.existsById(emailId)){
            user=userRepository.findById(emailId).get();
            List<List<String>> history=user.getHistory();
            history.add(data);
            user.setHistory(history);
            userRepository.save(user);
        }
        else throw new UserNotFoundException();
        return data;
    }

    @Override
    @KafkaListener(topics = "savedUser",groupId = "Group_JsonObject")
    public UserPayment saveUserPayment(UserPayment userPayment) {
        UserPayment saveUser =  userPaymentRepository.save(userPayment);
        return saveUser;

    }
}
