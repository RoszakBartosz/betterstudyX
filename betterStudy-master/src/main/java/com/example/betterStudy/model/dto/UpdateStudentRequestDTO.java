package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateStudentRequestDTO {
    // to tuz tez id do uopdate
    private String name;
    private String lastName;
    private List<Lesson> lessons;
    private String email;
    private String grade;
    private LocalDate registrationDate;
}
