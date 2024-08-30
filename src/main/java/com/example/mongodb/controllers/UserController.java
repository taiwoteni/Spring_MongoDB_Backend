package com.example.mongodb.controllers;

import com.example.mongodb.entities.User;
import com.example.mongodb.models.ApiResponse;
import com.example.mongodb.repositories.UserRepository;
import com.example.mongodb.services.UserService;
import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "getUsers")
    ResponseEntity<ApiResponse> getStudents(){
        final List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(new ApiResponse("Got user's successfully", users), HttpStatus.OK);
    }

}
