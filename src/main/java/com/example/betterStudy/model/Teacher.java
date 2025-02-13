package com.example.betterStudy.model;

import jakarta.persistence.*;
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
    @Column(name = "teacher_name")
    private String name;
    private String lastName;
    private String email;
    private String grade;
    private String rate;
    private boolean deleted;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();
}
