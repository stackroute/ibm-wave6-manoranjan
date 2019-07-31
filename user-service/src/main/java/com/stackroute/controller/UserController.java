package com.stackroute.controller;

import com.stackroute.domain.UserPayment;
import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="*")
@RequestMapping(value="api/v1")
public class UserController {
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAllReadyExistException
    {
        ResponseEntity responseEntity;
        userService.saveUser(user);
        responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("user-payment")
    public ResponseEntity<?> saveUser(@RequestBody UserPayment userPackage)
    {
        ResponseEntity responseEntity;
        try {
            userService.saveUserPayment(userPackage);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.OK);
        }

        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() throws UserNotFoundException {
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("user/wish/{email}")
    public ResponseEntity<?> getAllWishlist(@PathVariable("email") String emailId) throws UserNotFoundException {
        return new ResponseEntity<List<List<String>>>(userService.getAllWishlist(emailId),HttpStatus.OK);
    }
    @GetMapping("user/history/{email}")
    public ResponseEntity<?> getAllHistory(@PathVariable("email") String emailId) throws UserNotFoundException {
        return new ResponseEntity<List<List<String>>>(userService.getAllHistory(emailId),HttpStatus.OK);
    }
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getById(@PathVariable("email") String emailId) throws UserNotFoundException{
        ResponseEntity responseEntity;
        User user=null;
        user=userService.getById(emailId);
        responseEntity=new ResponseEntity<User>(user, HttpStatus.CREATED);
        return responseEntity;
    }
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String emailId) throws UserNotFoundException
    {
        ResponseEntity responseEntity;
        userService.deleteUser(emailId);
        responseEntity=new ResponseEntity<String>("Deleted Successfully", HttpStatus.CREATED);
        return responseEntity;
    }
    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email") String emailId, @RequestBody User user) throws UserNotFoundException
    {
        ResponseEntity responseEntity;
        userService.updateUser(emailId,user);
        responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
        return responseEntity;
    }

}
