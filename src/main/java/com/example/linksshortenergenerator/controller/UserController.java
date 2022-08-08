package com.example.linksshortenergenerator.controller;

import com.example.linksshortenergenerator.dto.user.UserRegistrationDto;
import com.example.linksshortenergenerator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/register")
    ResponseEntity<?> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.register(userRegistrationDto);
        return ResponseEntity.ok().build();
    }
}
