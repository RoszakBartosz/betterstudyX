package com.example.betterStudy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name cannot be empty! ")
    private String name;
    @Column(name = "classroom_location")
    @NotBlank(message = "Location cannot be empty ")
    private String location;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom") // to wiadomo, poptrzec i dokonczyc
    private List<Lesson> lessons;
}
