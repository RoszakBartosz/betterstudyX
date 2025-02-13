package com.example.betterStudy.model.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateTeacherRequestDTO {
    private String name;
    private String lastName;
    private String email;
    private String grade;
    private String rate;
}
