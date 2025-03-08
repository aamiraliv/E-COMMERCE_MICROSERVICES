package com.microservies.user_service.service;

import com.microservies.user_service.dto.UserDTO;
import com.microservies.user_service.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MapperService {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }

    public User toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }
}
