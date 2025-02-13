package com.example.betterStudy.repository;

import com.example.betterStudy.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
