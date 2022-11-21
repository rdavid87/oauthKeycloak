package com.msbills.api.repository;

import com.msbills.api.model.UserDTO;
import com.msbills.config.feign.FeignInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name= "user-service",url = "${app.url-user-service}", configuration = FeignInterceptor.class)
public interface UserFeignClient {

    @GetMapping("/usuarios/id/{userId}")
    ResponseEntity<UserDTO> findByUserId(@PathVariable(value = "userId") String userId);

    @GetMapping("/usuarios/admin/{username}")
    ResponseEntity<UserDTO> findByUserUsername(@PathVariable(value = "username") String username);
}

