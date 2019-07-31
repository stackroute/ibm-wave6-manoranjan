package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.domain.UserPayment;
import com.stackroute.service.UserPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="*")
@RequestMapping(value="api/v1")
public class UserPaymentController {

    private UserPaymentService userPackageService;

    public UserPaymentController(UserPaymentService userPackageService)
    {
        this.userPackageService=userPackageService;
    }
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody UserPayment userPackage)
    {
        ResponseEntity responseEntity;

        try {
            userPackageService.saveUserPayment(userPackage);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }

        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("userEmail")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        ResponseEntity responseEntity;
        try {
            userPackageService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }

        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<List<UserPayment>>(userPackageService.getAllUsers(),HttpStatus.OK);
    }

}
