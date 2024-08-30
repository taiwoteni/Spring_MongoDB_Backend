package com.example.mongodb.entities;

import com.google.gson.Gson;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.Optional;

@Document( collection = "users")
@Data
public class User {
    @Id
    private String id;

    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be null")
    private String lastName;

    private String password;

    private LocalDateTime lastSeen;

    @Transient
    private boolean online;

    @Transient
    private int age;

    @Indexed(unique = true)
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be null")
    private String email;


    private LocalDate dob;

    public User(){}

    public User(String id, String firstName, String lastName, String password, String email, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public User(String id, LocalDate dob, int age, String email, LocalDateTime lastSeen, String password, String lastName, String firstName) {
        this.id = id;
        this.dob = dob;
        this.age = age;
        this.email = email;
        this.lastSeen = lastSeen;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isOnline() {

        return true;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<LocalDate> getDob() {
        return Optional.ofNullable(dob);
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Optional<Integer> getAge(){
        return Optional.ofNullable(dob == null ? null : Period.between(dob, LocalDate.now()).getYears());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, User.class);
    }


    public static User fromJson(final HashMap<String,Object> json){
        return fromJson(new Gson().toJson(json));
    }

    public static User fromJson(final String json){
        return new Gson().fromJson(json, User.class);
    }

}
