package com.example.mongodb.services;

import com.example.mongodb.entities.User;
import com.example.mongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> createUser(final User user){
        // To make sure that irrespective of value passed in front-end
        // ID is always null, therefore generating it automatically. Every time.
        user.setId(null);
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> getOneUser(final String id){
        return userRepository.findById(id);
    }
    public Optional<User> getOneUserByEmail(final String email){
        return userRepository.findUserByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> updateUser(final User user){
        final Optional<User> search = getOneUser(user.getId());
        if(search.isEmpty()){
            return Optional.empty();
        }

        final User foundUser = search.get();

        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());

        if(user.getLastSeen() !=null){
            foundUser.setLastSeen(user.getLastSeen());
        }
        if(user.getDob().isPresent()){
            foundUser.setDob(user.getDob().get());
        }
        userRepository.save(foundUser);

        return Optional.of(foundUser);

    }
}
