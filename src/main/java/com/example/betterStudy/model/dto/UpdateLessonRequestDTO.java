package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

public class UpdateLessonRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "teacher cannot be null ")
    private long teacherId;
    @NotEmpty(message = "List of students cannot be empty ")
    private List<Student> students = new ArrayList<>();
    @NotNull(message = "classroom cannot be null ")
    @JoinColumn(name = "classroom_id")
    private long classroomId;
    @NotNull(message = "lessonDateTime cannot be null ")
    private LocalDateTime lessonDateTime;
    @NotBlank( message = "topic cannot be empty ")
    private String topic;
}
