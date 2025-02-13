package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateStudentRequestDTO {
    private String name;
    private String lastName;
    private String email;
    private String grade;
}
