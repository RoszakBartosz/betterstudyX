package com.example.betterStudy.repository;

import com.example.betterStudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
