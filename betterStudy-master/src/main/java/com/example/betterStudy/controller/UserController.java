package com.example.betterStudy.controller;

import com.example.betterStudy.model.User;
import com.example.betterStudy.model.dto.UserResponseDTO;
import com.example.betterStudy.repository.UserRepository;
import com.example.betterStudy.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public List<User> getUsers(@RequestParam("username") Optional<String> usernameSubstring)
    {
        return userService.getUsers; //?
    }

    @PostMapping("/create-user")
    public UserResponseDTO createUser(@RequestBody User user) //DTO!!!!!
    {
        return userService.createUser(user); //nadaj role user
    }



}

