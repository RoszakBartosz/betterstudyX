package com.example.betterStudy.service;

import com.example.betterStudy.model.User;
import com.example.betterStudy.model.dto.UserResponseDTO;
import com.example.betterStudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public List<User> getUsers;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponseDTO createUser(User user) {
        userRepository.save(user);
        return UserResponseDTO.builder()
                .email(user.getUsername())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).get();
        return user;
    }
}
