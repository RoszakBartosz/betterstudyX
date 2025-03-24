package com.example.betterStudy.service;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.Teacher;
import com.example.betterStudy.model.dto.CreateLessonRequestDTO;
import com.example.betterStudy.model.dto.LessonResponseDTO;
import com.example.betterStudy.model.dto.UpdateLessonRequestDTO;
import com.example.betterStudy.model.exception.GlobalExceptionHandler;
import com.example.betterStudy.model.exception.NotFoundLessonException;
import com.example.betterStudy.model.exception.NotFoundTeacherException;
import com.example.betterStudy.repository.ClassroomRepository;
import com.example.betterStudy.repository.LessonRepository;
import com.example.betterStudy.repository.StudentRepository;
import com.example.betterStudy.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private ClassroomRepository classroomRepository;
    @InjectMocks
    private LessonService service;

//    @Test
//    void save_ShouldSaveALesson(){
//        long teacherId = 1l;
//        long classroomId = 1l;
//        LocalDateTime now = LocalDateTime.now();
//        String topic = "saaa";
//        CreateLessonRequestDTO createLessonRequestDTO = new CreateLessonRequestDTO(teacherId, classroomId, now, topic);
//        Classroom classroom = Classroom.builder().id(classroomId)
//                .location("a")
//                .name("asds")
//                .build();
//
//        Teacher build = Teacher.builder().id(teacherId)
//                .grade("ads")
//                .email("sdad")
//                .lastName("dfsfd")
//                .name("fds")
//                .build();
//
//        Lesson build1 = Lesson.builder().topic(topic)
//                .classroom(classroom)
//                .teacher(build)
//                .lessonDateTime(now)
//                .build();
//        Lesson.builder().lessonDateTime(now);
//        when(classroomRepository.findById(classroomId)).thenReturn(Optional.ofNullable(classroom));
//        when(teacherRepository.findById(teacherId)).thenReturn(Optional.ofNullable(build));
//        service.save(createLessonRequestDTO);
//        verify(lessonRepository).save(build1);
//    }
    @Test
    void findById_shouldReturnLessonResponseDTO_whenLessonExists() {
        // Given
        long lessonId = 1L;

        Teacher teacher = Teacher.builder()
                .id(2L)
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .grade("Math")
                .rate("5")
                .build();

        Classroom classroom = Classroom.builder()
                .id(3L)
                .name("Room A")
                .build();

        Student student1 = Student.builder()
                .id(4L)
                .name("Alice")
                .build();

        Student student2 = Student.builder()
                .id(5L)
                .name("Bob")
                .build();

        Lesson lesson = Lesson.builder()
                .id(lessonId)
                .topic("Math")
                .lessonDateTime(LocalDateTime.of(2025, 3, 22, 10, 0))
                .teacher(teacher)
                .classroom(classroom)
                .students(List.of(student1, student2))
                .build();

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.ofNullable(lesson));

        // When
        LessonResponseDTO result = service.findById(lessonId);

        // Then
        assertNotNull(result);
        assertEquals(lessonId, result.getId());
        assertEquals("Math", result.getTopic());
        assertEquals(LocalDateTime.of(2025, 3, 22, 10, 0), result.getLessonDateTime());
        assertEquals(2L, result.getTeacherid());
        assertEquals(List.of(4L, 5L), result.getStudentsIds());
        assertEquals(3L, result.getClassroomid());

        verify(lessonRepository, times(1)).findById(lessonId);
    }
    @Test
    void findById_shouldThrowNotFoundTeacherException_whenTeacherNotFound() {
        when(lessonRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundLessonException.class, () -> service.findById(1L));
    }

}
