package com.example.mongodb.controllers;

import com.example.mongodb.entities.User;
import com.example.mongodb.models.ApiResponse;
import com.example.mongodb.repositories.UserRepository;
import com.example.mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "registerUser")
    ResponseEntity<ApiResponse> registerUser(@RequestBody User user){

        if(user.getEmail() == null){
            return new ResponseEntity<>(new ApiResponse("Email is required", null), HttpStatus.CONFLICT);
        }
        if(user.getPassword() == null){
            return new ResponseEntity<>(new ApiResponse("Password is required", null), HttpStatus.CONFLICT);
        }
        if(user.getFirstName() == null){
            return new ResponseEntity<>(new ApiResponse("First Name is required", null), HttpStatus.CONFLICT);
        }
        if(user.getLastName() == null){
            return new ResponseEntity<>(new ApiResponse("Last Name is required", null), HttpStatus.CONFLICT);
        }

        if(userService.getOneUserByEmail(user.getEmail()).isPresent()){
            return new ResponseEntity<>(new ApiResponse("User already exists", null), HttpStatus.CONFLICT);
        }

        final Optional<User> registeredUser = userService.createUser(user);

        if(registeredUser.isPresent()){
            return new ResponseEntity<>(new ApiResponse("Created User Successfully", registeredUser.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("Couldn't register user", null), HttpStatus.BAD_REQUEST);

    }

}
