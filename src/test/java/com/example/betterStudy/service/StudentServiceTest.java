package com.example.betterStudy.service;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.dto.CreateClassroomRequestDTO;
import com.example.betterStudy.model.dto.CreateStudentRequestDTO;
import com.example.betterStudy.model.dto.StudentResponseDTO;
import com.example.betterStudy.model.dto.UpdateStudentRequestDTO;
import com.example.betterStudy.model.exception.InvalidEmailException;
import com.example.betterStudy.model.exception.NotFoundStudentException;
import com.example.betterStudy.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentService studentService;

    @Test
    void findById_shouldReturnDTO(){
        //given
        long id = 1l;
        String name = "maciek";
        String lastname = "blaszczyk";
        String email = "maciej@blaszczyk.pl";
        String grade = "5.0";
        Student build = Student.builder()
                .id(id)
                .name(name)
                .lastName(lastname)
                .email(email)
                .grade(grade)
                .build();
        StudentResponseDTO expectedResult = StudentResponseDTO.builder()
                .id(id)
                .name(name)
                .lastName(lastname)
                .email(email)
                .grade(grade)
                .build();

        //when
        when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(build));
        StudentResponseDTO result = studentService.findById(id);
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    void save_shouldSaveTheStudent(){

        //given
        long id = 1l;
        String name = "maćko";
        String lastname = "b";
        String email = "b@b.b";
        String grade = "4";
        Student stu = Student.builder().id(0)
                .grade(grade)
                .email(email)
                .lastName(lastname)
                .name(name)
                .build();

        CreateStudentRequestDTO build = CreateStudentRequestDTO.builder()
                .name(name)
                .email(email)
                .grade(grade)
                .lastName(lastname)
                .build();

        studentService.save(build);
        //then
        verify(studentRepository).save(stu);
    }
    private Student student;
    private UpdateStudentRequestDTO updateRequestDTO;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .grade("A")
                .build();

        updateRequestDTO = UpdateStudentRequestDTO.builder()
                .name("John Updated")
                .lastName("Doe Updated")
                .email("john.doe.updated@example.com")
                .grade("B")
                .build();
    }

    @Test
    void updateStudent_shouldReturnUpdatedStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.findByEmail(updateRequestDTO.getEmail())).thenReturn(null);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO updatedStudent = studentService.updateStudent(updateRequestDTO, 1L);

        assertNotNull(updatedStudent);
        assertEquals("John Updated", updatedStudent.getName());
        assertEquals("Doe Updated", updatedStudent.getLastName());
        assertEquals("john.doe.updated@example.com", updatedStudent.getEmail());
        assertEquals("B", updatedStudent.getGrade());
        verify(studentRepository, times(1)).save(any(Student.class));
    }



    @Test
    void updateStudent_shouldThrowNotFoundStudentException_whenStudentNotFound() {
        // Arrange
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundStudentException exception = assertThrows(NotFoundStudentException.class, () -> {
            studentService.updateStudent(updateRequestDTO, 1L);
        });
        verify(studentRepository, never()).save(any(Student.class));
    }


}
