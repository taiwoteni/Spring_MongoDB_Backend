package com.example.mongodb.repositories;

import com.example.mongodb.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);
}
