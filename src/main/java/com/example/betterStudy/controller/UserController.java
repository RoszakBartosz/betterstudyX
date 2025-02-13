package com.example.betterStudy.controller;

import com.example.betterStudy.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
        private final UserService userService;
        private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        return ResponseEntity.ok().build();
    }
//    @PostMapping("/save")
//    public ResponseEntity<Void> save(@RequestParam String email, @RequestParam String password){
//
//    }
    public record LoginRequest(String email, String password) {
    }

}

