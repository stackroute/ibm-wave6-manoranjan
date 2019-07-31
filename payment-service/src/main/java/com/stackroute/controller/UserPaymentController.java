package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.domain.UserPayment;
import com.stackroute.exception.EmailIdNotFoundException;
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
    //posting the data in to the database
    public ResponseEntity<String> saveUser(UserPayment userPayment) throws EmailIdNotFoundException
    {
        ResponseEntity responseEntity;

        try {
            userPackageService.saveUserPayment(userPayment);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }

        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("user-email")
    public ResponseEntity<String> saveUser(User user)
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
    //getting the users from the database
    public ResponseEntity<List<UserPayment>> getAllUsers(){
        return new ResponseEntity<>(userPackageService.getAllUsers(),HttpStatus.OK);
    }

}
