package com.example.betterStudy.service;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.dto.ClassroomResponseDTO;
import com.example.betterStudy.repository.ClassroomRepository;
import com.example.betterStudy.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //TODO po co to
class ClassroomServiceTest {
    @Mock
    LessonRepository lessonRepository;
    @Mock
    ClassroomRepository classroomRepository;
    @InjectMocks
    ClassroomService classroomService;


    //happyPath
    @Test
    void testFindById() {

        //given
        long id = 1l;
        String name = "klasa walenia konia";
        String location = "sw Anny 3";
        Classroom klasaWaleniaKonia = new Classroom(id, name, location, null);

        ClassroomResponseDTO expectedResponseDTO = ClassroomResponseDTO.builder()
                .id(id)
                .name(name)
                .location(location)
                .build();

        Mockito.when(classroomRepository.findById(id)).thenReturn(Optional.of(klasaWaleniaKonia));

        //when
        ClassroomResponseDTO responseDTO = classroomService.findById(id);

        //then
        Assertions.assertEquals(expectedResponseDTO, responseDTO);

    }

    @Test
    void findById_shouldThrowExceptionWhenIdDoesntExist(){

        //given
        long id = 1l;

        //when/then
        Assertions.assertThrows(NoSuchElementException.class, () -> classroomService.findById(id));

    }

//    @Test
//    void saveTest(){
//        classroomService.save(dto);
//
//        Mockito.verify(classroomRepository).save(classroom);
//    }




}