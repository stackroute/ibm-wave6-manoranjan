package com.stackroute.userpayment.controller;

import com.stackroute.userpayment.domain.UserPayment;
import com.stackroute.userpayment.service.UserPaymentService;
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

//
//        Date date=new Date();
//        long millies=date.getTime();
//        Timestamp timestamp=new Timestamp(millies);
//        userPackage.setMydate(timestamp);


        try {
            userPackageService.saveUser(userPackage);
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
        return new ResponseEntity<List<UserPayment>>(userPackageService.getAllUsers(),HttpStatus.OK);
    }
    @DeleteMapping("/user/{emailId}")
    public ResponseEntity<?> deleteUser(@PathVariable String emailId)
    {
        ResponseEntity responseEntity;
        userPackageService.deleteUser(emailId);
        responseEntity=new ResponseEntity<String>("deleted", HttpStatus.CREATED);
        return responseEntity;
    }

}
