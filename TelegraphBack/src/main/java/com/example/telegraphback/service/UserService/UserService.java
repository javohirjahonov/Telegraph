package com.example.telegraphback.service.UserService;

import com.example.telegraphback.dto.UserCreateDto;
import com.example.telegraphback.entity.UserEntity;
import com.example.telegraphback.service.BaseService;
import org.springframework.http.ResponseEntity;

public interface UserService extends BaseService<UserEntity, UserCreateDto> {
    UserEntity signIn(String phoneNumber, String password);
}
