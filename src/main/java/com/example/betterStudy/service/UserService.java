package com.example.betterStudy.service;

import com.example.betterStudy.model.User;
import com.example.betterStudy.model.dto.RequestUserDTO;
import com.example.betterStudy.model.dto.UserResponseDTO;
import com.example.betterStudy.model.enums.UserRole;
import com.example.betterStudy.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers;

    public UserResponseDTO findById(long id){
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getUsername())
                .userRole(user.getUserRole())
                .build();

    }

    public UserResponseDTO createUser(RequestUserDTO requestUserDTO) {
        User user = User.builder()
                        .email(requestUserDTO.getEmail())
                        .password(passwordEncoder.encode(requestUserDTO.getPassword()))
                        .userRole(requestUserDTO.getUserRole())
                        .build();
        userRepository.save(user);
        return UserResponseDTO.builder()
                .email(user.getUsername())
                .userRole(user.getUserRole())
                .build();
    }

    @Transactional
    @PostConstruct
    public void createAdmin(){
        if (!userRepository.existsByUserRole(UserRole.ADMIN)){
            userRepository.save(
                    User.builder()
                            .email("admin")
                            .password(passwordEncoder.encode("admin"))
                            .userRole(UserRole.ADMIN)
                    .build());
        }
    }
    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(NoSuchElementException::new);
            return user;
    }
}
