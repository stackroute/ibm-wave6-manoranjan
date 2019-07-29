package com.stackroute.controller;

import com.stackroute.service.UserPackageService;
import com.stackroute.domain.UserPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
