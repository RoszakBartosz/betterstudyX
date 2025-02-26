package com.example.betterStudy.repository;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.dto.ClassroomResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
