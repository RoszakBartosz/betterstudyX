package com.example.betterStudy.repository;

import com.example.betterStudy.model.User;
import com.example.betterStudy.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByUserRole(UserRole userRole);
}
