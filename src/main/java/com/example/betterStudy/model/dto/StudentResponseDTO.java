package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
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
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String grade;
    private LocalDate registrationDate;
}
