package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "name cannot be empty ")
    private String name;
    @NotBlank(message = "lastname cannot be empty ")
    private String lastName;
    @NotBlank(message = "email cannot be empty ")
    private String email;
    @NotBlank(message = "grade cannot be empty ")
    private String grade;
    @NotNull
    LocalDate registrationDate;
    @NotNull
    List<Lesson> lessons;
}

