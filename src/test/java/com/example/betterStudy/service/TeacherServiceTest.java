package com.example.betterStudy.service;

import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.Teacher;
import com.example.betterStudy.model.dto.CreateTeacherRequestDTO;
import com.example.betterStudy.model.dto.TeacherResponseDTO;
import com.example.betterStudy.model.dto.UpdateTeacherRequestDTO;
import com.example.betterStudy.model.exception.NotFoundTeacherException;
import com.example.betterStudy.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    TeacherRepository teacherRepository;
    @InjectMocks
    TeacherService teacherService;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        Lesson lesson = Lesson.builder()
                .id(0L)
                .topic("Math")
                .lessonDateTime(LocalDateTime.of(2025, 3, 22, 10, 0))
                .teacher(teacher)
                .build();
        teacher = new Teacher(0L, "John", "Doe", "john.doe@example.com", "Math", "dcss", List.of(lesson));
    }

    @Test
    void findById_ShouldThrowANotFoundTeacherException(){
        long id = 3l;

        assertThrows(NotFoundTeacherException.class, () -> teacherService.findById(id));
    }



    @Test
    void findById_shouldReturnTeacherResponseDTO_whenTeacherExists() {
        when(teacherRepository.findById(0L)).thenReturn(Optional.of(teacher));
        TeacherResponseDTO response = teacherService.findById(0L);
        assertNotNull(response);
        assertEquals("John", response.getName());
    }

    @Test
    void findById_shouldThrowNotFoundTeacherException_whenTeacherNotFound() {
        when(teacherRepository.findById(0L)).thenReturn(Optional.empty());
        assertThrows(NotFoundTeacherException.class, () -> teacherService.findById(0L));
    }

    @Test
    void findAll_shouldReturnPageOfTeachers() {
        List<Teacher> teachers = List.of(teacher);
        Page<Teacher> teacherPage = new PageImpl<>(teachers);
        when(teacherRepository.findAll(any(Pageable.class))).thenReturn(teacherPage);
        Page<TeacherResponseDTO> result = teacherService.findAll(Pageable.unpaged(), null);
        assertFalse(result.isEmpty());
    }

    @Test
    void save_shouldReturnSavedTeacher() {
        CreateTeacherRequestDTO request = new CreateTeacherRequestDTO("John", "Doe", "john.doe@example.com", "Math", "asfa");
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);
        TeacherResponseDTO response = teacherService.save(request);
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    void updateTeacher_shouldUpdateAndReturnTeacherResponseDTO() {
        UpdateTeacherRequestDTO request = new UpdateTeacherRequestDTO(0L,"Jane", "Doe", "jane.doe@example.com", "Science", "dffs");
        Lesson lesson = Lesson.builder()
                .id(0L)
                .topic("Math")
                .lessonDateTime(LocalDateTime.of(2025, 3, 22, 10, 0))
                .teacher(teacher)
                .build();
        when(teacherRepository.findById(0L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(new Teacher(0L, "Jane", "Doe", "jane.doe@example.com", "Science", "svd", List.of(lesson)));
        TeacherResponseDTO response = teacherService.updateTeacher(request, 0L);
        assertEquals("Jane", response.getName());
    }

    @Test
    void delete_shouldDeleteTeacher() {
        doNothing().when(teacherRepository).deleteById(0L);
        assertDoesNotThrow(() -> teacherService.delete(0L));
        verify(teacherRepository, times(1)).deleteById(0L);
    }
}
