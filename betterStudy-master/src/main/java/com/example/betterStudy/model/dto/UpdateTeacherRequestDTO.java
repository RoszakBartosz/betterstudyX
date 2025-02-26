package com.example.betterStudy.model.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTeacherRequestDTO {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String grade;
    private String rate;
}
