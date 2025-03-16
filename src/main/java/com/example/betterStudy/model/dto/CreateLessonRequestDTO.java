package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateLessonRequestDTO {
    @NotNull
    private long teacherId; // jak ktos tworzy lekcje w json, to ma podac caly obiekt Teacher i classroom? :Dmoze byc tylko id? musi byc tylko, potem po tym id w serwisie szukasz z db, i masz obikety Teacher i classtom
    @NotNull
    private long classroomId;
    @NotNull
    private LocalDateTime lessonDateTime;
    @NotBlank
    private String topic;
}
