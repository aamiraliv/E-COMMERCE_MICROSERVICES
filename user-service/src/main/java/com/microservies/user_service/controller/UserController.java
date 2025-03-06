package com.microservies.user_service.controller;


import com.microservies.user_service.model.User;
import com.microservies.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> GetAllUsers(User user){
        List<User> users = service.GetAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping
    public ResponseEntity<String> CreateUser(@Valid @RequestBody User user){
        service.CreateUser(user);
        return ResponseEntity.ok("user created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> GetUserById(@PathVariable Long id){
        User user = service.GetUserById(id);
        return ResponseEntity.ok(user);
    }
}
