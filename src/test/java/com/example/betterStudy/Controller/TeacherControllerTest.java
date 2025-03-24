package com.example.betterStudy.Controller;


import com.example.betterStudy.controller.LessonController;
import com.example.betterStudy.controller.StudentController;
import com.example.betterStudy.controller.TeacherController;
import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.Teacher;
import com.example.betterStudy.model.dto.*;

import static com.example.betterStudy.model.enums.UserRole.ADMIN;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.betterStudy.model.enums.UserRole;
import com.example.betterStudy.repository.ClassroomRepository;
import com.example.betterStudy.repository.LessonRepository;
import com.example.betterStudy.service.LessonService;
import com.example.betterStudy.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TeacherController.class)
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeacherService teacherService;
    ObjectMapper objectMapper = new ObjectMapper();

    @WithMockUser(username = "admin", password = "admin")
    @Test
    void findById_shouldReturnTeacher() throws Exception {
        TeacherResponseDTO ads = TeacherResponseDTO.builder()
                .name("ads")
                .id(1L)
                .email("ads@d.d")
                .build();

        when(teacherService.findById(1L)).thenReturn(ads);

        mockMvc.perform(get("/teacher/find-by-id/1")
                .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.name").value("ads"))
                .andExpect(jsonPath("$.id").value(1L));

        verify(teacherService).findById(1l);
    }
//    @WithMockUser(username = "admin", password = "admin")
//    @Test
//    void findAll_shouldReturnTeacher() throws Exception {
//        TeacherResponseDTO ads = TeacherResponseDTO.builder()
//                .name("ads")
//                .id(1L)
//                .email("ads@d.d")
//                .build();
//
//        TeacherResponseDTO ads1 = TeacherResponseDTO.builder()
//                .name("ads1")
//                .id(2L)
//                .email("ads1@d.d")
//                .build();
//        Page<TeacherResponseDTO> page = new PageImpl<>(List.of(ads, ads1));
//
//        when(teacherService.findAll(any(), any())).thenReturn(page);
//
//        mockMvc.perform(get("/teacher/find-all")
//                        .with(httpBasic("admin", "admin")))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect( jsonPath("$.content[0].name").value("ads"))
//                .andExpect(jsonPath("$.content[0].id").value(1L))
//                .andExpect( jsonPath("$.content[1].name").value("ads1"))
//                .andExpect(jsonPath("$.content[1].id").value(2L));
//
//        verify(teacherService).findAll(any(), any());
//    }
//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
//    void testSave() throws Exception {
//        CreateTeacherRequestDTO requestDTO = CreateTeacherRequestDTO.builder()
//                .name("Mathew")
//                .build();
//
//        TeacherResponseDTO responseDTO = TeacherResponseDTO.builder()
//                .id(1L)
//                .name("Mathew")
//                .build();
//
//        when(teacherService.save(any(CreateTeacherRequestDTO.class))).thenReturn(responseDTO);
//
//        mockMvc.perform(post("/teacher/save")
//                        .with(httpBasic("admin", "admin"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
//                .andExpect(jsonPath("$.name").value(responseDTO.getName()));
//    }
}
