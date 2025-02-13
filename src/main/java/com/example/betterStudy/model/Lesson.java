package com.example.betterStudy.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.type.YesNoConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of = "id")
@SoftDelete
public class Lesson {
    // Lesson - id, teacher (manyToOne), student(ManyToMany), classroom(manyToOne?), dateTime, topic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lessons")
    private List<Student> students = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Classroom classroom;
    private LocalDateTime lessonDateTime;
    private String topic;
}
