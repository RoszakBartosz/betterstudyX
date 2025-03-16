package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateClassroomRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "classroom_name")
    @NotBlank(message = "Name cannot be empty! ")
    private String name;
    @Column(name = "classroom_location")
    @NotBlank(message = "Location cannot be empty ")
    private String location;
}
