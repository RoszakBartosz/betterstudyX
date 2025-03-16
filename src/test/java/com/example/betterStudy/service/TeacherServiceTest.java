package com.example.betterStudy.service;

import com.example.betterStudy.model.exception.NotFoundTeacherException;
import com.example.betterStudy.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    TeacherRepository teacherRepository;
    @InjectMocks
    TeacherService teacherService;

    @Test
    void findById_ShouldThrowANotFoundTeacherException(){
        long id = 3l;

        Assertions.assertThrows(NotFoundTeacherException.class, () -> teacherService.findById(id));

    }

}
