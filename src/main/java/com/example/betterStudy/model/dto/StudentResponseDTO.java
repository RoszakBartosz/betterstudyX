package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
    @Data // co to przerwy przed adnotaccjiami xD
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @EqualsAndHashCode(of = "id")
public class StudentResponseDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        @NotBlank(message = "name cannot be empty ")
        private String name;
        @NotBlank(message = "lastname cannot be empty ")
        private String lastName;
        @NotBlank(message = "email cannot be empty ")
        private String email;
        @NotNull
        private LocalDate registrationDate;
        @NotBlank(message = "grade cannot be empty ")
        private String grade;
}
