package com.microservies.user_service.service;

import com.microservies.user_service.Exception.UserNotFoundException;
import com.microservies.user_service.dto.UserDTO;
import com.microservies.user_service.model.User;
import com.microservies.user_service.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private MapperService mapperService;

    public List<UserDTO> GetAllUsers() {
        return repository.findAll().stream().map(mapperService::convertToDTO).collect(Collectors.toList());
    }

    public void CreateUser(User user) {
        repository.save(user);
    }

    public UserDTO GetUserById(Long id) {
        User user = repository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("userNotFoundWithID:"+id)
        );
        return mapperService.convertToDTO(user);
    }


    public Optional<User> GetUserByEmail(String email) {
        return repository.FindByEmail(email);
    }
}
