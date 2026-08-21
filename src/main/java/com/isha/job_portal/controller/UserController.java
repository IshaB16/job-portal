package com.isha.job_portal.controller;

import com.isha.job_portal.dto.LoginRequest;
import com.isha.job_portal.dto.LoginResponse;
import com.isha.job_portal.dto.UserRegisterRequest;
import com.isha.job_portal.dto.UserResponse;
import com.isha.job_portal.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}