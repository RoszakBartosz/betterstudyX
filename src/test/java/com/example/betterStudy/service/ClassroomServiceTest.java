package com.example.betterStudy.service;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.dto.ClassroomResponseDTO;
import com.example.betterStudy.model.dto.CreateClassroomRequestDTO;
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
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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
        assertEquals(expectedResponseDTO, responseDTO);

    }

    @Test
    void findById_shouldThrowExceptionWhenIdDoesntExist(){

        //given
        long id = 1l;

        //when/then
        assertThrows(NoSuchElementException.class, () -> classroomService.findById(id));

    }
    @Test
    void save_shouldSaveTheClassroom(){

        //given
        long id = 1l;
        String name = "electric class";
        String location = "A303";
        Classroom classroom = Classroom.builder()
                .name(name)
                .location(location)
                .build();

        CreateClassroomRequestDTO request = CreateClassroomRequestDTO.builder()
                .name(name)
                .location(location)
                .build();
        
        //when
        Mockito.when(classroomRepository.save(classroom)).thenReturn(classroom);
        classroomService.save(request);
        //then
        Mockito.verify(classroomRepository).save(classroom);
    }
    @Test
    void findAll_shouldReturnAPagination(){
        //given
        String name = "electric class";
        String location = "A303";
        Classroom classroom = Classroom.builder()
                .name(name)
                .location(location)
                .build();
        Classroom classroom2 = Classroom.builder()
                .name(name)
                .location(location)
                .build();
        Classroom classroom3 = Classroom.builder()
                .name(name)
                .location(location)
                .build();
        Pageable pageable = PageRequest.of(1, 2, Sort.by("name"));

        List<Classroom> classroom4 = List.of(classroom2, classroom3);
        Page<Classroom> page = new PageImpl<>(classroom4,pageable,classroom4.size());
        //when
        Mockito.when(classroomRepository.findAll(pageable)).thenReturn(page);
        classroomService.findAll(pageable);
        //then
        Mockito.verify(classroomRepository).findAll(pageable);
        classroomService.findAll(pageable);
    }





}