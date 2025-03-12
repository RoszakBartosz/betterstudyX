package com.example.betterStudy.repository;

import com.example.betterStudy.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "SELECT u.email FROM teacher u")
    List<String> findAllEmailsForTeacher();
}
