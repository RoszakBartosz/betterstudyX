package com.example.betterStudy.service;

import com.example.betterStudy.model.User;
import com.example.betterStudy.model.dto.RequestUserDTO;
import com.example.betterStudy.model.dto.UserResponseDTO;
import com.example.betterStudy.model.enums.UserRole;
import com.example.betterStudy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "test@example.com", "password123", UserRole.STUDENT);
    }

    @Test
    void findById_shouldReturnUserResponseDTO_whenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserResponseDTO response = userService.findById(1L);
        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void findById_shouldThrowNoSuchElementException_whenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
    }

    @Test
    void createUser_shouldReturnUserResponseDTO() {
        RequestUserDTO request = new RequestUserDTO("test@example.com", "password123", UserRole.STUDENT);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserResponseDTO response = userService.createUser(request);
        assertEquals("test@example.com", response.getEmail());
    }
}
