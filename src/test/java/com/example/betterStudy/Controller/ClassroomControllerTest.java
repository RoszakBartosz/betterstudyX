package com.example.betterStudy.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.betterStudy.controller.ClassroomController;
import com.example.betterStudy.model.dto.ClassroomResponseDTO;
import com.example.betterStudy.model.dto.CreateClassroomRequestDTO;
import com.example.betterStudy.model.dto.UpdateClassroomRequestDTO;
import com.example.betterStudy.service.ClassroomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;

class ClassroomControllerTest {


    private MockMvc mockMvc;

    @Mock
    private ClassroomService classroomService;

    @InjectMocks
    private ClassroomController classroomController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).build();
    }

    @Test
    void testFindById() throws Exception {
        ClassroomResponseDTO responseDTO = ClassroomResponseDTO.builder()
                .id(1L)
                .name("Math Classroom")
                .build();

        when(classroomService.findById(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/classroom/find-by-id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.name").value(responseDTO.getName()));
    }

//    @Test
//    void testFindAll() throws Exception {
//        Page<ClassroomResponseDTO> page = new PageImpl<>(Collections.singletonList(
//                ClassroomResponseDTO.builder()
//                        .id(1L)
//                        .name("Math Classroom")
//                        .build()
//        ));
//
//        when(classroomService.findAll(PageRequest.of(1,2))).thenReturn(page);
//
//        mockMvc.perform(get("/classroom/find-all?size=2&page=1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1L))
//                .andExpect(jsonPath("$.content[0].name").value("Math Classroom"));
//    }

    @Test
    void testSave() throws Exception {
        CreateClassroomRequestDTO requestDTO = CreateClassroomRequestDTO.builder()
                .name("Math Classroom")
                .build();

        ClassroomResponseDTO responseDTO = ClassroomResponseDTO.builder()
                .id(1L)
                .name("Math Classroom")
                .build();

        when(classroomService.save(any(CreateClassroomRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/classroom/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.name").value(responseDTO.getName()));
    }

    @Test
    void testUpdate() throws Exception {
        UpdateClassroomRequestDTO requestDTO = UpdateClassroomRequestDTO.builder()
                .name("Updated Classroom")
                .build();

        ClassroomResponseDTO responseDTO = ClassroomResponseDTO.builder()
                .id(1L)
                .name("Updated Classroom")
                .build();

        when(classroomService.updateClassroom(any(UpdateClassroomRequestDTO.class), eq(1L))).thenReturn(responseDTO);

        mockMvc.perform(put("/classroom/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.name").value(responseDTO.getName()));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(classroomService).delete(1L);

        mockMvc.perform(delete("/classroom/delete/1"))
                .andExpect(status().isOk());
    }
}