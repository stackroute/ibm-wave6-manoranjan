package com.stackroute.controller;

import com.stackroute.domain.Producer;
import com.stackroute.domain.UserPayment;
import com.stackroute.exceptions.DataAlreadyExistException;
import com.stackroute.exceptions.UserAllReadyExistException;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.ProducerService;
import com.stackroute.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "UseServiceApi",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*")
@RequestMapping(value = "api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProducerService producerService;

    //posting user details
    @ApiOperation(value = "save user")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAllReadyExistException {
        ResponseEntity responseEntity;
        userService.saveUser(user);
        responseEntity=new ResponseEntity<>("Successfully created", HttpStatus.OK);
        return responseEntity;
    }

    //posting user payment details
    @ApiOperation(value = "save user payment")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK"),@ApiResponse(code = 409,message = "CONFLICT")})
    @PostMapping("user-payment")
    public ResponseEntity<?> saveUser(@RequestBody UserPayment userPackage) {
        ResponseEntity responseEntity;
        try {
            userService.saveUserPayment(userPackage);
            responseEntity=new ResponseEntity<>("Successfully created", HttpStatus.OK);
        }

        catch(Exception e)
        {
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //fetching all the users
    @ApiOperation(value = "Get all users")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @GetMapping("users")
    public ResponseEntity<?> getAllUsers()  {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    //fetchhing the user wishlist details by email
    @GetMapping("user/wish/{email}")
    public ResponseEntity<?> getAllWishlist(@PathVariable("email") String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getAllWishlist(emailId),HttpStatus.OK);

    }

    //fetching user history by email
    @ApiOperation(value = "Get history")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @GetMapping("user/history/{email}")
    public ResponseEntity<?> getAllHistory(@PathVariable("email") String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getAllHistory(emailId),HttpStatus.OK);
    }

    //add media to wishlist
    @ApiOperation(value = "Add media to wishlist")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @PostMapping("/user/wish/{email}/{title}/{category}")
    public ResponseEntity<?> addToWishlish(@PathVariable("email") String emailId,@PathVariable("title") String title,@PathVariable("category") String category) throws UserNotFoundException, DataAlreadyExistException {
        return new ResponseEntity<>(userService.addToWishlish(emailId, title, category),HttpStatus.OK);
    }

    //add media to  history
    @ApiOperation(value = "Add media to history")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @PostMapping("/user/history/{email}/{title}/{category}")
    public ResponseEntity<?> addToHistory(@PathVariable("email") String emailId,@PathVariable("title") String title,@PathVariable("category") String category) throws UserNotFoundException {
        return new ResponseEntity<>(userService.addToHistory(emailId, title, category),HttpStatus.OK);
    }

    //fetching user by emailId
    @ApiOperation(value = "Get user by email")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getById(@PathVariable("email") String emailId) throws UserNotFoundException {
        ResponseEntity responseEntity;
        User user=null;
        user=userService.getById(emailId);
        responseEntity=new ResponseEntity<>(user, HttpStatus.OK);
        return responseEntity;
    }

    //deleting user by email
    @ApiOperation(value = "Delete user by email")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String emailId) throws UserNotFoundException {
        ResponseEntity responseEntity;
        userService.deleteUser(emailId);
        responseEntity=new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        return responseEntity;
    }

    //updating user details by email
    @ApiOperation(value = "Update user")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email") String emailId, @RequestBody User user) throws UserNotFoundException {
        ResponseEntity responseEntity;
        userService.updateUser(emailId,user);
        responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        return responseEntity;
    }

    //updating user details by email
    @ApiOperation(value = "Update producer")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @PutMapping("/producer/{email}")
    public ResponseEntity<?> updateProducer(@PathVariable("email") String emailId, @RequestBody Producer producer) {
        ResponseEntity responseEntity;
        producerService.updateProducer(emailId,producer);
        responseEntity = new ResponseEntity<>(producer, HttpStatus.OK);
        return responseEntity;
    }


    //fetching user by emailId
    @ApiOperation(value = "Get producer by email")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    @GetMapping("/producers/{email}")
    public ResponseEntity<?> getByEmailId(@PathVariable("email") String emailId) {
        ResponseEntity responseEntity;
        Producer producer=null;
        producer=producerService.getByEmailId(emailId);
        responseEntity=new ResponseEntity<>(producer, HttpStatus.OK);
        return responseEntity;
    }

}
