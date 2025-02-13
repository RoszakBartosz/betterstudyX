package com.example.betterStudy.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.Where;
import org.hibernate.type.YesNoConverter;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "table_product")
@SoftDelete
public class Classroom {
    // Classroom - id, name, location, lessons(tu jakas relacja)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "classroom_name")
    private String name;
    @Column(name = "classroom_location")
    private String location;
    @OneToMany() // to wiadomo, poptrzec i dokonczyc
    private List<Lesson> lessons = new ArrayList<>();
}
