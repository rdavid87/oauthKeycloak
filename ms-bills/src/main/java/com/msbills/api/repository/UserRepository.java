package com.msbills.api.repository;

import com.msbills.api.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final UserFeignClient userFeignClient;
    public UserRepository(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public UserDTO findByUserId(String userId){
        ResponseEntity<UserDTO> response = userFeignClient.findByUserId(userId);
        return response.getBody();
    }

    public UserDTO findByUserUsername(String username){
        ResponseEntity<UserDTO> response = userFeignClient.findByUserUsername(username);
        return response.getBody();
    }


}
