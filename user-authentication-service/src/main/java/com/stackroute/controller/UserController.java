package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exception.*;
import com.stackroute.jwt.SecurityTokenGenerator;
import com.stackroute.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@CrossOrigin(value = "*")
@RequestMapping("api/v1")
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Accept user into repository and generating token")
    @PostMapping("/user")
    public ResponseEntity<?> login(@RequestBody User loginDetails) throws UserNameOrPasswordEmptyException, UserNameNotFoundException, PasswordNotMatchException {

        String username = loginDetails.getEmailId();
        String password = loginDetails.getPassword();

        if (username == null || password == null) {

            throw new UserNameOrPasswordEmptyException();
        }

        User user = userService.findByEmailIdAndPassword(username, password);

        if (user == null) {
            throw new UserNameNotFoundException();
        }

        String fetchedPassword = user.getPassword();

        if (!password.equals(fetchedPassword)) {
            throw new PasswordNotMatchException();
        }

        // generating token

        SecurityTokenGenerator securityTokenGenrator = (User userDetails) -> {
            String jwtToken = "";

            jwtToken = Jwts.builder().setId(""+user.getEmailId()).setSubject(user.getRole()).setAudience(user.getEmailId()).setIssuedAt(new Date())

                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            Map<String, String> map1 = new HashMap<>();

            map1.put("token", jwtToken);

            map1.put("message", user.getRole());

            return map1;

        };
        Map<String, String> map = securityTokenGenrator.generateToken(user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @ApiOperation(value = "Gets all the user details(username,password,role)")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUser() throws UserNotFoundException
    {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "It saves all the user details")
    @PostMapping("/users/user")
    public ResponseEntity<?> saveEvent(@RequestBody User user) throws UserAlreadyExistsException {

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);

    }



}