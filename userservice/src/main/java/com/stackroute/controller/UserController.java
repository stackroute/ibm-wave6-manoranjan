package com.stackroute.controller;

import com.stackroute.domain.UserPayment;
import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.domain.User;
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
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.OK);
        }
        catch (UserAllReadyExistException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("userPayment")
    public ResponseEntity<?> saveUser(@RequestBody UserPayment userPackage)
    {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(userPackage);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.OK);
        }

        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/users/{emailId}")
    public ResponseEntity<?> getById(@PathVariable("emailId") String emailId){
        ResponseEntity responseEntity;
        User user=null;
        user=userService.getById(emailId);
        responseEntity=new ResponseEntity<User>(user, HttpStatus.CREATED);
        return responseEntity;
    }
    @DeleteMapping("/user/{emailId}")
    public ResponseEntity<?> deleteUser(@PathVariable String emailId)
    {
        ResponseEntity responseEntity;
        userService.deleteUser(emailId);
        responseEntity=new ResponseEntity<String>("Deleted Successfully", HttpStatus.CREATED);
        return responseEntity;
    }
    @PutMapping("/user/{emailId}")
    public ResponseEntity<?> updateUser(@PathVariable("emailId") String emailId, @RequestBody User user)
    {
        ResponseEntity responseEntity;
        userService.updateUser(emailId,user);
        responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
        return responseEntity;
    }

}
