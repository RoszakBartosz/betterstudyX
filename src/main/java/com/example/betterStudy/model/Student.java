package com.example.betterStudy.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.type.YesNoConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@SoftDelete
public class Student {
    // Student - long id, name, lastName, email, grade, lessons (many to many), registrationDate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "student_name")
    private String name;
    private String lastName;
    private String email;
    private String grade;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "students_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private List<Lesson> lessons = new ArrayList<>();
    private LocalDate registrationDate;
    private boolean deleted;


}
