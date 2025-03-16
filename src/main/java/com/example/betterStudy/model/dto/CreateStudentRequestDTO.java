package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateStudentRequestDTO {

    @NotBlank(message = "name cannot be empty ")
    private String name;
    @NotBlank(message = "lastname cannot be empty ")
    private String lastName;
    @NotBlank(message = "email cannot be empty ")
    private String email;
    @NotBlank(message = "grade cannot be empty ")
    private String grade;
}
