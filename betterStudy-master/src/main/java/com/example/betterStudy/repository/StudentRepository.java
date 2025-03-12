package com.example.betterStudy.repository;

import com.example.betterStudy.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    @Query(value = "SELECT student.email FROM student u")
    List<String> findAllEmailsForStudent();
}
