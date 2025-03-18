package com.example.betterStudy.controller;

import com.example.betterStudy.model.dto.RequestUserDTO;
import com.example.betterStudy.model.dto.UserResponseDTO;
import com.example.betterStudy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserResponseDTO> findById(@Valid @PathVariable(name = "id")long id)
    {
        UserResponseDTO userResponseDTO = userService.findById(id);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody RequestUserDTO requestUserDTO) //DTO!!!!!
    {
        UserResponseDTO user = userService.createUser(requestUserDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public HttpStatus deleteById(@Valid @PathVariable(name = "id")long id){
        userService.deleteById(id);
        return HttpStatus.OK;
    }


}

