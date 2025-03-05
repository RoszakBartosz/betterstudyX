package com.example.betterStudy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.type.YesNoConverter;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of = "id")
@SoftDelete
public class Teacher {
    // Teacher - id, name, lastName, email, grade, rate, lessons (oneToMany)
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
    @NotBlank(message = "rate cannot be empty")
    private String rate;
    @NotEmpty(message = "lessons cannot be empty ")
    @OneToMany(fetch = FetchType.LAZY) // tu tez trzeba dodac mappedBy
    private List<Lesson> lessons = new ArrayList<>();
}
