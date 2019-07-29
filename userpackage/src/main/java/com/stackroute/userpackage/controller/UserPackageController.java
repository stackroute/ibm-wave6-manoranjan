package com.stackroute.userpackage.controller;

import com.stackroute.userpackage.domain.UserPackage;
import com.stackroute.userpackage.service.UserPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value="*")
@RequestMapping(value="api/v1")
public class UserPackageController {
    private UserPackageService userPackageService;

    public UserPackageController(UserPackageService userPackageService)
    {
        this.userPackageService=userPackageService;
    }
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody UserPackage userPackage)
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
        return new ResponseEntity<List<UserPackage>>(userPackageService.getAllUsers(),HttpStatus.OK);
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
