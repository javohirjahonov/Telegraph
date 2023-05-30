package com.example.telegraphback.controller;

import com.example.telegraphback.dto.UserCreateDto;
import com.example.telegraphback.entity.UserEntity;
import com.example.telegraphback.exceptions.RequestValidationException;
import com.example.telegraphback.service.UserService.UserService;
import com.example.telegraphback.service.contentService.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final ContentService contentService;

    @PostMapping("/api/v1/sign-up")
    public ResponseEntity<UserEntity> signUpPage(
           @Valid @RequestBody UserCreateDto userCreateDto,
           BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        UserEntity add = userService.add(userCreateDto);

        return ResponseEntity.ok(add);
    }

    @PostMapping("/api/v1/sign-in")
    public UserEntity signInPage (
             @RequestParam String phoneNumber,
             @RequestParam String password
    ) {
        return userService.signIn(phoneNumber, password);
    }

}
