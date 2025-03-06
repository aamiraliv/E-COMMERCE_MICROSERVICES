package com.microservies.user_service.service;

import com.microservies.user_service.Exception.UserNotFoundException;
import com.microservies.user_service.model.User;
import com.microservies.user_service.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> GetAllUsers() {
        return repository.findAll();
    }

    public void CreateUser(User user) {
        repository.save(user);
    }

    public User GetUserById(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("userNotFoundWithID:"+id)
        );
    }
}
