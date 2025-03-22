package com.example.betterStudy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Version
    @Column(name = "lesson_version", nullable = false)
    private Integer version = 0;
    @ManyToOne(fetch = FetchType.LAZY) // to, to sao co na dole //TODO
    @NotNull(message = "teacher cannot be null ")
    private Teacher teacher;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lessons") // to git
    private List<Student> students = new ArrayList<>();
    @NotNull(message = "classroom cannot be null ")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    @NotNull(message = "lessonDateTime cannot be null ")
    private LocalDateTime lessonDateTime;
    @NotBlank( message = "topic cannot be empty ")
    private String topic;
}
