package com.example.betterStudy.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTeacherRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name cannot be empty ")
    @Column(name = "teacher_name")
    private String name;
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;
    @Email
    private String email;
    @NotBlank(message = "grade cannot be emoty")
    private String grade;
    private String rate;
}
