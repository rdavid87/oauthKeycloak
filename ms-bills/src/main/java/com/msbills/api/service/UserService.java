package com.msbills.api.service;

import com.msbills.api.model.UserDTO;
import com.msbills.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findById(String userId){
        UserDTO userDTO = userRepository.findByUserId(userId);
        return userDTO;
    }

    public UserDTO findByUsername(String username){
        UserDTO userDTO = userRepository.findByUserUsername(username);
        return userDTO;
    }
}
