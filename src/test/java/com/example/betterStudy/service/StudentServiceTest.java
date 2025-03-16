package com.example.betterStudy.service;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.dto.CreateClassroomRequestDTO;
import com.example.betterStudy.model.dto.CreateStudentRequestDTO;
import com.example.betterStudy.model.dto.StudentResponseDTO;
import com.example.betterStudy.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(build));
        StudentResponseDTO result = studentService.findById(id);
        //then
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    void save_shouldSaveTheStudent(){

        //given
        long id = 1l;
        String name = "maćko";
        String lastname = "b";
        String email = "b@b.b";
        String grade = "4";
        Student stu = Student.builder().id(id)
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
        Mockito.verify(studentRepository).save(stu);
    }


}
