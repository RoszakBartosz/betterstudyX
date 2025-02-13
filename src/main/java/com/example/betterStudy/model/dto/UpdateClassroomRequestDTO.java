package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.Lesson;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    // to tuz tez id do uopdate
    private String name;
    private String location;
}
